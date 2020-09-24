package nuernberg.team.spiel;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * Die Klasse Datei versucht herauszufinden, ob sich der angebene Dateipfad im
 * resources-Ordner befindet ({@code "./src/main/resources"}) oder außerhalb
 * dieses Ordners liegt.
 *
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
   * Wahr, wenn die Datei bereits existiert. Falsch, wenn sie nicht existiert.
   */
  protected Boolean existiert;

  /**
   *
   * @param pfad Eine relativer Pfad (relative zum Ordner src/main/resources)
   */
  public Datei(String pfad) {
    this.pfad = pfad;
    existiert = false;

    try {
      versucheExternZuLesen();
      intern = false;
      existiert = true;
      return;
    } catch (IOException e) {
    }

    try {
      versucheInternZuLesen();
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
  private void versucheInternZuLesen() throws IOException {
    URL url = getClass().getResource(pfad);
    if (url == null) {
      throw new IOException();
    }
    Files.newBufferedReader(Paths.get(URI.create(url.toString())));
  }

  /**
   * Versucht eine externe Datei zu lesen.
   *
   * @throws IOException
   */
  private void versucheExternZuLesen() throws IOException {
    Files.newBufferedReader(Paths.get(pfad));
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
