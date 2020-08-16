package nuernberg.team.gui;

import nuernberg.team.spiel.Spiel;
import nuernberg.team.spiel.ThemenGebiet;
import nuernberg.team.spiel.ThemenKatalog;

/**
 * Hauptklasse, die die main Methode enthält.
 *
 * In dieser werden die zwei Pakete spiel und gui zusammengefügt.
 */
public class SpielManager {

  public static void main(String[] args) throws Exception {
    Spiel spiel = new Spiel();

    ThemenKatalog katalog = new ThemenKatalog();
    ThemenGebiet gebiet = katalog.gibGebietDurchNummer(0);
    gebiet.leseFragenInsSpiel(spiel);
    new GGSpielfeld(spiel);
  }
}
