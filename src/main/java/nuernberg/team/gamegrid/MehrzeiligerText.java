package nuernberg.team.gamegrid;

import ch.aplu.jgamegrid.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

/**
 * Ein mehrzeiliger Text.
 */
public class MehrzeiligerText {
  private int textWeite = 40;
  private int y;
  private GameGrid gameGrid;
  private Location location;
  private ArrayList<TextActor> zeilen = new ArrayList<TextActor>();

  private Color textFarbe = new Color(255, 255, 255);
  private Color hintergrundFarbe = new Color(255, 255, 255, 0);
  private Font schriftart = new Font("Sans", Font.PLAIN, 20);

  public MehrzeiligerText(String text) {
    this.teileText(text);
  }

  public MehrzeiligerText(String text, Color textFarbe, Font schriftart) {
    this.textFarbe = textFarbe;
    this.schriftart = schriftart;
    this.teileText(text);
  }

  private void teileText(String text) {
    String[] wörter = text.split("[ \t\n\r]+");
    String textEineZeile = "";

    for (int i = 0; i < wörter.length; i++) {
      if (textEineZeile.length() > textWeite) {
        erzeugeTextAkteur(textEineZeile);
        textEineZeile = "";
      }
      textEineZeile = textEineZeile + wörter[i] + " ";
    }
    erzeugeTextAkteur(textEineZeile);
  }

  private TextActor erzeugeTextAkteur(String text) {
    TextActor textActor = new TextActor(text, textFarbe, hintergrundFarbe, schriftart);
    zeilen.add(textActor);
    return textActor;
  }

  private void setzeEineTextzeile(TextActor eineZeile) {
    gameGrid.addActor(eineZeile, new Location(location.getX(), y));
    y = y + 1;
  }

  public void setzeImSpielfeld(GameGrid gameGrid, Location location) {
    this.gameGrid = gameGrid;
    this.location = location;
    y = location.getY();
    for (TextActor eineZeile : zeilen) {
      setzeEineTextzeile(eineZeile);
    }
  }

  public void entferneVomSpielfeld() {
    for (TextActor eineZeile : zeilen) {
      eineZeile.removeSelf();
    }

  }

  public static void main(String[] args) {
    MehrzeiligerText text = new MehrzeiligerText(
        "Ein ganz langer Text, der nicht in eine Zeile passt ..! Ein ganz langer Text, der nicht in eine Zeile passt ..! Ein ganz langer Text, der nicht in eine Zeile passt ..! Ein ganz langer Text, der nicht in eine Zeile passt ..!");

    GameGrid gg = new GameGrid(10, 10, 60, Color.red);
    gg.show();

    text.setzeImSpielfeld(gg, new Location(1, 1));
  }
}
