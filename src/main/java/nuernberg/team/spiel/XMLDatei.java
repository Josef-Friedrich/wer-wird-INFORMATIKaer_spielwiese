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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
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

  protected Element wurzel;
  protected Document dokument;
  private XPath xPath;

  public XMLDatei (String pfad) {
    URL resource = getClass().getResource(pfad);
    File datei = new File(resource.getFile());
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      dokument = db.parse(datei);
      dokument.getDocumentElement().normalize();
      xPath = XPathFactory.newInstance().newXPath();
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
  }

  public XMLDatei(String pfad, String wurzelName) {
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

  public void schreibeInDatei(String dateiName) {
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource domSource = new DOMSource(dokument);
      StreamResult streamResult = new StreamResult(new File(dateiName));
      transformer.transform(domSource, streamResult);
    } catch (TransformerException e) {
      e.printStackTrace();
    }
  }

  public NodeList sucheXMLPfad(String ausdruck) throws XPathExpressionException {
    return (NodeList) xPath.compile(ausdruck).evaluate(dokument, XPathConstants.NODESET);
  }

  public String gibTextDurchXMLPfad(String xmlPfadAusdruck) throws XPathExpressionException {
    return (String) xPath.compile(String.format("%s/text()", xmlPfadAusdruck)).evaluate(dokument,
        XPathConstants.STRING);
  }

  public int zähleElemente(String xmlPfadAusdruck) throws XPathExpressionException {
    return ((Double) xPath.compile(String.format("count(%s)", xmlPfadAusdruck)).evaluate(dokument,
        XPathConstants.NUMBER)).intValue();
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
