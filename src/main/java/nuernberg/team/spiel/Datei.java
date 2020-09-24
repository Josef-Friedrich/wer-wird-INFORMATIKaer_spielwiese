package nuernberg.team.spiel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * Die Klasse Datei versucht herauszufinden, ob sich der angebene Dateipfad im
 * resources-Ordner befindet ({@code "./src/main/resources"}) oder außerhalb
 * dieses Ordners liegt.
 */
public class Datei {

  /**
   * Der angebenene Dateipfad.
   */
  protected Path pfad;

  /**
   * Wahr, wenn die Datei existiert und innerhalb des resources-Ordnern
   * ({@code "./src/main/resources"}) liegt. Falsch, wenn die Datei außerhalb
   * liegt.
   */
  protected Boolean intern;

  /**
   * Wahr, wenn die Datei bereits existiert. Falsch, wenn sie nicht existiert.
   */
  protected Boolean existiert;

  /**
   * @param pfad Eine interner Pfad (relativ zum Ordner
   *             {@code "src/main/resources"}) oder ein externer Pfad. Interne
   *             Pfade müssen immer mit einem Schrägstrich beginnen {@code "/"}).
   *             Externe Pfade können sowohl relativ als auch absolute sein.
   */
  public Datei(String pfad) {
    existiert = false;

    try {
      versucheExternZuLesen(pfad);
      intern = false;
      existiert = true;
      return;
    } catch (IOException e) {
    }

    try {
      versucheInternZuLesen(pfad);
      intern = true;
      existiert = true;
    } catch (IOException e) {
    }
  }

  /**
   * Versucht eine interne Datei zu lesen.
   *
   * @throws IOException
   */
  private void versucheInternZuLesen(String pfad) throws IOException {
    URL url = getClass().getResource(pfad);
    if (url == null) {
      throw new IOException();
    }
    this.pfad = Paths.get(URI.create(url.toString()));
    Files.newBufferedReader(this.pfad);
  }

  /**
   * Versucht eine externe Datei zu lesen.
   *
   * @throws IOException
   */
  private void versucheExternZuLesen(String pfad) throws IOException {
    this.pfad = Paths.get(pfad);
    Files.newBufferedReader(this.pfad);
  }

  /**
   * @return Wahr, wenn es sich um eine interne Datei handelt, falsch, wenn die
   *         Datei einen externen Pfad hat, null wenn die Datei nicht existiert.
   */
  public Boolean istIntern() {
    return intern;
  }

  /**
   * @return Wahr, wenn die Datei existiert, falsch wenn sie nicht existiert.
   */
  public Boolean existiert() {
    return existiert;

  }
}
