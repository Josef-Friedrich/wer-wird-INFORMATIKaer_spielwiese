package nuernberg.team.spiel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ThemenGebietTest {

  @Test
  public void testeKonstruktor() throws Exception {
    ThemenGebiet gebiet = new ThemenGebiet("/fragen/musik/musik01.xml");
    assertEquals("Musik", gebiet.gibFach());
  }

  @Test
  public void testeMethodeGibTextVonFragenElement() throws Exception {
    ThemenGebiet gebiet = new ThemenGebiet("/fragen/musik/musik01.xml");
    assertEquals("Die Klarinette ist ein", gebiet.gibTextVonFragenElement("fragenText", 1));
  }

}
