package spiel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class XMLLeserTest {


  @Test
  public void testeKonstruktor() {
    XMLLeser leser = new XMLLeser("./fragen/musik/musik01.xml");
    assertEquals("Allgemein", leser.fach);

  }

}
