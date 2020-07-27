package cli;
import java.util.Scanner;

import spiel.Frage;

public class CliFrage {

  private Frage frage;
  private String[] antworten;

  public CliFrage(Frage frage) {
    this.frage = frage;
  }

  private void gibAntwortAus(int antwortNr) {
    System.out.println(String.format("  %s: %s", frage.gibBuchstabe(antwortNr), antworten[antwortNr]));
  }

  public void stelleFrageAlsTextausgabe() {
    frage.mischeAntworten();
    antworten = frage.gibAntworten();
    System.out.println(String.format("\n\n%s\n", frage.gibFragenText()));
    for (int i = 0; i < antworten.length; i++) {
      gibAntwortAus(i);
    }
  }

  /**
   * @param eingabe a A: 0, b B: 1, c C: 2; d D: 3
   *
   * @return
   */
  public int konvertiereAntwort(String eingabe) {
    eingabe = eingabe.toLowerCase();

    if (eingabe.equals("a")) {
      return 0;
    } else if (eingabe.equals("b")) {
      return 1;
    } else if (eingabe.equals("c")) {
      return 2;
    } else if (eingabe.equals("d")) {
      return 3;
    }
    return -1;
  }

  public int holeAntwort(Scanner scanner) {
    System.out.print("\nDeine Antwort: ");
    String auswahl = scanner.next();
    return konvertiereAntwort(auswahl);
  }
}
