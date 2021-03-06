package nuernberg.team.spiel;

/**
 * Ein Spiel
 */
public class Spiel {

  /**
   * Die Gewinnsumme. Nach jeder beantworteten Frage wird diese Summe verdoppelt.
   * Im der deutschen Fernsehsendung wird nicht immer verdoppelt, sondern manchmal
   * ein Betrag gewählt, sodass am Ende 1 Million gewonnen werden.
   */
  private int[] gewinnSumme = { 50, 100, 200, 300, 500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 125000, 50000,
      1000000 };

  /**
   * Die aktuelle Fragenummer beginnend bei 1.
   */
  private int frageNummer = 0;

  /**
   * Die aktuelle Frage, die gerade beantwortet wird.
   */
  private Frage aktuelleFrage;

  /**
   * Die aktuelle Schwierigkeit der ausgewählten Fragen. Die Schwierigkeit der
   * Fragen sollte steigen. 1 ist die leichte. 5 ist schwer.
   */
  private int schwierigkeit = 1;

  /**
   * Eine Liste der zu unbeantwortenden Fragen. Wird eine Frage beantwortet wird
   * sind in die Liste {@link beantworteteFragen} hinzugefügt.
   */
  private FragenListe unbeantworteteFragen;

  /**
   * Eine Liste der beantworteten Fragen. Die Liste ist nützlich um eine Übersicht
   * über die bereits gegeben Antworten zu erzeugen.
   */
  private FragenListe beantworteteFragen;

  /**
   * Wenn eine Frage falsch beantwortet wird, gilt das Spiel als verloren.
   */
  private boolean verloren = false;

  public Spiel() {
    unbeantworteteFragen = new FragenListe();
    beantworteteFragen = new FragenListe();
  }

  /**
   * Erzeuge eine neue Frage anhand von mehreren String-Argumenten und füge diese
   * Frage zufällig ein. Die Argumente können direkt aus dem CSVLeser eingelesen
   * werden.
   *
   * @param fragenText Der Text der Frage (Der eigentliche Fragensatz).
   * @param richtigeAntwort Die richtige Antwort.
   * @param falscheAntwort1 Die falsche Antwort Nr. 1.
   * @param falscheAntwort2 Die falsche Antwort Nr. 2.
   * @param falscheAntwort3 Die falsche Antwort Nr. 3.
   * @param schwierigkeit Die Schwierigkeit (1-5).
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
   * @return Die nächste Frage
   */
  public Frage gibNächsteFrage() {
    aktuelleFrage = unbeantworteteFragen.entnimmFrage(schwierigkeit);
    schwierigkeit++;
    frageNummer++;
    return aktuelleFrage;
  }

  /**
   * Überprüfe, ob die Antwort zur aktuellen Frage richtig oder flasch ist.
   *
   * @param antwort 0 = Frage A, 3 = Frage D
   *
   * @return Wahr, wenn die Frage richtig beantwortete wurde.
   */
  public boolean istAntwortRichtig(int antwort) {
    if (aktuelleFrage.gibRichtigeAntwort() == antwort) {
      return true;
    }
    return false;
  }

  public FragenListe gibBeantworteteFragen() {
    return beantworteteFragen;
  }

  /**
   * Beantworte die aktuelle Frage.
   *
   * @param antwort 0 = Frage A, 3 = Frage D
   *
   * @return Wahr, wenn die Frage richtig beantwortet wurde.
   */
  public boolean beantworteFrage(int antwort) {
    beantworteteFragen.fügeHintenEin(aktuelleFrage);
    boolean richtig = aktuelleFrage.beantworteFrage(antwort);
    verloren = !richtig;
    return richtig;
  }

  /**
   * Gib die aktuelle Fragennummer zurück. 1 ist die erste Fragennummer und so
   * weiter.
   *
   * @return Die Fragennummer. 1 ist die erste Fragennummer.
   */
  public int gibFragenNummer() {
    return frageNummer;
  }

  /**
   * Gib die Anzahl der noch unbeantworteten Fragen, die ins Spiel geladen wurden
   * zurück.
   *
   * @return Die Anzahl der noch unbeantworteten Fragen.
   */
  public int gibAnzahlUnbeantworterFragen() {
    return unbeantworteteFragen.gibAnzahlFragen();
  }

  /**
   * Gib die Anzahl der beantworteten Fragen zurück.
   *
   * @return Die Anzahl der beantworteten Fragen.
   */
  public int gibAnzahlBeantworterFragen() {
    return beantworteteFragen.gibAnzahlFragen();
  }

  /**
   * Gib die aktuelle Gewinnsumme aus.
   *
   * @return Die aktuelle Gewinnsumme.
   */
  public long gibGewinnSumme() {
    if (frageNummer == 0) {
      return 0;
    } else if (frageNummer <= 15) {
      return gewinnSumme[frageNummer - 1];
    } else {
      // Ist eine Million erreicht (bei der 15 Frage) verdoppeln wir
      // bei jeder neuen Frage:
      // 16. Frage 2.000.000
      // 17. Frage 4.000.000
      int potenz = frageNummer - 15;
      return Math.round(1000000 * Math.pow(2, potenz));
    }
  }

  /**
   * Zeige an, ob das Spiel gewonnen oder verloren wurde.
   *
   * @return Wahr, wenn das Spiel verloren wurde.
   */
  public boolean istVerloren() {
    return verloren;
  }

  /**
   * Lade ein Themengebiet ins Spiel.
   *
   * @param dateiPfad
   */
  public void ladeThemenGebiet(String dateiPfad) {
    ThemenGebiet gebiet = new ThemenGebiet(dateiPfad);
    gebiet.leseFragenInsSpiel(this);
  }
}
