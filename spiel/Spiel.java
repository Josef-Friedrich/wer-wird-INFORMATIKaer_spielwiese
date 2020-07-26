package spiel;

/**
 * Ein Spiel
 */
public class Spiel {

  /**
   * Die Gewinnsumme. Nach jeder beantworteten Frage wird diese Summe verdoppelt.
   * Im der deutschen Fernsehsendung wird nicht immer verdoppelt, sondern manchmal
   * ein Betrag gewählt, sodass am Ende 1 Million gewonnen werden.
   */
  private int gewinnSumme;

  /**
   * Die aktuelle Fragenummer beginnend bei 1.
   */
  private int frageNummer = 0;

  /**
   * Die aktuelle Frage, die gerade beantwortet wird.
   */
  private Frage aktuelleFrage;


  /**
   * Die aktuelle Schwierigkeit der ausgewählten Fragen. Die Schwierigkeit
   * der Fragen sollte steigen. 1 ist die leichte. 5 ist schwer.
   */
  private int schwierigkeit = 1;

  /**
   * Eine Liste der zu unbeantwortenden Fragen. Wird eine Frage
   * beantwortet wird sind in die Liste {@link beantworteteFragen}
   * hinzugefügt.
   */
  private FragenListe unbeantworteteFragen;

  /**
   * Eine Liste der beantworteten Fragen. Die Liste ist nützlich
   * um eine Übersicht über die bereits gegeben Antworten zu erzeugen.
   */
  private FragenListe beantworteteFragen;

  public Spiel() {
    gewinnSumme = 50;
    unbeantworteteFragen = new FragenListe();
    beantworteteFragen = new FragenListe();
  }

  /**
   * Erzeuge eine neue Frage anhand von mehreren String-Argumenten und füge diese
   * Frage zufällig ein. Die Argumente können direkt aus dem CSVLeser eingelesen
   * werden.
   *
   * @param fragenText
   * @param richtigeAntwort
   * @param falscheAntwort1
   * @param falscheAntwort2
   * @param falscheAntwort3
   * @param schwierigkeit
   */
  public void erzeugeFrage(String fragenText, String richtigeAntwort, String falscheAntwort1, String falscheAntwort2,
      String falscheAntwort3, String schwierigkeit) {
    unbeantworteteFragen.erzeugeFrage(fragenText, richtigeAntwort, falscheAntwort1, falscheAntwort2, falscheAntwort3,
        schwierigkeit);
  }

  /**
   * Füge mehrere Fragen zufällig als zweidimensionales Feld ein.
   *
   * Die Methode wird an die gleichnamige Methode der Klasse Jahrgangsstufe
   * weitergegeben.
   *
   * @param fragen Mehrere Fragen als zweidimensionales Feld.
   */
  public void fügeFragenAlsFeldEin(String[][] fragen) {
    unbeantworteteFragen.fügeFragenAlsFeldEin(fragen);
  }

  /**
   * Entnimm eine Frage. Diese Methode wird an die Klasse {@link FragenListe}
   * weitergeleitet.
   *
   * @return
   */
  public Frage gibNächsteFrage() {
    aktuelleFrage = unbeantworteteFragen.entnimmFrage(schwierigkeit);
    schwierigkeit++;
    frageNummer++;
    return aktuelleFrage;
  }

  /**
   * Überprüfe, ob die Antwort zur aktuellen Frage richtig oder flasch
   * ist.
   *
   * @param antwort 0 = Frage A, 3 = Frage D
   *
   * @return
   */
  public boolean istAntwortRichtig(int antwort) {
    if (aktuelleFrage.gibRichtigeAntwort() == antwort) {
      return true;
    }
    return false;
  }

  /**
   * Beantworte die aktuelle Frage.
   *
   * @param antwort 0 = Frage A, 3 = Frage D
   * @return
   */
  public boolean beantworteFrage(int antwort) {
    beantworteteFragen.fügeHintenEin(aktuelleFrage);
    boolean ergebnis = aktuelleFrage.beantworteFrage(antwort);
    return ergebnis;
  }

  /**
   * Gib die aktuelle Fragennummer zurück. 1 ist die erste Fragennummer
   * und so weiter.
   *
   * @return
   */
  public int gibFragenNummer() {
    return frageNummer;
  }

  /**
   * Gib die Anzahl der noch unbeantwortete Fragen, die ins
   * Spiel geladen wurden zurück.
   *
   * @return
   */
  public int gibAnzahlUnbeantworterFragen() {
    return unbeantworteteFragen.gibAnzahlFragen();
  }

  /**
   * Gib die Anzahl der beantworteten Fragen zurück.
   *
   * @return
   */
  public int gibAnzahlBeantworterFragen() {
    return beantworteteFragen.gibAnzahlFragen();
  }
}
