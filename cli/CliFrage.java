package cli;

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
}
