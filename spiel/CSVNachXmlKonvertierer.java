package spiel;

import java.io.IOException;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVNachXmlKonvertierer {
  public static void main(String[] args) throws IOException {
    CSVLeser csvLeser = new CSVLeser("./spiel/fragen/fragen.csv");
    CSVParser csv = csvLeser.gibLeser();

    XMLSchreiber xmlSchreiber = new XMLSchreiber();

    xmlSchreiber.setzeFach("Informatik");
    xmlSchreiber.setzeThema("Jahrgangsstufe");
    xmlSchreiber.setzeAutor("Michi, Steffi, Josef, Martin");

    for (CSVRecord csvRecord : csv) {
      xmlSchreiber.setzeFrage(csvRecord.get("fragenText"), csvRecord.get("richtigeAntwort"),
          csvRecord.get("falscheAntwort1"), csvRecord.get("falscheAntwort2"), csvRecord.get("falscheAntwort3"),
          Integer.parseInt(csvRecord.get("schwierigkeit")));
    }
    csv.close();

    xmlSchreiber.schreibeInDatei("tmp.xml");
  }

}
