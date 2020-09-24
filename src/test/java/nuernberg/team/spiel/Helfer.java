package nuernberg.team.spiel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@SuppressWarnings("rawtypes")
public class Helfer {
  public static void kopierteInterneDatei(Class klasse, String internerDateiPfad, File externeDatei) {
    try {
      Files.copy(klasse.getResourceAsStream(internerDateiPfad), externeDatei.toPath(),
          StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
