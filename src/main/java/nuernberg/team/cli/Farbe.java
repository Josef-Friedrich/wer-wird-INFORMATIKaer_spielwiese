package nuernberg.team.cli;

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

  public static String schwarz(Object objekt) {
    return SCHWARZ + objekt.toString() + RESET;
  }

  public static String rot(Object objekt) {
    return ROT + objekt.toString() + RESET;
  }

  public static String grün(Object objekt) {
    return GRÜN + objekt.toString() + RESET;
  }

  public static String gelb(Object objekt) {
    return GELB + objekt.toString() + RESET;
  }

  public static String blau(Object objekt) {
    return BLAU + objekt.toString() + RESET;
  }

  public static String lila(Object objekt) {
    return LILA + objekt.toString() + RESET;
  }

  public static String cyan(Object objekt) {
    return ZYAN + objekt.toString() + RESET;
  }

  public static String weiß(Object objekt) {
    return WEISS + objekt.toString() + RESET;
  }

}
