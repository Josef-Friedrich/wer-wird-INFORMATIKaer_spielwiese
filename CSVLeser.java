import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import spiel.Spiel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVLeser {

  private CSVParser csvParser;

  public CSVLeser(String csvDatei) throws IOException {
    BufferedReader reader = Files.newBufferedReader(Paths.get(csvDatei));
    csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withIgnoreHeaderCase().withTrim());
  }

  private void leseZeileInSpielEin(Spiel spiel, CSVRecord csvRecord) {
    spiel.erzeugeFrage(csvRecord.get("fragenText"), csvRecord.get("richtigeAntwort"), csvRecord.get("falscheAntwort1"),
        csvRecord.get("falscheAntwort2"), csvRecord.get("falscheAntwort3"), csvRecord.get("schwierigkeit"));
  }

  /**
   * Liest alle Frage aus der CSV-Datei in die Klasse Spiel ein.
   *
   * @param spiel
   * @throws IOException
   */
  public void leseInSpielEin(Spiel spiel) throws IOException {
    for (CSVRecord csvRecord : csvParser) {
      leseZeileInSpielEin(spiel, csvRecord);
    }
    csvParser.close();
  }

  /**
   * Liest die Fragen einer Jahrgangsstufe in die Klasse Spiel ein.
   *
   * @param spiel
   * @param jahrgangsstufe
   * @throws IOException
   */
  public void leseInSpielEin(Spiel spiel, int jahrgangsstufe) throws IOException {
    for (CSVRecord csvRecord : csvParser) {
      if (Integer.parseInt(csvRecord.get("jahrgangsstufe")) == jahrgangsstufe) {
        leseZeileInSpielEin(spiel, csvRecord);
      }
    }
    csvParser.close();
  }
}
