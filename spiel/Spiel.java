package spiel;

/**
 * Ein Spiel
 */
public class Spiel {

  /**
   * Die Gewinnsumme. Nach jeder beantworteten Frage wird diese
   * Summe verdoppelt. Im der deutschen Fernsehsendung wird nicht
   * immer verdoppelt, sondern manchmal ein Betrag gewählt, sodass
   * am Ende 1 Million gewonnen werden.
   */
  private int gewinnSumme;

  /**
   * Die aktuelle Fragenummer beginnend bei 1.
   */
  private int frageNummer;

  /**
   *
   */
  private Jahrgangsstufe jahrgangsstufe;

  public Spiel() {
    gewinnSumme = 50;
    jahrgangsstufe = new Jahrgangsstufe();
  }

  /**
   * Füge mehrere Fragen zufällig als zweidimensionales Feld ein.
   *
   * Die Methode wird an die gleichnamige Methode der Klasse
   * Jahrgangsstufe weitergegeben.
   *
   * @param fragen Mehrere Fragen als zweidimensionales Feld.
   */
  public void fügeFragenAlsFeldEin(String[][] fragen) {
    jahrgangsstufe.fügeFragenAlsFeldEin(fragen);
  }

  /**
   * Entnimme eine Frage. Diese Methode wird an die
   * Klasse {@link Jahrgangsstufe} weitergeleitet.
   *
   * @return
   */
  public Frage entnimmFrage() {
    return jahrgangsstufe.entnimmFrage();
  }
}
