package nuernberg.team.spiel;

import java.util.Random;

/**
 * Eine Jahrgangstufe beinhaltet eine gewissen Anzahl an Fragen.
 *
 * Sie ist als eine einfach verkette Liste nach dem Entwurfsmuster „Kompositum“
 * realisiert.
 *
 * Sie entspricht der Klasse „Liste“ aus dem
 * <a href="https://www.studon.fau.de/file2861024_download.html">AUD 4 Beispiel
 * von Annabel.</a>
 */
public class FragenListe {
  private ListenElement kopf;

  /**
   * Die Anzahl an Fragen kann zwar rekursiv durch die Methode
   * {@link gibAnzahlFragen} bestimmt werden. Das ist aber etwas zeitintensiv bei
   * vielen Fragen.
   */
  private int anzahlFragen = 0;

  public FragenListe() {
    kopf = new ListenAbschluss();
  }

  /**
   * Füge eine Frage vorne in die Liste ein.
   *
   * @param frage Die Frage, die eingefügt werden soll.
   */
  public void fügeVorneEin(Frage frage) {
    ListenElement neuerKnoten = new DatenKnoten(kopf, frage);
    kopf = neuerKnoten;
    anzahlFragen++;
  }

  public ListenElement gibKopf() {
    return kopf;
  }

  /**
   * Füge eine Frage hinten in die Liste ein.
   *
   * @param frage Die Frage, die eingefügt werden soll.
   */
  public void fügeHintenEin(Frage frage) {
    kopf = kopf.fügeHintenEin(frage);
    anzahlFragen++;
  }

  /**
   * Füge ein Frage vor einer Positions-Nummer ein.
   *
   * @param frage    Die {@link Frage}, die einfügt werden sollen
   * @param position Die Positions-Nummer. 0 ist die erste Frage.
   *                 {@link anzahlFragen} - 1 ist die Positionsnummer der letzten
   *                 Frage.
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
    anzahlFragen++;
  }

  /**
   * Füge zufällig eine Frage in die Fragenliste ein.
   *
   * @param frage Die Frage, die eingefügt werden soll.
   */
  public void fügeZufälligEin(Frage frage) {
    Random zufall = new Random();
    // anzahlFragen ist bei der Initialisierung 0.
    // Außerdem ist die in nextInt angegeben Zahl nicht bei den
    // zurückgegeben Zufallszahlen dabei.
    fügeVorPositionEin(frage, zufall.nextInt(anzahlFragen + 1));
  }

  /**
   * Füge mehrere Fragen zufällig als zweidimensionales Feld ein.
   *
   * @param fragen Mehrere Fragen als zweidimensionales Feld.
   */
  public void fügeFragenAlsFeldEin(String[][] fragen) {
    for (int i = 0; i < fragen.length; i++) {
      String[] frage = fragen[i];
      fügeZufälligEin(new Frage(frage[0], frage[1], frage[2], frage[3], frage[4], Integer.parseInt(frage[5])));
    }
  }

  /**
   * Erzeuge eine neue Frage anhand von mehreren String-Argumenten und füge diese
   * Frage zufällig ein.
   *
   * @param fragenText      Der Text der Frage (Der eigentliche Fragensatz).
   * @param richtigeAntwort Die richtige Antwort.
   * @param falscheAntwort1 Die falsche Antwort Nr. 1.
   * @param falscheAntwort2 Die falsche Antwort Nr. 2.
   * @param falscheAntwort3 Die falsche Antwort Nr. 3.
   * @param schwierigkeit   Die Schwierigkeit (1-5).
   */
  public void erzeugeFrage(String fragenText, String richtigeAntwort, String falscheAntwort1, String falscheAntwort2,
      String falscheAntwort3, String schwierigkeit) {
    fügeZufälligEin(new Frage(fragenText, richtigeAntwort, falscheAntwort1, falscheAntwort2, falscheAntwort3,
        Integer.parseInt(schwierigkeit)));
  }

  /**
   * Entnimmt bzw. lösche einen Datenkonten aus der Liste.
   *
   * @param vorhergehender
   * @param datenKnoten
   */
  private void entnimmDatenKnoten(ListenElement datenKnoten, ListenElement vorhergehender) {
    if (vorhergehender != null) {
      vorhergehender.setzeNächstes(datenKnoten.gibNächstes());
    } else {
      kopf = datenKnoten.gibNächstes();
    }
    anzahlFragen--;
  }

  /**
   * Diese Methode berechnete die Anzahl der Datenknoten und damit die Anzahl der
   * Frage rekursiv. Im Gegensatz zur Methode {@link gibAnzahlFragen} wird in
   * dieser Methode keine Zähler verwendet, der hochgezählt werden muss. Diese
   * Methode funktioniert also sicherer.
   *
   * @return Die Anzahl der Datenknoten.
   */
  public int gibAnzahlDatenKnoten() {
    return kopf.gibAnzahlDatenKnoten();
  }

  /**
   * Gib die Anzahl der Fragen zurück.
   *
   * @return Die Anzahl der Fragen.
   */
  public int gibAnzahlFragen() {
    return anzahlFragen;
  }

  /**
   * Entnehme die erste Frage aus der Liste und setzte die zweite Frage an die
   * Position der Ersten.
   *
   * @return Die Frage an der ersten Position
   */
  public Frage entnimmErsteFrage() {
    Frage frage = kopf.gibFrage();
    kopf = kopf.gibNächstes();
    anzahlFragen--;
    return frage;
  }

  /**
   * Entnehme eine Frage aus der Liste mit der passenden Schwierigkeit. Falls
   * keine Frage mit der passenden Schwierigkeit vorhanden ist, entnehme die erste
   * Frage.
   *
   * @param schwierigkeit Die geschwünschte Schwierigkeit der Frage.
   *
   * @return Die Frage mit der gewünschen Schwierigkeit oder eine Frage an der
   *         ersten Position.
   */
  public Frage entnimmFrage(int schwierigkeit) {
    Frage frage = null;
    ListenElement vorhergehenderDatenKnoten = null;
    ListenElement datenKnoten = kopf;
    while (datenKnoten != null) {
      frage = datenKnoten.gibFrage();
      if (frage != null && frage.gibSchwierigkeit() == schwierigkeit) {
        entnimmDatenKnoten(datenKnoten, vorhergehenderDatenKnoten);
        break;
      }
      vorhergehenderDatenKnoten = datenKnoten;
      datenKnoten = datenKnoten.gibNächstes();
    }
    if (frage == null) {
      frage = entnimmErsteFrage();
    }
    return frage;
  }
}
