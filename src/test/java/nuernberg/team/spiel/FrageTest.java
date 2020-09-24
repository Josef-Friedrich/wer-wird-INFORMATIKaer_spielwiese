package nuernberg.team.spiel;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.lang.reflect.Field;

public class FrageTest {

  private Frage frage;

  @Before
  public void erzeugeFrage() {
    frage = new Frage("Frage1", "richtig", "falsch1", "falsch2", "falsch3", 1);
  }

  @Test
  public void methodeGibSchwierigkeit() {
    assertEquals(frage.gibSchwierigkeit(), 1);
  }

  @Test
  public void methodeGibFragenText() {
    assertEquals(frage.gibFragenText(), "Frage1");
  }

  @Test
  public void methodeGibAntworten() throws NoSuchFieldException, IllegalAccessException {
    String[] antworten = frage.gibAntworten();
    assertEquals(antworten[0], "richtig");
    assertEquals(antworten[1], "falsch1");
    assertEquals(antworten[2], "falsch2");
    assertEquals(antworten[3], "falsch3");

    Field feldAntworten = Frage.class.getDeclaredField("antworten");
    feldAntworten.setAccessible(true);
    String[] neueAntworten = { "eins", "zwei", "drei", "vier" };
    feldAntworten.set(frage, neueAntworten);

    antworten = frage.gibAntworten();
    assertEquals(antworten[0], "eins");
    assertEquals(antworten[1], "zwei");
    assertEquals(antworten[2], "drei");
    assertEquals(antworten[3], "vier");
    feldAntworten.setAccessible(false);
  }

  @Test
  public void methodeGibRichtigAntwort() {
    assertEquals(frage.gibRichtigeAntwort(), 0);
  }

  @Test
  public void methodeMischeAntwort() {
    frage.mischeAntworten();
    String[] antworten = frage.gibAntworten();
    assertTrue(antworten[0] instanceof String);
    assertTrue(antworten[1] instanceof String);
    assertTrue(antworten[2] instanceof String);
    assertTrue(antworten[3] instanceof String);
  }

  @Test
  public void methodeBeantworteFrage() {
    assertTrue(frage.beantworteFrage(0));
    assertFalse(frage.beantworteFrage(1));
    assertFalse(frage.beantworteFrage(2));
    assertFalse(frage.beantworteFrage(3));
  }
}
