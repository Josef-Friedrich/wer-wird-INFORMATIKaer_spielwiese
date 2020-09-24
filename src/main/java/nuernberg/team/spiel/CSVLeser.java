package nuernberg.team.spiel;

import java.io.IOException;

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
    try {
      leser = new CSVParser(gibBufferedReader(), CSVFormat.DEFAULT.withHeader().withIgnoreHeaderCase().withTrim());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public CSVParser gibLeser() {
    return leser;
  }
}
