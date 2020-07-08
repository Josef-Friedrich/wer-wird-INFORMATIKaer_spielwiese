package spiel;

import static org.junit.Assert.*;
import org.junit.Test;

public class FrageTest {

  private Frage frage;

  public FrageTest() {
    frage = new Frage("Frage1", "richtig", "falsch1", "falsch2", "falsch3", 1);
  }

  @Test
  public void testMethodeGibFragenText() {
    assertEquals(frage.gibFragenText(), "Frage1");
  }

  @Test
  public void testMethodeGibAntworten() {
    String[] antworten = frage.gibAntworten();
    assertEquals(antworten[0], "richtig");
    assertEquals(antworten[1], "falsch1");
    assertEquals(antworten[2], "falsch2");
    assertEquals(antworten[3], "falsch3");
  }
}
