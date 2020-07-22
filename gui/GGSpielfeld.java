package gui;

import ch.aplu.jgamegrid.*;
import java.awt.event.KeyEvent;
import spiel.Spiel;
import spiel.Frage;

public class GGSpielfeld extends GameGrid implements GGKeyListener {

  private GGTextAkteur frageText;
  private GGTextAkteur antwortAText;
  private GGTextAkteur antwortBText;
  private GGTextAkteur antwortCText;
  private GGTextAkteur antwortDText;

  private Spiel spiel;

  public GGSpielfeld(Spiel spiel) {

    // 1024 	× 	768
    super(52, 38, 20);
    setBgColor(0, 0, 255);
    this.spiel = spiel;
    addKeyListener(this);
    show();
  }

  public GGTextAkteur zeigeText(GGTextAkteur textBaustein, String text, int x, int y) {
    if (textBaustein != null) {
      textBaustein.hide();
      removeActor(textBaustein);
    }

    textBaustein = new GGTextAkteur(text);
    addMouseListener(textBaustein, GGMouse.lPress);
    addActor(textBaustein, new Location(x, y));

    return textBaustein;
  }

  public void zeigeFrage(Frage frage) {
    String frageTextnachricht = frage.gibFragenText();
    String[] antworten = frage.gibAntworten();
    frageText = zeigeText(frageText, frageTextnachricht, 2, 4);
    // 52 / 2 = 26
    // 0 1 [2] | 26 27 [28]
    antwortAText = zeigeText(antwortAText, "A: " + antworten[0], 2, 20);
    antwortBText = zeigeText(antwortBText, "B: " + antworten[1], 28, 20);

    antwortCText = zeigeText(antwortCText, "C: " + antworten[2], 2, 30);
    antwortDText = zeigeText(antwortDText, "D: " + antworten[3], 28, 30);
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
      Frage frage = spiel.entnimmFrage();
      frage.mischeAntworten();
      zeigeFrage(frage);
    }
    return false;
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
