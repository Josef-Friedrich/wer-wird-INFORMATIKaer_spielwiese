package nuernberg.team.spiel;

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

  public ThemenGebiet(String pfad) throws Exception {
    super(pfad);
  }
  public ThemenGebiet() throws Exception {
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
      String falscheAntwort3, int schwierigkeit) {
    Element frage = dokument.createElement("frage");
    fragen.appendChild(frage);
    hängeTextElementAn(frage, "fragenText", fragenText);
    hängeTextElementAn(frage, "richtigeAntwort", richtigeAntwort);
    hängeTextElementAn(frage, "falscheAntwort1", falscheAntwort1);
    hängeTextElementAn(frage, "falscheAntwort2", falscheAntwort2);
    hängeTextElementAn(frage, "falscheAntwort3", falscheAntwort3);
    hängeTextElementAn(frage, "schwierigkeit", Integer.toString(schwierigkeit));
    fragenZähler++;
    return frage;
  }

  public void setzeFrage(String fragenText, String richtigeAntwort, String falscheAntwort1, String falscheAntwort2,
      String falscheAntwort3, int schwierigkeit, int schwierigkeitMin, int schwierigkeitMax) {
    Element frage = setzeFrage(fragenText, richtigeAntwort, falscheAntwort1, falscheAntwort2, falscheAntwort3,
        schwierigkeit);
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
      Node frage = knotenListe.item(i);

      String fragenText = gibTextVonKind(frage, "fragenText");
      String richtigeAntwort = gibTextVonKind(frage, "richtigeAntwort");
      String falscheAntwort1 = gibTextVonKind(frage, "falscheAntwort1");
      String falscheAntwort2 = gibTextVonKind(frage, "falscheAntwort2");
      String falscheAntwort3 = gibTextVonKind(frage, "falscheAntwort3");
      String schwierigkeit = gibTextVonKind(frage, "schwierigkeit");
      spiel.erzeugeFrage(fragenText, richtigeAntwort,
          falscheAntwort1, falscheAntwort2, falscheAntwort3,
          schwierigkeit);
    }
  }

  public static void main(String[] args) throws Exception {
    // ThemenGebiet schreiber = new ThemenGebiet();
    // schreiber.konvertiereCSV("./spiel/fragen/fragen.csv");
    ThemenGebiet themenGebiet = new ThemenGebiet("fragen/musik/musik01.xml");
    System.out.println(themenGebiet.gibTextVonFragenElement("fragenText", 1));
  }

}
