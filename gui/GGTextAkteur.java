package gui;

import ch.aplu.jgamegrid.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

/**
 * @see <a href="http://www.aplu.ch/home/apluhomex.jsp?site=51">JGameGrid Lesson 6: Ex07c: The Better World: GGKeyListener</a>
 *
 * @see <a href="http://www.aplu.ch/classdoc/jgamegrid/ch/aplu/jgamegrid/TextActor.html">Dokumentation der Kasse TextActor</a>
 * @see <a href="http://www.aplu.ch/classdoc/jgamegrid/ch/aplu/jgamegrid/GGKeyListener.html">Dokumentation der Interfaces GGKeyListener</a>
 **/
public class GGTextAkteur extends TextActor implements GGKeyListener, GGMouseListener {

  public GGTextAkteur(String text) {
    super(false, text, Color.WHITE, new Color(255, 255, 255, 0), new Font("Sans", Font.PLAIN, 20));
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

  public boolean mouseEvent(GGMouse mouse)
  {
    Location location =
      gameGrid.toLocationInGrid(mouse.getX(), mouse.getY());
    if (location.equals(getLocation())) {
      System.out.println("test");
    }

    gameGrid.refresh();
    return false;
  }
}
