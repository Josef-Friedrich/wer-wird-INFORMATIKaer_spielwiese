package nuernberg.team.spiel;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class CSVLeser {

  private CSVParser leser;

  public CSVLeser(String csvDatei) {
    BufferedReader reader;
    try {
      // Zuerst ausprobieren, ob der Pfad außerhalb der JAR-Datei liegt.
      reader = Files.newBufferedReader(Paths.get(csvDatei));
      leser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withIgnoreHeaderCase().withTrim());
      return;
    } catch (IOException e) {
    }

    try {
      // Dann probieren, ob der Pfad innerhalb der JAR-Datei liegt.
      reader = Files.newBufferedReader(Paths.get(URI.create(getClass().getResource(csvDatei).toString())));
      leser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withIgnoreHeaderCase().withTrim());
    } catch (IOException e) {
    }
  }

  public CSVParser gibLeser() {
    return leser;
  }
}
