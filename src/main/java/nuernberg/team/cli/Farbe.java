package nuernberg.team.cli;

/**
 * Stellt statische Methoden zur Verfügung, mit denen Text in der Kommandozeile
 * eingefärbt werden können.
 *
 * Der Text wird mit <a href=
 * "https://de.wikipedia.org/wiki/ANSI-Escapesequenz">ANSI-Escapesequenzen</a>
 * umgeben.
 */
public class Farbe {

  public static final String RESET = "\u001B[0m";
  public static final String SCHWARZ = "\u001B[30m";
  public static final String ROT = "\u001B[31m";
  public static final String GRÜN = "\u001B[32m";
  public static final String GELB = "\u001B[33m";
  public static final String BLAU = "\u001B[34m";
  public static final String LILA = "\u001B[35m";
  public static final String ZYAN = "\u001B[36m";
  public static final String WEISS = "\u001B[37m";

  /**
   * Wandelt das gegebene Objekt in einen String um und färbt es anschließend schwarz ein.
   *
   * @param objekt Ein beliebiges Objekt, das in einen String umgewandelt wird.
   *
   * @return Den eingefärbten Text.
   */
  public static String schwarz(Object objekt) {
    return SCHWARZ + objekt.toString() + RESET;
  }

  /**
   * Wandelt das gegebene Objekt in einen String um und färbt es anschließend rot ein.
   *
   * @param objekt Ein beliebiges Objekt, das in einen String umgewandelt wird.
   *
   * @return Den eingefärbten Text.
   */
  public static String rot(Object objekt) {
    return ROT + objekt.toString() + RESET;
  }

  /**
   * Wandelt das gegebene Objekt in einen String um und färbt es anschließend grün ein.
   *
   * @param objekt Ein beliebiges Objekt, das in einen String umgewandelt wird.
   *
   * @return Den eingefärbten Text.
   */
  public static String grün(Object objekt) {
    return GRÜN + objekt.toString() + RESET;
  }

  /**
   * Wandelt das gegebene Objekt in einen String um und färbt es anschließend gelb ein.
   *
   * @param objekt Ein beliebiges Objekt, das in einen String umgewandelt wird.
   *
   * @return Den eingefärbten Text.
   */
  public static String gelb(Object objekt) {
    return GELB + objekt.toString() + RESET;
  }

  /**
   * Wandelt das gegebene Objekt in einen String um und färbt es anschließend blau ein.
   *
   * @param objekt Ein beliebiges Objekt, das in einen String umgewandelt wird.
   *
   * @return Den eingefärbten Text.
   */
  public static String blau(Object objekt) {
    return BLAU + objekt.toString() + RESET;
  }

  /**
   * Wandelt das gegebene Objekt in einen String um und färbt es anschließend lila ein.
   *
   * @param objekt Ein beliebiges Objekt, das in einen String umgewandelt wird.
   *
   * @return Den eingefärbten Text.
   */
  public static String lila(Object objekt) {
    return LILA + objekt.toString() + RESET;
  }

  /**
   * Wandelt das gegebene Objekt in einen String um und färbt es anschließend zyan ein.
   *
   * @param objekt Ein beliebiges Objekt, das in einen String umgewandelt wird.
   *
   * @return Den eingefärbten Text.
   */
  public static String zyan(Object objekt) {
    return ZYAN + objekt.toString() + RESET;
  }

  /**
   * Wandelt das gegebene Objekt in einen String um und färbt es anschließend weiß ein.
   *
   * @param objekt Ein beliebiges Objekt, das in einen String umgewandelt wird.
   *
   * @return Den eingefärbten Text.
   */
  public static String weiß(Object objekt) {
    return WEISS + objekt.toString() + RESET;
  }

}
