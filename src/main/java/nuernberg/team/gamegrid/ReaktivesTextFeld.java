package nuernberg.team.gamegrid;

import ch.aplu.jgamegrid.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

public class ReaktivesTextFeld implements GGMouseListener {
  private GGTextField textFeld;

  private boolean istHauptTextSichtbar = true;

  private int linksObenX;
  private int linksObenY;
  private int rechtsUntenX;
  private int rechtsUntenY;

  private Color farbe;
  private Color schwebeFarbe;

  private GameGrid spielfeld;

  public ReaktivesTextFeld(GameGrid spielfeld, String text, Location platz, Color farbe, Color schwebeFarbe,
      Font schriftart) {
    textFeld = new GGTextField(spielfeld, text, platz, true);
    textFeld.setFont(schriftart);
    textFeld.setTextColor(farbe);
    textFeld.show();
    this.farbe = farbe;
    this.schwebeFarbe = schwebeFarbe;
    this.spielfeld = spielfeld;
  }

  public void initialisieren() {
    TextActor textAkteur = textFeld.getTextActor();

    int textHöhe = textAkteur.getTextHeight();
    int textWeite = textAkteur.getTextWidth();

    Point pixelPlatz = textAkteur.getPixelLocation();

    linksObenX = (int) pixelPlatz.getX();
    linksObenY = (int) pixelPlatz.getY() - textHöhe / 2;
    rechtsUntenX = linksObenX + textWeite;
    rechtsUntenY = (int) pixelPlatz.getY() + textHöhe / 2;

    GGBackground bg = spielfeld.getBg();
    bg.setPaintColor(new Color(255, 255, 0));
    bg.fillRectangle(new Point(linksObenX, linksObenY), new Point(rechtsUntenX, rechtsUntenY));
    spielfeld.addMouseListener(this, GGMouse.move);
  }

  public boolean mouseEvent(GGMouse mouse) {
    int x = mouse.getX();
    int y = mouse.getY();

    if (x >= linksObenX && x <= rechtsUntenX && y >= linksObenY && y <= rechtsUntenY) {
      if (istHauptTextSichtbar) {
        textFeld.setTextColor(schwebeFarbe);
        textFeld.show();
        istHauptTextSichtbar = false;
      }
    } else {
      if (!istHauptTextSichtbar) {
        textFeld.setTextColor(farbe);
        textFeld.show();
        istHauptTextSichtbar = true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    GameGrid gg = new GameGrid(10, 10, 60, Color.red);
    gg.show();

    ReaktivesTextFeld text3 = new ReaktivesTextFeld(gg, "A second example text.", new Location(1, 2),
        new Color(2, 2, 200), new Color(2, 255, 2), new Font("Sans", 0, 22));
    text3.initialisieren();
  }
}
