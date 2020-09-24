package nuernberg.team.spiel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

/**
 * Liest eine CSV-Datei eine und gibt eine Instance der Klasse {@code CSVParser}
 * weiter.
 */
public class CSVLeser extends Datei {

  private CSVParser leser;

  /**
   * @param csvDatei Der Pfad zu einer CSV-Datei. Es kann eine Datei außerhalb und
   *                 innerhalb des resources-Ordners sein. Ist die Datei innerhalb
   *                 des resources-Ordner dann muss er relativ sein und mit
   *                 {@code "/"} beginnen.
   */
  public CSVLeser(String csvDatei) {
    super(csvDatei);
    BufferedReader dateiLeser;
    try {
      dateiLeser = Files.newBufferedReader(pfad);
      leser = new CSVParser(dateiLeser, CSVFormat.DEFAULT.withHeader().withIgnoreHeaderCase().withTrim());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public CSVParser gibLeser() {
    return leser;
  }
}
