package nuernberg.team.spiel;

import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Die Klasse Datei versucht herauszufinden, ob sich der angebene Dateipfad im
 * resources-Ordner befindet ({@code "./src/main/resources"}) oder außerhalb
 * dieses Ordners liegt.
 */
public class Datei {

  /**
   * Der angebenene Dateipfad.
   */
  protected String pfad;

  /**
   * Wahr, wenn die Datei existiert und innerhalb des resources-Ordnern
   * ({@code "./src/main/resources"}) liegt. Falsch, wenn die Datei außerhalb
   * liegt.
   */
  protected Boolean intern;

  /**
   * @param pfad Eine interner Pfad (relativ zum Ordner
   *             {@code "src/main/resources"}) oder ein externer Pfad. Interne
   *             Pfade müssen immer mit einem Schrägstrich beginnen {@code "/"}).
   *             Externe Pfade können sowohl relativ als auch absolute sein.
   */
  public Datei(String pfad) {
    this.pfad = pfad;
    intern = istIntern() ? true : false;
  }

  /**
   * Überprüft, ob die XML-Datei im Lesemodus ist.
   *
   * Um die Datei im Lesemodus zu öffnen, muss die Datei existieren und darf nicht
   * leer sein.
   *
   * @return wahr, wenn sich die Datei im Lese-Modus befindet.
   */
  public boolean istIntern() {
    // Diese Zeile funktioniert nicht bei der Verwendung von jar Dateien.
    // return datei.exists() && datei.length() > 0;
    try {
      InputStream eingabeStrom = getClass().getResourceAsStream(pfad);
      if (eingabeStrom == null)
        return false;
      InputStreamReader isr = new InputStreamReader(eingabeStrom);
      BufferedReader br = new BufferedReader(isr);
      boolean ergebnis;
      if (br.readLine() != null)
        ergebnis = true;
      else
        ergebnis = false;
      eingabeStrom.close();
      isr.close();
      br.close();
      return ergebnis;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  public Boolean istBeschreibbar() {
    if (intern) return false;
    if ((new File(pfad)).length() == 0) {
      return true;
    }
    return false;
  }

  public Boolean istLesbar() {
    if (intern) return true;
    File datei = new File(pfad);
    if (datei.exists() && datei.length() > 0) {
      return true;
    }
    return false;
  }

  public BufferedReader gibBufferedReader() {
    BufferedReader dateiLeser = null;
    try {
      if (intern) {
        dateiLeser = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(pfad)));
      } else {
        dateiLeser = Files.newBufferedReader(new File(pfad).toPath());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return dateiLeser;
  }

  public InputStream gibInputStream() {
    InputStream eingabeStrom = null;
    try {
      if (intern) {
        eingabeStrom = getClass().getResourceAsStream(pfad);
      } else {
        eingabeStrom = new FileInputStream(pfad);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return eingabeStrom;
  }

  public File gibFile() {
    return new File(pfad);
  }
}
