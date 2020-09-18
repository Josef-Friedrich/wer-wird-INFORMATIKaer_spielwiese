package nuernberg.team.spiel;

import static org.junit.Assert.assertEquals;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

public class CSVLeserTest {

  @Test
  public void konstruktor() {
    CSVLeser csv = new CSVLeser("/fragen/fragen.csv");
    CSVParser leser = csv.gibLeser();
    CSVRecord datensatz = leser.iterator().next();
    assertEquals("Welche Wörter stecken hinter der Abkürzung HTTP?", datensatz.get("fragenText"));
  }

}
