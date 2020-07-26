package spiel;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class FrageTest {

  private Frage frage;

  @Before
  public void erzeugeFrage() {
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
    frage.mischeAntworten();
    String[] antworten = frage.gibAntworten();
    assertTrue(antworten[0] instanceof String);
    assertTrue(antworten[1] instanceof String);
    assertTrue(antworten[2] instanceof String);
    assertTrue(antworten[3] instanceof String);
  }

  @Test
  public void testeMethodeBeantworteFrage() {
    assertTrue(frage.beantworteFrage(0));
    assertFalse(frage.beantworteFrage(1));
    assertFalse(frage.beantworteFrage(2));
    assertFalse(frage.beantworteFrage(3));
  }
}
