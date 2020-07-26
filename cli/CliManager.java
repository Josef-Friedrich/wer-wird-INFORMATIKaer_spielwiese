package cli;

import spiel.Spiel;
import spiel.Frage;
import spiel.CSVLeser;
import java.util.Scanner;
import java.io.IOException;

public class CliManager {
  public static void main(String[] args) {
    System.out.println("Willkommen bei „Wer wird INFORMATIKär (INFORMATIK-Millionär)");

    System.out.println("                         ");
    System.out.println("        R   W   I        ");
    System.out.println("     E             R     ");
    System.out.println("  W                   D  ");
    System.out.println("                         ");
    System.out.println(" I n f o r m a t i k Ä R ");
    System.out.println("                         ");
    System.out.println("  W                   D  ");
    System.out.println("     E              R    ");
    System.out.println("        R    W   I       ");
    System.out.println("                         ");
    System.out.print("In welcher Jahrgangsstufe bist du? (6 oder 7): ");
    Scanner scanner = new Scanner(System.in);
    int jahrgangsstufe = scanner.nextInt();
    System.out.println(String.format("Du bist in der %s. Jahrgangsstufe.", jahrgangsstufe));

    Spiel spiel = new Spiel();

    try {
      CSVLeser leser = new CSVLeser("./Fragen.csv");
      leser.leseInSpielEin(spiel, jahrgangsstufe);
    } catch (IOException e) {
      //TODO: handle exception
    }

    Frage frage = spiel.gibNächsteFrage();
    CliFrage cliFrage = new CliFrage(frage);
    cliFrage.stelleFrageAlsTextausgabe();

    scanner.close();

  }
}
