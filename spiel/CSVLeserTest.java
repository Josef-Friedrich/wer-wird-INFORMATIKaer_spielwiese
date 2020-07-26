package spiel;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class CSVLeserTest {

  @Test
  public void teste() {
    try {
      CSVLeser leser = new CSVLeser("./Fragen.csv");
      Spiel spiel = new Spiel();
      leser.leseInSpielEin(spiel);
      Frage frage = spiel.gibNächsteFrage();
      assertTrue(frage.gibFragenText() instanceof String);
    } catch (Exception e) {
      //TODO: handle exception
    }
  }
}
