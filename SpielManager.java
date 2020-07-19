import spiel.Spiel;
import gui.GGSpielfeld;

/**
 * Hauptklasse, die die main Methode enthält.
 *
 * In dieser werden die zwei Pakete spiel und gui zusammengefügt.
 */
public class SpielManager {

  public static void main(String[] args) {
    Spiel spiel = new Spiel();

    CSVLeser leser = new CSVLeser(7);
    try {
      leser.leseInSpielEin(spiel);
    } catch (Exception e) {
      //TODO: handle exception
    }

    new GGSpielfeld(spiel);
  }
}
