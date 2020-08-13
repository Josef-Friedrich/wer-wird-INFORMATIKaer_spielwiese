import spiel.Spiel;
import spiel.CSVLeser;

import gui.GGSpielfeld;
import java.io.IOException;

/**
 * Hauptklasse, die die main Methode enthält.
 *
 * In dieser werden die zwei Pakete spiel und gui zusammengefügt.
 */
public class SpielManager {

  public static void main(String[] args) {
    Spiel spiel = new Spiel();

    try {
      CSVLeser leser = new CSVLeser("./Fragen.csv");
      leser.leseInSpielEin(spiel, 7);
    } catch (IOException e) {
      e.printStackTrace();
    }

    new GGSpielfeld(spiel);
  }
}
