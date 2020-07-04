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

  /**
   * Füge eine Frage vorne in die Liste ein.
   *
   * @param frage
   */
  public void fügeVorneEin(Frage frage) {
    ListenElement neuerKnoten = new DatenKnoten(kopf, frage);
    kopf = neuerKnoten;
    anzahlFragen++;
  }

  /**
   * Füge eine Frage hinten in die Liste ein.
   *
   * @param frage
   */
  public void fügeHintenEin(Frage frage) {
    kopf = kopf.fügeHintenEin(frage);
    anzahlFragen++;
  }

  /**
   *
   */
  public void fügeVorPositionEin(Frage frage, int position) {
    if (position == 0) {
      fügeVorneEin(frage);
      return;
    }

    if (position >= gibAnzahlFragen()) {
      fügeHintenEin(frage);
      return;
    }

    ListenElement nächstesElement = kopf;
    ListenElement vorhergehendesElement = nächstesElement;

    for (int i = 0; i < position; i++) {
      vorhergehendesElement = nächstesElement;
      nächstesElement = nächstesElement.gibNächstes();
    }
    DatenKnoten neuerKnoten = new DatenKnoten(nächstesElement, frage);

    if (vorhergehendesElement != null) {
      vorhergehendesElement.setzeNächstes(neuerKnoten);
    }
  }

  /**
   * Füge nach Zufall entweder vorne oder hinten in die Liste ein.
   *
   * @param frage
   */
  public void fügeZufälligEin(Frage frage) {
    Random zufall = new Random();
    if (zufall.nextBoolean()) {
      fügeVorneEin(frage);
    } else {
      fügeHintenEin(frage);
    }
  }

  /**
   * Gib die Anzahl der Fragen zurück.
   *
   * @return Die Anzahl der Fragen.
   */
  public int gibAnzahlFragen() {
    //return kopf.gibAnzahlFragen();
    return anzahlFragen;
  }

  /**
   * Entnehme die erste Frage aus der Liste und setzte die zweite
   * Frage an die Position der Ersten.
   *
   * @return Die Frage an der ersten Position
   */
  public Frage entnimmFrage() {
    Frage frage = kopf.gibFrage();
    kopf = kopf.gibNächstes();
    anzahlFragen--;
    return frage;
  }
}
