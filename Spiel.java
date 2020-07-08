import ch.aplu.jgamegrid.*;
import java.awt.event.KeyEvent;

public class Spiel extends GameGrid implements GGKeyListener {

  private GGTextAkteur frageText;
  private GGTextAkteur antwortAText;
  private GGTextAkteur antwortBText;
  private GGTextAkteur antwortCText;
  private GGTextAkteur antwortDText;

  private Jahrgangsstufe jahrgangsstufe;

  public Spiel() {
    super(10, 10, 60, java.awt.Color.RED);
    addKeyListener(this);
    show();
  }

  public GGTextAkteur zeigeText(GGTextAkteur textBaustein, String text, int x, int y) {
    if (textBaustein != null) {
      textBaustein.hide();
      removeActor(textBaustein);
    }

    textBaustein = new GGTextAkteur(text);
    addActor(textBaustein, new Location(x, y));

    return textBaustein;
  }

  public void zeigeFrage(Frage frage) {
    String frageTextnachricht = frage.gibFragenText();
    String[] antworten = frage.gibAntworten();
    frageText = zeigeText(frageText, frageTextnachricht, 2, 4);
    antwortAText = zeigeText(antwortAText, "A: " + antworten[0], 1, 6);
    antwortBText = zeigeText(antwortBText, "B: " + antworten[1], 1, 8);

    antwortCText = zeigeText(antwortCText, "C: " + antworten[2], 6, 6);
    antwortDText = zeigeText(antwortDText, "D: " + antworten[3], 6, 8);
  }

  /**
   * Diese Methode muss implementiert sein, da das Interface
   * GGKeyListener es verlangt. Wir geben false zurück, damit weitere
   * Klassen, die das Interface benutzen das Drücken von Tasten
   * empfangen können.
   *
   * @see <a href="http://www.aplu.ch/classdoc/jgamegrid/ch/aplu/jgamegrid/GGKeyListener.html">
   *   Dokumentation des Interfaces</a>
   */
  public boolean keyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
      Frage frage = jahrgangsstufe.entnimmFrage();
      frage.mischeAntworten();
      zeigeFrage(frage);
    }
    return false; // Don't consume
  }

  /**
   * Diese Methode muss implementiert sein, da das Interface
   * GGKeyListener es verlangt. Wir geben false zurück, damit weitere
   * Klassen, die das Interface benutzen das Drücken von Tasten
   * empfangen können.
   *
   * @see <a href="http://www.aplu.ch/classdoc/jgamegrid/ch/aplu/jgamegrid/GGKeyListener.html">
   *   Dokumentation des Interfaces</a>
   */
  public boolean keyReleased(KeyEvent evt) {
    return false;
  }

  public static void main(String[] args) {

    Spiel spiel = new Spiel();

    Frage frage1 = new Frage("Frage1", "richtig", "falsch1", "falsch2", "falsch3", 1);
    Frage frage2 = new Frage("Frage2", "richtig", "falsch1", "falsch2", "falsch3", 2);
    Frage frage3 = new Frage("Frage3", "richtig", "falsch1", "falsch2", "falsch3", 3);
    Frage frage4 = new Frage("Frage4", "richtig", "falsch1", "falsch2", "falsch3", 4);
    Frage frage5 = new Frage("Frage5", "richtig", "falsch1", "falsch2", "falsch3", 5);
    Frage frage6 = new Frage("Frage6", "richtig", "falsch1", "falsch2", "falsch3", 1);
    Frage frage7 = new Frage("Frage7", "richtig", "falsch1", "falsch2", "falsch3", 2);
    Frage frage8 = new Frage("Frage8", "richtig", "falsch1", "falsch2", "falsch3", 3);
    Frage frage9 = new Frage("Frage9", "richtig", "falsch1", "falsch2", "falsch3", 4);
    Frage frage10 = new Frage("Frage10", "richtig", "falsch1", "falsch2", "falsch3", 5);

    spiel.jahrgangsstufe = new Jahrgangsstufe();

    spiel.jahrgangsstufe.fügeZufälligEin(frage1);
    spiel.jahrgangsstufe.fügeZufälligEin(frage2);
    spiel.jahrgangsstufe.fügeZufälligEin(frage3);
    spiel.jahrgangsstufe.fügeZufälligEin(frage4);
    spiel.jahrgangsstufe.fügeZufälligEin(frage5);
    spiel.jahrgangsstufe.fügeZufälligEin(frage6);
    spiel.jahrgangsstufe.fügeZufälligEin(frage7);
    spiel.jahrgangsstufe.fügeZufälligEin(frage8);
    spiel.jahrgangsstufe.fügeZufälligEin(frage9);
    spiel.jahrgangsstufe.fügeZufälligEin(frage10);
  }
}
