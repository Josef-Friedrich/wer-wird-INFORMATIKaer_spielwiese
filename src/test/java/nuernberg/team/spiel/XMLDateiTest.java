package nuernberg.team.spiel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLDateiTest {

  @Test
  public void methodeGibWurzel() throws Exception {
    XMLDatei datei = new XMLDatei("/fragen/musik/musik01.xml");
    Element wurzel = datei.gibWurzel();
    assertEquals("themenGebiet", wurzel.getTagName());
  }

  @Test
  public void methodeGibDokument() throws Exception {
    XMLDatei datei = new XMLDatei("/fragen/musik/musik01.xml");
    Document dokument = datei.gibDokument();
    assertEquals("#document", dokument.getNodeName());
  }

  @Test
  public void methodeGibTextVonFragenElement() throws Exception {
    XMLDatei datei = new XMLDatei("/fragen/musik/musik01.xml");
    Node knoten = datei.gibDokument().getElementsByTagName("frage").item(0);
    assertEquals("Die Klarinette ist ein", datei.gibTextVonKind(knoten, "fragenText"));
  }
}
