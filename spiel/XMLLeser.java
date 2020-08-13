package spiel;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import org.xml.sax.SAXException;

public class XMLLeser {

  Document dokument;
  public String fach;
  public String thema;
  public String author;

  public XMLLeser(String pfad)  {
    try {
      File file = new File(pfad);
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      dokument = db.parse(file);
      dokument.getDocumentElement().normalize();
      fach = leseMetaDaten("fach");
      thema = leseMetaDaten("thema");
      author = leseMetaDaten("autor");
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Lese die Metadaten des Fragen-Katalogs. Metadaten sind direkt
   * unter dem Wurzel-Element fragenKatalog angehängt und haben nur
   * einen Text-Inhalt.
   *
   * @param elementName Der Name des XML-Elements z. b. thema, autor
   *
   * @return Der Textinhalt des Elements.
   */
  public String leseMetaDaten(String elementName) {
    NodeList nodeList = dokument.getElementsByTagName(elementName);
    Node node = nodeList.item(0);
    if (node == null) {
      return "";
    } else {
      return node.getTextContent();
    }
  }

  public static void main(String[] args) {
    XMLLeser leser = new XMLLeser("fragen/musik/musik01.xml");
    System.out.println(leser.fach);
  }

}
