package spiel;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;

import org.junit.Test;

public class XMLManagerTest {

  @Test
  public void testeKonstruktor() {
    URL url = Thread.currentThread().getContextClassLoader().getResource("spiel/fragen/musik/musik01.xml");
    File file = new File(url.getPath());
    XMLManager xml = new XMLManager(file);
    assertEquals("Musik", xml.gibFach());
  }

}
