import java.util.Random;

/**
 * Die Klasse Frage speichert den Fragentext sowie die richtige Antwort als auch
 * drei falsche Antworten. Außerdem wird die Schwierigkeit gespeichert.
 */
public class Frage {
  /**
   * Der Fragentext
   */
  private String frage;

  /**
   * Ein Feld mit 4 Elementen, das Text aufgenehmen kann. Wenn die Klasse Frage
   * erzeugt wird, ist die richtige Antwort das erste Element
   */
  private String[] antworten = new String[4];

  /**
   * Index-Position im Feld {@link antworten}, bei der sich die richtige Antwort
   * befindent.
   */
  private int positionRichtigeAntwort;

  /**
   * Eine Zahl zwischen 1 und 5. 1 ist die leichteste Schwierigkeitsstufe, 5 die
   * schwierigste.
   */
  private int schwierigkeit;

  /**
   * Ein Hilfsfeld, damit wir leicht die Fragen mit A B C D nummerieren können.
   */
  private String[] fragenAnfangsBuchstaben = { "A", "B", "C", "D" };

  /**
   * @param frage Der Text der Frage.
   * @param richtigeAntwort Der Text der richtigen Antwort.
   */
  public Frage(String frage, String richtigeAntwort, String falscheAntwort1, String falscheAntwort2,
      String falscheAntwort3, int schwierigkeit) {
    this.frage = frage;
    antworten[0] = richtigeAntwort;
    positionRichtigeAntwort = 0;
    antworten[1] = falscheAntwort1;
    antworten[2] = falscheAntwort2;
    antworten[3] = falscheAntwort3;
    this.schwierigkeit = schwierigkeit;
  }

  /**
   * Mische die Antworten zufällig.
   *
   * Dabei muss das Attribut {@link positionRichtigeAntwort} aktualisiert werden.
   *
   * @see <a href=
   *      "https://de.wikipedia.org/wiki/Zufällige_Permutation#Fisher-Yates-Verfahren">Fisher-Yates-Verfahren</a>
   */
  public void mischeAntworten() {
    // Ein Objekt der Klasse Random erzeugen, um Zugriff auf einen
    // Pseudozufallsgenerator zu haben.
    Random zufall = new Random();

    // Wir beginnen beim letzten Element und vertauschen die Elemente
    // nach und nach. Wir müssen nicht das erste Element vertauschen,
    // weil es möglicherweise schon vertauscht wurde. Deshalb ist
    // i > 0
    for (int i = antworten.length - 1; i > 0; i--) {
      // Wähle eine zufälligen Index von 0 bis i.
      int j = zufall.nextInt(i + 1);

      // Falls eine der der vertauschten Antworten die richtige
      // Antwort ist, müssen wird die Index-Nummer des Attributs
      // positionRichtigeAntwort anpassen.
      // Die richtige Antwort kann an der j- oder an der i-ten Position
      // stehen.
      if (j == positionRichtigeAntwort) {
        positionRichtigeAntwort = i;
      } else if (i == positionRichtigeAntwort) {
        positionRichtigeAntwort = j;
      }
      // Vertausche die Antworten
      // Wir brauchen dazu eine temporäre Variable.
      String tmp = antworten[i];
      antworten[i] = antworten[j];
      antworten[j] = tmp;
    }
  }

  public void stelleFrageAlsTextausgabe() {
    mischeAntworten();
    System.out.println(frage);
    System.out.print("A:" + antworten[0] + " ");
    System.out.print("B:" + antworten[1] + " ");
    System.out.print("C:" + antworten[2] + " ");
    System.out.print("D:" + antworten[3] + " ");
    System.out.println("Richtige Antwort: " + fragenAnfangsBuchstaben[positionRichtigeAntwort]);
    System.out.println();
  }

  /**
   * Gib die Schwierigkeit der Frage zurück.
   *
   * @return Die Schwierigkeit der Frage.
   */
  public int gibSchwierigkeit() {
    return schwierigkeit;
  }

  /**
   * Gib den Text der Frage zurück.
   *
   * @return Der Text der Frage.
   */
  public String gibFragenText() {
    return frage;
  }

  /**
   * Gib alle Fragen als ein Feld zurück.
   *
   * @return Alle vier Antworten als eine Feld.
   */
  public String[] gibAntworten() {
    return antworten;
  }
}
