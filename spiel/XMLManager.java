package spiel;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLManager {

  Element wurzel;
  Document dokument;
  Element fragen;
  Element thema;
  Element autor;
  Element anzahlFragen;
  int fragenZähler = 0;
  Element fach;

  public XMLManager(File datei)  {
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      dokument = db.parse(datei);
      dokument.getDocumentElement().normalize();
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
  }

  public XMLManager() {
    try {
      DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
      dokument = documentBuilder.newDocument();
      wurzel = dokument.createElement("fragenKatalog");
      dokument.appendChild(wurzel);

      fach = dokument.createElement("fach");
      wurzel.appendChild(fach);

      thema = dokument.createElement("thema");
      wurzel.appendChild(thema);

      autor = dokument.createElement("autor");
      wurzel.appendChild(autor);

      anzahlFragen = dokument.createElement("anzahlFragen");
      wurzel.appendChild(anzahlFragen);

      fragen = dokument.createElement("fragen");
      wurzel.appendChild(fragen);
    } catch (ParserConfigurationException e) {
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

  private void erzeugeText(Element element, String text) {
    element.appendChild(dokument.createTextNode(text));
  }

  private Element erzeugeElementMitText(String elementName, String text) {
    Element element = dokument.createElement(elementName);
    erzeugeText(element, text);
    return element;
  }

  private void hängeTextElementAn(Element element, String elementName, String text) {
    element.appendChild(erzeugeElementMitText(elementName, text));
  }

  public void setzeFach(String fach) {
    erzeugeText(this.fach, fach);
  }

  public String gibFach() {
    return leseMetaDaten("fach");
  }

  public void setzeThema(String thema) {
    erzeugeText(this.thema, thema);
  }

  public String gibThema() {
    return leseMetaDaten("thema");
  }

  public void setzeAutor(String autor) {
    erzeugeText(this.autor, autor);
  }

  public String gibAutor() {
    return leseMetaDaten("autor");
  }

  public void setzeAnzahFragen(String anzahlFragen) {
    erzeugeText(this.anzahlFragen, anzahlFragen);
  }

  public void setzeAnzahFragen() {
    erzeugeText(this.anzahlFragen, String.valueOf(fragenZähler));
  }

  public Element setzeFrage(String fragenText, String richtigeAntwort, String falscheAntwort1, String falscheAntwort2,
      String falscheAntwort3, int schwierikeit) {
    Element frage = dokument.createElement("frage");
    fragen.appendChild(frage);
    hängeTextElementAn(frage, "fragenText", fragenText);
    hängeTextElementAn(frage, "richtigeAntwort", richtigeAntwort);
    hängeTextElementAn(frage, "falscheAntwort1", falscheAntwort1);
    hängeTextElementAn(frage, "falscheAntwort2", falscheAntwort2);
    hängeTextElementAn(frage, "falscheAntwort3", falscheAntwort3);
    hängeTextElementAn(frage, "schwierikeit", Integer.toString(schwierikeit));
    fragenZähler++;
    return frage;
  }

  public void setzeFrage(String fragenText, String richtigeAntwort, String falscheAntwort1, String falscheAntwort2,
      String falscheAntwort3, int schwierikeit, int schwierigkeitMin, int schwierigkeitMax) {
    Element frage = setzeFrage(fragenText, richtigeAntwort, falscheAntwort1, falscheAntwort2, falscheAntwort3,
        schwierikeit);
    hängeTextElementAn(frage, "schwierigkeitMin", Integer.toString(schwierigkeitMin));
    hängeTextElementAn(frage, "schwierigkeitMax", Integer.toString(schwierigkeitMax));
  }

  public void schreibeInDatei(String dateiName) {
    try {
      setzeAnzahFragen();
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

  public void konvertiereCSV(String pfad) {
    try {
      CSVLeser csvLeser = new CSVLeser(pfad);
      CSVParser csv = csvLeser.gibLeser();
      setzeFach("Informatik");
      setzeThema("Jahrgangsstufe");
      setzeAutor("Michi, Steffi, Josef, Martin");

      for (CSVRecord csvRecord : csv) {
        setzeFrage(csvRecord.get("fragenText"), csvRecord.get("richtigeAntwort"), csvRecord.get("falscheAntwort1"),
            csvRecord.get("falscheAntwort2"), csvRecord.get("falscheAntwort3"),
            Integer.parseInt(csvRecord.get("schwierigkeit")));
      }
      csv.close();
      schreibeInDatei("tmp.xml");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException {
    XMLManager schreiber = new XMLManager();
    schreiber.konvertiereCSV("./spiel/fragen/fragen.csv");
  }

}
