package nuernberg.team.spiel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ThemenGebietTest {

  @Test
  public void testeKonstruktor() throws Exception {
    ThemenGebiet gebiet = new ThemenGebiet("/fragen/musik/musik01.xml");
    assertEquals("Musik", gebiet.gibFach());
  }
}
