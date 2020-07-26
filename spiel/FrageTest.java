package spiel;

import static org.junit.Assert.*;
import org.junit.Test;

public class FrageTest {

  private Frage frage;

  public FrageTest() {
    frage = new Frage("Frage1", "richtig", "falsch1", "falsch2", "falsch3", 1);
  }

  @Test
  public void testeMethodeGibSchwierigkeit() {
    assertEquals(frage.gibSchwierigkeit(), 1);
  }

  @Test
  public void testeMethodeGibFragenText() {
    assertEquals(frage.gibFragenText(), "Frage1");
  }

  @Test
  public void testeMethodeGibAntworten() {
    String[] antworten = frage.gibAntworten();
    assertEquals(antworten[0], "richtig");
    assertEquals(antworten[1], "falsch1");
    assertEquals(antworten[2], "falsch2");
    assertEquals(antworten[3], "falsch3");
  }

  @Test
  public void testeMethodeGibRichtigAntwort() {
    assertEquals(frage.gibRichtigeAntwort(), 0);
  }

  @Test
  public void testeMethodeMischeAntwort() {
    Frage f = new Frage("Frage1", "richtig", "falsch1", "falsch2", "falsch3", 1);
    f.mischeAntworten();
    String[] antworten = f.gibAntworten();
    assertTrue(antworten[0] instanceof String);
    assertTrue(antworten[1] instanceof String);
    assertTrue(antworten[2] instanceof String);
    assertTrue(antworten[3] instanceof String);
  }
}
