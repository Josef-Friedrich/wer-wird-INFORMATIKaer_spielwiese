import spiel.Frage;
import spiel.Spiel;
import spiel.Jahrgangsstufe;

public class SpielManager {

  public static void main(String[] args) {
    Spiel spiel = new Spiel();

    String[][] fragen = {
      {"Frage1", "richtig", "falsch1", "falsch2", "falsch3", "1"},
      {"Frage2", "richtig", "falsch1", "falsch2", "falsch3", "2"},
      {"Frage3", "richtig", "falsch1", "falsch2", "falsch3", "3"},
      {"Frage4", "richtig", "falsch1", "falsch2", "falsch3", "4"},
      {"Frage5", "richtig", "falsch1", "falsch2", "falsch3", "5"},
      {"Frage6", "richtig", "falsch1", "falsch2", "falsch3", "1"},
      {"Frage7", "richtig", "falsch1", "falsch2", "falsch3", "2"},
      {"Frage8", "richtig", "falsch1", "falsch2", "falsch3", "3"},
      {"Frage9", "richtig", "falsch1", "falsch2", "falsch3", "4"},
      {"Frage10", "richtig", "falsch1", "falsch2", "falsch3", "5"},
    };

    spiel.jahrgangsstufe = new Jahrgangsstufe();
  }
}
