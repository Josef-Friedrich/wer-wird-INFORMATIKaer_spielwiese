import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVReader {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = Files.newBufferedReader(Paths.get("./Fragen.csv"));
    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withIgnoreHeaderCase().withTrim());

    List<String> kopfNamen = csvParser.getHeaderNames();

    for (CSVRecord csvRecord : csvParser) {
      for (String kopfName : kopfNamen) {
        System.out.println(kopfName + " : " + csvRecord.get(kopfName));
      }
      System.out.println("\n");
    }
    csvParser.close();
  }
}
