package spiel;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;

import org.junit.Test;

public class ThemenGebietTest {

  @Test
  public void testeKonstruktor() {
    URL url = Thread.currentThread().getContextClassLoader().getResource("spiel/fragen/musik/musik01.xml");
    File file = new File(url.getPath());
    ThemenGebiet xml = new ThemenGebiet(file);
    assertEquals("Musik", xml.gibFach());
  }

}
