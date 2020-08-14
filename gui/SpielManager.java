package gui;

import spiel.Spiel;
import spiel.ThemenGebiet;
import spiel.ThemenKatalog;

/**
 * Hauptklasse, die die main Methode enthält.
 *
 * In dieser werden die zwei Pakete spiel und gui zusammengefügt.
 */
public class SpielManager {

  public static void main(String[] args) {
    Spiel spiel = new Spiel();

    ThemenKatalog katalog = new ThemenKatalog();
    ThemenGebiet gebiet = katalog.gibGebietDurchNummer(0);
    gebiet.leseFragenInsSpiel(spiel);
    new GGSpielfeld(spiel);
  }
}
