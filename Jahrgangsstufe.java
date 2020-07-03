import java.util.Random;

/**
 * Eine Jahrgangstufe beinhaltet eine gewissen Anzahl an Fragen.
 *
 * Sie ist als eine einfach verkette Liste nach dem Entwurfsmuster
 * „Kompositum“ realisiert.
 *
 * Sie entspricht der Klasse „Liste“ aus dem AUD 4 Beispiel von Annabel.
 *
 * @see https://www.studon.fau.de/file2861024_download.html
 */
public class Jahrgangsstufe {
  private ListenElement kopf;

  /**
   * Die Anzahl an Fragen kann zwar rekursiv durch die Methode
   * {@link gibAnzahlFragen} bestimmt werden. Das ist aber etwas
   * zeitintensiv bei vielen Fragen.
   */
  private int anzahlFragen = 0;

  public Jahrgangsstufe() {
    kopf = new ListenAbschluss();
  }

  public void fügeVorneEin(Frage frage) {
    DatenKnoten neuerKnoten = new DatenKnoten(kopf, frage);
    kopf = neuerKnoten;
  }


  public void fügeHintenEin(Frage frage) {
    kopf = kopf.fügeHintenEin(frage);
  }

  public void fügeNachPositionEin(Frage frage, int position) {
    DatenKnoten neuerKnoten = new DatenKnoten(kopf, frage);
    kopf = neuerKnoten;
  }

  public void fügeZufälligEin(Frage frage) {
    Random zufall = new Random();
    if (zufall.nextBoolean()) {
      fügeVorneEin(frage);
    } else {
      fügeHintenEin(frage);
    }
  }

  public int gibAnzahlFragen() {
    return kopf.gibAnzahlFragen();
  }

  public Frage entnimmFrage() {
    Frage frage = kopf.gibFrage();
    kopf = kopf.gibNächstes();
    return frage;
  }
}
