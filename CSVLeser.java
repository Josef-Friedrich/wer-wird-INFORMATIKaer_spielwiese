import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import spiel.Spiel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVLeser {

  private int jahrgangsstufe;

  public CSVLeser(int jahrgangsstufe) {
    this.jahrgangsstufe = jahrgangsstufe;
  }

  public void leseInSpielEin(Spiel spiel) throws IOException {
    BufferedReader reader = Files.newBufferedReader(Paths.get("./Fragen.csv"));
    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withIgnoreHeaderCase().withTrim());

    for (CSVRecord csvRecord : csvParser) {
      if (Integer.parseInt(csvRecord.get("jahrgangsstufe")) == jahrgangsstufe) {
        spiel.erzeugeFrage(csvRecord.get("fragenText"), csvRecord.get("richtigeAntwort"),
        csvRecord.get("falscheAntwort1"), csvRecord.get("falscheAntwort2"), csvRecord.get("falscheAntwort3"),
        csvRecord.get("schwierigkeit"));
      }
    }
    csvParser.close();
  }
}
