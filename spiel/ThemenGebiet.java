package spiel;

import java.io.File;
import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Die Fragen für ein Themengebiet werden in einer XML-Datei festgehalten.
 */
public class ThemenGebiet extends XMLDatei {

  Element fragen;
  Element thema;
  Element autor;
  Element anzahlFragen;
  int fragenZähler = 0;
  Element fach;

  public ThemenGebiet(File datei) {
    super(datei);
  }

  public ThemenGebiet() {
    super("themenGebiet");
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
  }

  public void setzeFach(String fach) {
    erzeugeText(this.fach, fach);
  }

  public String gibFach() {
    return leseTextInhalt("fach");
  }

  public void setzeThema(String thema) {
    erzeugeText(this.thema, thema);
  }

  public String gibThema() {
    return leseTextInhalt("thema");
  }

  public void setzeAutor(String autor) {
    erzeugeText(this.autor, autor);
  }

  public String gibAutor() {
    return leseTextInhalt("autor");
  }

  public String gibTextVonFragenElement(String elementName, int frageNummer) throws XPathExpressionException  {
    return gibTextDurchXMLPfad(String.format("/themenGebiet/fragen/frage[%s]/%s", frageNummer, elementName));
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

  /**
   * Liest die Fragen einer Jahrgangsstufe in die Klasse Spiel ein.
   *
   * @param spiel
   * @param jahrgangsstufe
   * @throws IOException
   */
  public void leseFragenInsSpiel(Spiel spiel) {
    NodeList knotenListe = dokument.getElementsByTagName("frage");
    for (int i = 0; i < knotenListe.getLength(); i++) {
      NodeList fragenElemente = knotenListe.item(i).getChildNodes();
      // Die anderen Knoten 0 2 sind Text-Knoten, die nur die Leerzeichen aus der
      // XML-Datei enthalten.
      // Das ist ziemlich unschön und fragil.
      Node fragenText = fragenElemente.item(1);
      Node richtigeAntwort = fragenElemente.item(3);
      Node falscheAntwort1 = fragenElemente.item(5);
      Node falscheAntwort2 = fragenElemente.item(7);
      Node falscheAntwort3 = fragenElemente.item(9);
      Node schwierigkeit = fragenElemente.item(11);
      spiel.erzeugeFrage(fragenText.getTextContent(), richtigeAntwort.getTextContent(),
          falscheAntwort1.getTextContent(), falscheAntwort2.getTextContent(), falscheAntwort3.getTextContent(),
          schwierigkeit.getTextContent());
    }
  }

  public static void main(String[] args) throws IOException, XPathExpressionException {
    // ThemenGebiet schreiber = new ThemenGebiet();
    // schreiber.konvertiereCSV("./spiel/fragen/fragen.csv");

    ThemenGebiet themenGebiet = new ThemenGebiet(new File("spiel/fragen/musik/musik01.xml"));
    System.out.println(themenGebiet.gibTextVonFragenElement("fragenText", 1));
  }

}
