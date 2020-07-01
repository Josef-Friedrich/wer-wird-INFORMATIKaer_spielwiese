public class Spiel {
  public static void main(String[] args) {
    Frage frage1 = new Frage("Frage1", "richtig", "falsch1", "falsch2", "falsch3", 1);
    Frage frage2 = new Frage("Frage2", "richtig", "falsch1", "falsch2", "falsch3", 2);
    Frage frage3 = new Frage("Frage3", "richtig", "falsch1", "falsch2", "falsch3", 3);
    Frage frage4 = new Frage("Frage4", "richtig", "falsch1", "falsch2", "falsch3", 4);
    Frage frage5 = new Frage("Frage5", "richtig", "falsch1", "falsch2", "falsch3", 5);
    Frage frage6 = new Frage("Frage6", "richtig", "falsch1", "falsch2", "falsch3", 1);
    Frage frage7 = new Frage("Frage7", "richtig", "falsch1", "falsch2", "falsch3", 2);
    Frage frage8 = new Frage("Frage8", "richtig", "falsch1", "falsch2", "falsch3", 3);
    Frage frage9 = new Frage("Frage9", "richtig", "falsch1", "falsch2", "falsch3", 4);
    Frage frage10 = new Frage("Frage10", "richtig", "falsch1", "falsch2", "falsch3", 5);
    // for (int i = 0; i < 10; i++) {
    // frage1.stelleFrageAlsTextausgabe();
    // }

    Jahrgangsstufe klasse6 = new Jahrgangsstufe();

    System.out.println("Anzahl: " + klasse6.gibAnzahlFragen());

    klasse6.fügeZufälligEin(frage1);
    klasse6.fügeZufälligEin(frage2);
    klasse6.fügeZufälligEin(frage3);
    klasse6.fügeZufälligEin(frage4);
    klasse6.fügeZufälligEin(frage5);
    klasse6.fügeZufälligEin(frage6);
    klasse6.fügeZufälligEin(frage7);
    klasse6.fügeZufälligEin(frage8);
    klasse6.fügeZufälligEin(frage9);
    klasse6.fügeZufälligEin(frage10);

    System.out.println("Anzahl: " + klasse6.gibAnzahlFragen());

    Frage entnommeneFrage;
    do {
      entnommeneFrage = klasse6.entnimmFrage();
      if (entnommeneFrage != null) entnommeneFrage.stelleFrageAlsTextausgabe();
    } while (entnommeneFrage != null);

    System.out.println("Anzahl: " + klasse6.gibAnzahlFragen());
  }
}
