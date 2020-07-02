import java.util.Random;

/**
 * Die Klasse Frage speichert den Fragentext sowie die richtige Antwort als auch
 * drei falsche Antworten. Außerdem wird die Schwierigkeit gespeichert.
 */
public class Frage {
  /**
   * Der Fragentext
   */
  protected String frage;

  /**
   * Ein Feld mit 4 Elementen, das Text aufgenehmen kann. Wenn die Klasse Frage
   * erzeugt wird, ist die richtige Antwort das erste Element
   */
  protected String[] antworten = new String[4];

  /**
   * Index-Position im Feld {@link antworten}, bei der sich die richtige Antwort
   * befindent.
   */
  protected int positionRichtigeAntwort;

  /**
   * Eine Zahl zwischen 1 und 5. 1 ist die leichteste Schwierigkeitsstufe, 5 die
   * schwierigste.
   */
  protected int schwierigkeit;

  /**
   * Ein Hilfsfeld, damit wir leicht die Fragen mit A B C D nummerieren können.
   */
  protected String[] fragenAnfangsBuchstaben = { "A", "B", "C", "D" };

  /**
   * @param frage           Der Text der Frage
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
  protected void mischeAntworten() {
    // Creating a object for Random class
    Random zufall = new Random();

    // Start from the last element and swap one by one. We don't
    // need to run for the first element that's why i > 0
    for (int i = antworten.length - 1; i > 0; i--) {
      // Pick a random index from 0 to i
      int j = zufall.nextInt(i + 1);

      if (j == positionRichtigeAntwort) {
        positionRichtigeAntwort = i;
      } else if (i == positionRichtigeAntwort) {
        positionRichtigeAntwort = j;
      }
      // Swap arr[i] with the element at random index
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

  public int gibSchwierigkeit() {
    return schwierigkeit;
  }
}
