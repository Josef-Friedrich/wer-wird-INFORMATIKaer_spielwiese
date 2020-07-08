package gui;

import ch.aplu.jgamegrid.*;
import java.awt.event.KeyEvent;
import spiel.Jahrgangsstufe;
import spiel.Frage;

public class GGSpielfeld extends GameGrid implements GGKeyListener {

  private GGTextAkteur frageText;
  private GGTextAkteur antwortAText;
  private GGTextAkteur antwortBText;
  private GGTextAkteur antwortCText;
  private GGTextAkteur antwortDText;

  private Jahrgangsstufe jahrgangsstufe;

  public GGSpielfeld(Jahrgangsstufe jahrgangsstufe) {
    super(10, 10, 60, java.awt.Color.RED);
    this.jahrgangsstufe = jahrgangsstufe;
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
}
