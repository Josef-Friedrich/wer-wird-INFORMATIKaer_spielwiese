package spiel;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;

import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;

public class ThemenGebietTest {

  File testDatei;

  public ThemenGebietTest() {
    URL url = Thread.currentThread().getContextClassLoader().getResource("spiel/fragen/musik/musik01.xml");
    testDatei = new File(url.getPath());
  }

  @Test
  public void testeKonstruktor() {
    ThemenGebiet gebiet = new ThemenGebiet(testDatei);
    assertEquals("Musik", gebiet.gibFach());
  }

  @Test
  public void testeMethodeGibTextVonFragenElement() throws XPathExpressionException {
    ThemenGebiet gebiet = new ThemenGebiet(testDatei);
    assertEquals("Die Klarinette ist ein", gebiet.gibTextVonFragenElement("fragenText", 1));
  }

}
