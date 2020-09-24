package nuernberg.team.spiel;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Die Fragen für ein Themengebiet werden in einer XML-Datei festgehalten.
 *
 * <pre>
 * {@code
 * <?xml version="1.0" encoding="UTF-8" standalone="no"?>
 * <themenGebiet>
 *     <fach>Informatik</fach>
 *     <thema>6. Jahrgangsstufe</thema>
 *     <autor>Michi</autor>
 *     <anzahlFragen>10</anzahlFragen>
 *     <fragen>
 *         <frage>
 *             <fragenText>Wie nennt die Beziehung, wenn zwei Objekte der gleichen Klasse in Beziehung stehen?</fragenText>
 *             <richtigeAntwort>rekursive Beziehung</richtigeAntwort>
 *             <falscheAntwort1>repetetive Beziehung</falscheAntwort1>
 *             <falscheAntwort2>reflexive Beziehung</falscheAntwort2>
 *             <falscheAntwort3>relationale Beziehung</falscheAntwort3>
 *             <schwierigkeit>2</schwierigkeit>
 *         </frage>
 *     </fragen>
 * </themenGebiet>
 * }
 * </pre>
 */
public class ThemenGebiet extends XMLDatei {

  private Element fragen;
  private Element thema;
  private Element autor;
  private Element anzahlFragen;
  private Element fach;

  /**
   * Erzeugt anhand eines relativen Pfades zu einer XML-Datei eine neue Instanze
   * der Klasse.
   *
   * @param relativerPfad Relativer Pfad zum Elternverzeichnis
   *                      <code>./src/main/resources/fragen</code>. Beispielsweise
   *                      wird <code>informatik/6_jahrgangsstufe.xml</code> zu
   *                      <code>src/main/resources/fragen/informatik/6_jahrgangsstufe.xml</code>
   *                      ergänzt.
   */
  public ThemenGebiet(String pfad) {
    super(pfad);

    if (!existiert() || istLeer()) {
      setzeWurzel("themenGebiet");
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

  /**
   * Setze manuell die Anzahl der Fragen.
   *
   * @param anzahlFragen Die Anzahl der Fragen.
   */
  public void setzeAnzahlFragen(String anzahlFragen) {
    erzeugeText(this.anzahlFragen, anzahlFragen);
  }

  /**
   * Setze manuell die Anzahl der Fragen.
   *
   * @param anzahlFragen Die Anzahl der Fragen.
   */
  public void setzeAnzahlFragen(int anzahlFragen) {
    setzeAnzahlFragen(Integer.toString(anzahlFragen));
  }

  /**
   * Setzte die Anzahl der Fragen automatisch nach der Anzahl der
   * {@code<frage>}-Tags.
   */
  public void setzeAnzahlFragen() {
    erzeugeText(this.anzahlFragen, String.valueOf(gibAnzahlFragen()));
  }

  /**
   * Gibt die tatsächliche Anzahl der Fragen zurück.
   *
   * Diese Methode zählt die {@code<frage>}-Tags in der XML-Datei und gibt nicht
   * den Wert des Tags {@code<anzahlFragen>} zurück.
   *
   * @return Die Anzahl der Fragen.
   */
  public int gibAnzahlFragen() {
    return dokument.getElementsByTagName("frage").getLength();
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
    return frage;
  }

  public void setzeFrage(String fragenText, String richtigeAntwort, String falscheAntwort1, String falscheAntwort2,
      String falscheAntwort3, int schwierigkeit, int schwierigkeitMin, int schwierigkeitMax) {
    Element frage = setzeFrage(fragenText, richtigeAntwort, falscheAntwort1, falscheAntwort2, falscheAntwort3,
        schwierigkeit);
    hängeTextElementAn(frage, "schwierigkeitMin", Integer.toString(schwierigkeitMin));
    hängeTextElementAn(frage, "schwierigkeitMax", Integer.toString(schwierigkeitMax));
  }

  /**
   * Konvertiere die CSV-Datei mit den Fragen in eine XML-Datei. Dabei wird nach
   * der Jahrgangsstufe gefilter.
   *
   * @param pfad           Der Pfad zur CSV-Datei. Kann ein relativer Pfad zu den
   *                       resources-Ordner sein, z. B.
   *                       {@code"/fragen/fragen.csv"} oder ein relativer oder
   *                       absoluter Pfade außerhalb des resources-Ordner.
   * @param jahrgangsstufe Die Jahrgangsstufe, nach der gefilter werden soll.
   */
  public void konvertiereCSV(String pfad, int jahrgangsstufe) {
    try {
      CSVLeser csvLeser = new CSVLeser(pfad);
      CSVParser csv = csvLeser.gibLeser();
      setzeFach("Informatik");
      setzeThema(String.format("%s. Jahrgangsstufe", jahrgangsstufe));
      setzeAutor("Team Nürnberg");

      for (CSVRecord csvRecord : csv) {
        if (csvRecord.get("jahrgangsstufe").equals(Integer.toString(jahrgangsstufe))) {
          setzeFrage(csvRecord.get("fragenText"), csvRecord.get("richtigeAntwort"), csvRecord.get("falscheAntwort1"),
              csvRecord.get("falscheAntwort2"), csvRecord.get("falscheAntwort3"),
              Integer.parseInt(csvRecord.get("schwierigkeit")));
        }
      }
      csv.close();
      setzeAnzahlFragen();
      schreibeInDatei();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Liest die Fragen einer Jahrgangsstufe in die Klasse Spiel ein.
   *
   * @param spiel Eine Instanz der Klasse {@link Spiel}
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
      spiel.erzeugeFrage(fragenText, richtigeAntwort, falscheAntwort1, falscheAntwort2, falscheAntwort3, schwierigkeit);
    }
  }

  /**
   * Erzeuge für die Fragen einer bestimmen Jahrgangsstufe eine XML-Datei.
   *
   * @param jahrgangsstufe Die Jahrgangsstufe, nach der gefilter werden soll.
   */
  public static void erzeugeXmlPerJahrgang(int jahrgangsstufe) {
    String dateiPfad = System.getProperty("user.home") + "/" + jahrgangsstufe + "_jahrgangsstufe.xml";
    File datei = new File(dateiPfad);
    try {
      // Eine leere Datei erzeugen.
      datei.createNewFile();
      // Die XML leeren.
      new PrintWriter(dateiPfad).close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    ThemenGebiet gebiet = new ThemenGebiet(dateiPfad);
    gebiet.konvertiereCSV("/fragen/fragen.csv", jahrgangsstufe);
  }

  public static void main(String[] args) {
    erzeugeXmlPerJahrgang(6);
    erzeugeXmlPerJahrgang(7);
  }

}
