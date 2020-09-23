package nuernberg.team.spiel;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

public class CSVLeserTest {

  @Test
  public void konstruktorInnerhalbResources() {
    CSVLeser csv = new CSVLeser("/fragen/fragen.csv");
    CSVParser leser = csv.gibLeser();
    CSVRecord datensatz = leser.iterator().next();
    assertEquals("Welche Wörter stecken hinter der Abkürzung HTTP?", datensatz.get("fragenText"));
  }

  @Test
  public void konstruktorAußerhalbResources() throws IOException {
    File tmpDatei = File.createTempFile("wwim", ".xml");
    Files.copy(getClass().getResourceAsStream("/fragen/fragen.csv"), tmpDatei.toPath(), StandardCopyOption.REPLACE_EXISTING);
    CSVLeser csv = new CSVLeser(tmpDatei.getAbsolutePath());
    CSVParser leser = csv.gibLeser();
    CSVRecord datensatz = leser.iterator().next();
    assertEquals("Welche Wörter stecken hinter der Abkürzung HTTP?", datensatz.get("fragenText"));
  }

}
