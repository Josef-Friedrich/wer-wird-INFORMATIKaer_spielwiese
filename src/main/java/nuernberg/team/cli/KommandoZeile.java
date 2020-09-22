package nuernberg.team.cli;

import nuernberg.team.spiel.Spiel;
import nuernberg.team.spiel.ThemenGebiet;
import nuernberg.team.spiel.ThemenKatalog;

import nuernberg.team.spiel.Frage;
import nuernberg.team.spiel.FragenListe;
import nuernberg.team.spiel.ListenElement;

import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
    System.out.println(Farbe.gelb(String.format("\n\nFrage Nr. %s: %s\n", spiel.gibFragenNummer(), frage.gibFragenText())));
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

  /**
   * @param scanner
   *
   * @return
   */
  private static int frageNachAntwort(Scanner scanner) {
    System.out.print("\nDeine Antwort (a, b, c, d): ");
    String auswahl = scanner.next();
    return konvertiereAntwort(auswahl);
  }

  /**
   * Frage in der Kommandozeile, ob weitergespielt werden soll oder nicht. Es
   * können sowohl Klein- als auch Großbuchstaben eingegeben werden. Wird nichts
   * eingegeben, dann bedeutet das ja.
   *
   * @param scanner
   *
   * @return
   */
  private static boolean frageNachWeiterspielen() throws IOException {
    System.out.print("Weiter spielen, ja oder nein? (J, n): ");
    // https://stackoverflow.com/a/1323799/10193818
    // Hier kann nicht der Scanner verwendet werden, weil wir die
    // Möglichkeite haben wollen eine Standardeingabe zu bekommen, d. h.
    // gibt der Benutzer nichts ein und drückt Return, dann soll das für
    // Ja stehen.
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    String eingabe = input.readLine();
    eingabe = eingabe.trim();
    eingabe = eingabe.toLowerCase();
    if (eingabe.equals("") || eingabe.equals("j")) {
      return true;
    }
    return false;
  }

  /**
   * @param frage
   *
   * @return
   */
  private static void zeigeFragenErgebnis(Spiel spiel, Frage frage) throws Exception {
    String buchstabeRichtig = frage.gibBuchstabe(frage.gibRichtigeAntwort());
    String buchstabeAntwort = frage.gibBuchstabe(frage.gibGegebeneAntwort());
    if (frage.istRichtigBeantwortet()) {
      System.out.println(Farbe.grün(String.format("Die Antwort %s war richtig!", buchstabeAntwort)));
      System.out.println(String.format("Deine momentane Gewinnsumme: %s €", spiel.gibGewinnSumme()));
    } else {
      System.out.print(Farbe.rot(String.format("Die Antwort %s war falsch! ", buchstabeAntwort)));
      System.out.println(String.format("Richtig wäre Antwort %s gewesen: %s", Farbe.grün(buchstabeRichtig),
          Farbe.grün(frage.gibRichtigeAntwortText())));
    }
  }

  /**
   * Zeige ein Logo in ASCII art.
   */
  private static void zeigeASCIILogo() {
    String[] zeilen = { "                         ", "        R    W   I       ", "    E               R    ",
        "  W                   D  ", "                         ", "                         ",
        " I n f o r m a t i k Ä R ", "                         ", "                         ",
        "  W                   D  ", "    E                R   ", "        R    W    I      ",
        "                         ", };
    System.out.println(String.join("\n", zeilen));
  }

  /**
   * Zeige eine Meldung in der Kommando-Zeile am Spiel-Ende. Zeige die
   * Gewinnsumme.
   */
  private static void zeigeSpielEnde(Spiel spiel) {
    if (spiel.istVerloren()) {
      System.out.println("Du hast leider verloren!");
    } else {
      System.out.println(String.format("Gratulation! Du hast %s € gewonnen!", spiel.gibGewinnSumme()));
    }
  }

  private static void zeigeThemenGebiete(ThemenKatalog katalog) {
    System.out.println("Wähle ein Themengebiet aus:\n");
    ThemenKatalog.GebietDaten[] gebietDaten = katalog.gibGebietDaten();
    for (ThemenKatalog.GebietDaten gebiet : gebietDaten) {
      System.out.println(String.format("  %s. %s", gebiet.nummer + 1, gebiet.titel));
    }
    System.out.print("\nGib die Nummer des Themen-Gebiets ein: ");
  }

  private static void zeigeBeantworteteFragen(FragenListe fragen) {
    System.out.println("\nDeine beantworteten Fragen:\n");
    ListenElement datenKnoten = fragen.gibKopf();
    while (datenKnoten != null) {
      Frage frage = datenKnoten.gibFrage();
      if (frage != null) {
        System.out.println(frage.gibFragenText());
      }
      datenKnoten = datenKnoten.gibNächstes();
    }
  }

  public static void main(String[] args) throws Exception, IOException {
    System.out.println("Willkommen bei „Wer wird INFORMATIKär (INFORMATIK-Millionär)");
    zeigeASCIILogo();
    ThemenKatalog katalog = new ThemenKatalog();
    zeigeThemenGebiete(katalog);
    Scanner scanner = new Scanner(System.in);
    int gebietsNummer = scanner.nextInt();

    ThemenKatalog.GebietDaten gebietDaten = katalog.gibGebietDatenDurchNummer(gebietsNummer - 1);

    System.out.println(String.format("Du hast das Themengebiet „%s“ ausgewählt. Das ist eine sehr gute Wahl!",
        Farbe.grün(gebietDaten.titel)));

    Spiel spiel = new Spiel();

    ThemenGebiet gebiet = katalog.gibGebietDurchNummer(gebietDaten.nummer);

    gebiet.leseFragenInsSpiel(spiel);

    boolean nochImSpiel = true;

    while (nochImSpiel) {
      Frage frage = spiel.gibNächsteFrage();
      stelleFrageAlsTextausgabe(spiel, frage);
      int antwort = frageNachAntwort(scanner);
      boolean richtig = spiel.beantworteFrage(antwort);
      zeigeFragenErgebnis(spiel, frage);
      if (richtig) {
        nochImSpiel = frageNachWeiterspielen();
      } else {
        nochImSpiel = richtig;
      }
    }

    zeigeSpielEnde(spiel);
    zeigeBeantworteteFragen(spiel.gibBeantworteteFragen());

    scanner.close();
  }
}
