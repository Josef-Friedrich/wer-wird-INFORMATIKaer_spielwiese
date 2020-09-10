package nuernberg.team.spiel;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Liefert einen vereinfachten Zugriff auf eine XML-Datei.
 */
public class XMLDatei {

  /**
   * Der relative Pfad zum „resources“-Ordner mit führendem
   * Schrägstrich.
   */
  protected String pfad;
  protected Element wurzel;
  protected Document dokument;

  public XMLDatei(String pfad) throws Exception {
    this.pfad = pfad;
    URL resource = getClass().getResource(pfad);
    if (resource == null) {
      throw new Exception("Pfad konnte nicht geladen werden: " + pfad);
    }
    File datei = new File(resource.getFile());
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      dokument = db.parse(datei);
      wurzel = dokument.getDocumentElement();
      wurzel.normalize();
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Eine neue XMLDatei beginnen.
   *
   * @param pfad
   * @param wurzelName
   */
  public XMLDatei(String pfad, String wurzelName) {
    this.pfad = pfad;
    try {
      DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
      dokument = documentBuilder.newDocument();
      wurzel = dokument.createElement(wurzelName);
      dokument.appendChild(wurzel);
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
  }

  public Element gibWurzel() {
    return wurzel;
  }

  public Document gibDokument() {
    return dokument;
  }

  public void schreibeInDatei() {
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource domSource = new DOMSource(dokument);
      StreamResult streamResult = new StreamResult(new File(pfad));
      transformer.transform(domSource, streamResult);
    } catch (TransformerException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gib den Textinhalt eines XML-Kind-Knoten zurück.
   *
   * @param elternKnoten Der übergeordnete Elternknoten: ein „fragen“-Knoten.
   * @param name         Der Name des Kindknoten z. B. „fragenText“ oder
   *                     „richtigeAntwort“.
   *
   * @return Der Textinhalt des Kind-Knotens.
   */
  public String gibTextVonKind(Node elternKnoten, String name) {
    Element element = (Element) elternKnoten;
    return element.getElementsByTagName(name).item(0).getTextContent();
  }

  /**
   * Lese den Text eines XML-Elements.
   *
   * @param elementName Der Name des XML-Elements z. b. thema, autor
   *
   * @return Der Textinhalt des Elements.
   */
  protected String leseTextInhalt(String elementName) {
    NodeList nodeList = dokument.getElementsByTagName(elementName);
    Node node = nodeList.item(0);
    if (node == null) {
      return "";
    } else {
      return node.getTextContent();
    }
  }

  protected void erzeugeText(Element element, String text) {
    element.appendChild(dokument.createTextNode(text));
  }

  protected Element erzeugeElementMitText(String elementName, String text) {
    Element element = dokument.createElement(elementName);
    erzeugeText(element, text);
    return element;
  }

  protected void hängeTextElementAn(Element element, String elementName, String text) {
    element.appendChild(erzeugeElementMitText(elementName, text));
  }

}
