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
   * Der relative Pfad zum „resources“-Ordner mit führendem Schrägstrich.
   */
  protected File datei;
  protected Element wurzel;
  protected Document dokument;

  /**
   *
   * @param relativerPfad Eine relativer Pfad (relative zum Ordner
   *                      src/main/resources)
   * @throws Exception
   */
  public XMLDatei(String relativerPfad) throws Exception {
    URL resource = getClass().getResource(relativerPfad);
    if (resource == null) {
      throw new Exception("Pfad konnte nicht geladen werden: " + relativerPfad);
    }
    datei = new File(resource.getFile());
    initialisiere(datei);
  }

  /**
   * Eine neue XMLDatei beginnen.
   *
   * @param datei Existiert die Datein noch nicht, wird eine neue Datei angelegt.
   *              Existierte sie, so wird sie gelesen.
   */
  public XMLDatei(File datei) {
    initialisiere(datei);
  }

  /**
   * Initialisiere die XML-Datei.
   *
   * @param datei Existiert die Datein noch nicht, wird eine neue Datei angelegt.
   *              Existierte sie, so wird sie gelesen.
   */
  private void initialisiere(File datei) {
    this.datei = datei;
    try {
      DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      if (istImLeseModus()) {
        dokument = documentBuilder.parse(datei);
        wurzel = dokument.getDocumentElement();
        wurzel.normalize();
      } else {
        dokument = documentBuilder.newDocument();
      }
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Überprüft, ob die XML-Datei im Lesemodus ist.
   *
   * Um die Datei im Lesemodus zu öffnen, muss die Datei existieren und
   * darf nicht leer sein.
   *
   * @return wahr, wenn sich die Datei im Lese-Modus befindet.
   */
  private boolean istImLeseModus() {
    return datei.exists() && datei.length() > 0;
  }

  public Element gibWurzel() {
    return wurzel;
  }

  public void setzeWurzel(String wurzelName) {
    if (!istImLeseModus()) {
      wurzel = dokument.createElement(wurzelName);
      dokument.appendChild(wurzel);
    }
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
      StreamResult streamResult = new StreamResult(datei);
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
