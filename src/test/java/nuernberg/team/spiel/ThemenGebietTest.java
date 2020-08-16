package nuernberg.team.spiel;

import static org.junit.Assert.assertEquals;

import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;

public class ThemenGebietTest {

  @Test
  public void testeKonstruktor() {
    ThemenGebiet gebiet = new ThemenGebiet("musik/musik01.xml");
    assertEquals("Musik", gebiet.gibFach());
  }

  @Test
  public void testeMethodeGibTextVonFragenElement() throws XPathExpressionException {
    ThemenGebiet gebiet = new ThemenGebiet("musik/musik01.xml");
    assertEquals("Die Klarinette ist ein", gebiet.gibTextVonFragenElement("fragenText", 1));
  }

}
