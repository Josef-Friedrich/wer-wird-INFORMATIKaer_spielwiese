package spiel;

public class Spiel {

  private Jahrgangsstufe jahrgangsstufe;

  public Spiel() {
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
