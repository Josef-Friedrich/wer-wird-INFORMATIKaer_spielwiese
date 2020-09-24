package nuernberg.team.spiel;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.Test;

public class DateiTest {

  private void kopierteInterneDatei(String internerDateiPfad, File externeDatei) {
    try {
      Files.copy(getClass().getResourceAsStream(internerDateiPfad), externeDatei.toPath(),
          StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void interneDatei() {
    Datei datei = new Datei("/fragen/fragen.csv");
    assertEquals(true, datei.istIntern());
    assertEquals(true, datei.existiert());

  }

  @Test
  public void externeDatei() throws IOException {
    File tmpDatei = File.createTempFile("wwim", ".csv");
    kopierteInterneDatei("/fragen/fragen.csv", tmpDatei);
    Datei datei = new Datei(tmpDatei.getAbsolutePath());
    assertEquals(false, datei.istIntern());
    assertEquals(true, datei.existiert());
  }

  @Test
  public void nichtExistierendeDatei() {
    Datei datei = new Datei("/a9e9ffe6-643d-4965-8ac7-1f57ce3e0b61/ea81b207-507b-4eb9-a5d8-6430d69caf7a.test");
    assertEquals(null, datei.istIntern());
    assertEquals(false, datei.existiert());
  }

}
