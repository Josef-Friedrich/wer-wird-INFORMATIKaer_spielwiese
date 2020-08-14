package spiel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class CSVLeser {

  private CSVParser leser;

  public CSVLeser(String csvDatei) throws IOException {
    BufferedReader reader = Files.newBufferedReader(Paths.get(csvDatei));
    leser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withIgnoreHeaderCase().withTrim());
  }

  public CSVParser gibLeser() {
    return leser;
  }
}
