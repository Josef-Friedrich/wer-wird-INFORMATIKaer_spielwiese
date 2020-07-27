package cli;

import spiel.Spiel;
import spiel.Frage;
import spiel.CSVLeser;
import java.util.Scanner;
import java.io.IOException;

public class KommandoZeile {

  /**
   *
   * @param frage
   * @param antworten
   * @param antwortNr
   */
  private static void zeigeAntwort(Frage frage, String[] antworten, int antwortNr) {
    System.out.println(String.format("  %s: %s", frage.gibBuchstabe(antwortNr), antworten[antwortNr]));
  }

  /**
   *
   * @param spiel
   * @param frage
   */
  private static void stelleFrageAlsTextausgabe(Spiel spiel, Frage frage) {
    frage.mischeAntworten();
    String[] antworten = frage.gibAntworten();
    System.out.println(String.format("\n\nFrage Nr. %s: %s\n", spiel.gibFragenNummer(), frage.gibFragenText()));
    for (int i = 0; i < antworten.length; i++) {
      zeigeAntwort(frage, antworten, i);
    }
  }

  /**
   * @param eingabe a A: 0, b B: 1, c C: 2; d D: 3
   *
   * @return
   */
  private static int konvertiereAntwort(String eingabe) {
    eingabe = eingabe.toLowerCase();

    if (eingabe.equals("a")) {
      return 0;
    } else if (eingabe.equals("b")) {
      return 1;
    } else if (eingabe.equals("c")) {
      return 2;
    } else if (eingabe.equals("d")) {
      return 3;
    }
    return -1;
  }

  private static int frageNachAntwort(Scanner scanner) {
    System.out.print("\nDeine Antwort (a, b, c, d): ");
    String auswahl = scanner.next();
    return konvertiereAntwort(auswahl);
  }

  private static boolean frageNachWeiterspielen(Scanner scanner) {
    System.out.print("\nWeiter spielen, ja oder nein? (j, n): ");
    String eingabe = scanner.next();
    eingabe = eingabe.toLowerCase();
    if (eingabe.equals("j")) {
      return true;
    }
    return false;
  }

  private static void zeigeFragenErgebnis(Frage frage) throws Exception {
    String buchstabeRichtig = frage.gibBuchstabe(frage.gibRichtigeAntwort());
    String buchstabeAntwort = frage.gibBuchstabe(frage.gibGegebeneAntwort());
    if (frage.istRichtigBeantwortet()) {
      System.out.println(String.format("Die Antwort %s war richtig!", buchstabeAntwort));
    } else {
      System.out.println(String.format("Die Antwort %s war falsch! Richtig ist Antwort %s: %s", buchstabeAntwort,
          buchstabeRichtig, frage.gibRichtigeAntwortText()));
    }
  }

  private static void zeigeASCIILogo() {
    String[] zeilen = { "                         ", "        R    W   I       ", "    E               R    ",
        "  W                   D  ", "                         ", "                         ",
        " I n f o r m a t i k Ä R ", "                         ", "                         ",
        "  W                   D  ", "    E                R   ", "        R    W    I      ",
        "                         ", };
    System.out.println(String.join("\n", zeilen));
  }

  public static void main(String[] args) throws Exception, IOException {
    System.out.println("Willkommen bei „Wer wird INFORMATIKär (INFORMATIK-Millionär)");
    zeigeASCIILogo();

    System.out.print("In welcher Jahrgangsstufe bist du? (6 oder 7): ");
    Scanner scanner = new Scanner(System.in);
    int jahrgangsstufe = scanner.nextInt();

    System.out.println(String.format("Du bist in der %s. Jahrgangsstufe.", jahrgangsstufe));

    Spiel spiel = new Spiel();

    CSVLeser leser = new CSVLeser("./Fragen.csv");
    leser.leseInSpielEin(spiel, jahrgangsstufe);

    boolean nochImSpiel = true;

    while (nochImSpiel) {
      Frage frage = spiel.gibNächsteFrage();
      stelleFrageAlsTextausgabe(spiel, frage);
      int antwort = frageNachAntwort(scanner);
      boolean richtig = spiel.beantworteFrage(antwort);
      zeigeFragenErgebnis(frage);
      if (richtig) {
        nochImSpiel = frageNachWeiterspielen(scanner);
      } else {
        nochImSpiel = richtig;
      }
    }

    scanner.close();
  }
}
