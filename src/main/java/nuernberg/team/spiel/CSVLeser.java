package nuernberg.team.spiel;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

/**
 * Liest eine CSV-Datei eine und gibt eine Instance der Klasse {@code CSVParser}
 * weiter.
 */
public class CSVLeser {

  private CSVParser leser;

  /**
   * @param csvDatei Der Pfad zu einer CSV-Datei. Es kann eine Datei außerhalb und
   *                 innerhalb des resources-Ordners sein. Ist die Datei innerhalb
   *                 des resources-Ordner dann muss er relativ sein und mit
   *                 {@code"/"} beginnen.
   */
  public CSVLeser(String csvDatei) {
    BufferedReader dateiLeser;
    try {
      // Zuerst ausprobieren, ob der Pfad außerhalb der JAR-Datei liegt.
      dateiLeser = Files.newBufferedReader(Paths.get(csvDatei));
      setzeLeser(dateiLeser);
      return;
    } catch (IOException e) {
    }

    try {
      // Dann probieren, ob der Pfad innerhalb der JAR-Datei liegt.
      dateiLeser = Files.newBufferedReader(Paths.get(URI.create(getClass().getResource(csvDatei).toString())));
      setzeLeser(dateiLeser);
    } catch (IOException e) {
    }
  }

  private void setzeLeser(BufferedReader dateiLeser) {
    try {
      leser = new CSVParser(dateiLeser, CSVFormat.DEFAULT.withHeader().withIgnoreHeaderCase().withTrim());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public CSVParser gibLeser() {
    return leser;
  }
}
