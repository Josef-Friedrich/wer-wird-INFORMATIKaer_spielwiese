import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JahrgangsstufeTest {

  Frage frage1;
  Frage frage2;
  Frage frage3;
  Jahrgangsstufe jahrgangsstufe;

  /**
   * Konstruktor fuer die Test-Klasse JahrgangsstufeTest
   */
  public JahrgangsstufeTest() {
    frage1 = new Frage("Frage1", "richtig", "falsch1", "falsch2", "falsch3", 1);
    frage2 = new Frage("Frage2", "richtig", "falsch1", "falsch2", "falsch3", 2);
    frage3 = new Frage("Frage3", "richtig", "falsch1", "falsch2", "falsch3", 3);
  }

  void überprüfeFragenTexte(Jahrgangsstufe jahrgangsstufe, String[] fragenTexte) {
    Frage frage;
    for (String fragenText: fragenTexte) {
      frage = jahrgangsstufe.entnimmFrage();
      assertEquals(frage.gibFragenText(), fragenText);
    }
  }

  Jahrgangsstufe gibBefüllteJahrgangsstufe() {
    jahrgangsstufe = new Jahrgangsstufe();
    jahrgangsstufe.fügeHintenEin(frage1);
    jahrgangsstufe.fügeHintenEin(frage2);
    jahrgangsstufe.fügeHintenEin(frage3);
    return jahrgangsstufe;
  }

  @Test
  public void testeMethodeFügeVorneEin() {
    jahrgangsstufe = new Jahrgangsstufe();
    jahrgangsstufe.fügeVorneEin(frage1);
    jahrgangsstufe.fügeVorneEin(frage2);
    jahrgangsstufe.fügeVorneEin(frage3);
    überprüfeFragenTexte(jahrgangsstufe, new String[] { "Frage3", "Frage2", "Frage1" });
  }

  @Test
  public void testeMethodeFügeHintenEin() {
    jahrgangsstufe = new Jahrgangsstufe();
    jahrgangsstufe.fügeHintenEin(frage1);
    jahrgangsstufe.fügeHintenEin(frage2);
    jahrgangsstufe.fügeHintenEin(frage3);
    überprüfeFragenTexte(jahrgangsstufe, new String[] { "Frage1", "Frage2", "Frage3" });
  }

  @Test
  public void testeMethodeGibAnzahl() {
    jahrgangsstufe = new Jahrgangsstufe();
    jahrgangsstufe.fügeVorneEin(frage1);

    assertEquals(jahrgangsstufe.gibAnzahlFragen(), 1);
    jahrgangsstufe.fügeVorneEin(frage2);

    assertEquals(jahrgangsstufe.gibAnzahlFragen(), 2);

    jahrgangsstufe.fügeVorneEin(frage3);
    assertEquals(jahrgangsstufe.gibAnzahlFragen(), 3);

    jahrgangsstufe.entnimmFrage();
    assertEquals(jahrgangsstufe.gibAnzahlFragen(), 2);

    jahrgangsstufe.entnimmFrage();
    assertEquals(jahrgangsstufe.gibAnzahlFragen(), 1);

    jahrgangsstufe.entnimmFrage();
    assertEquals(jahrgangsstufe.gibAnzahlFragen(), 0);
  }

  @Test
  public void testeMethodeFügeVorPositionEin() {
    Frage frage4 = new Frage("Frage4", "richtig", "falsch", "falsch", "falsch", 1);
    Jahrgangsstufe jahrgangsstufe;

    jahrgangsstufe = gibBefüllteJahrgangsstufe();
    jahrgangsstufe.fügeVorPositionEin(frage4, 0);
    überprüfeFragenTexte(jahrgangsstufe, new String[] { "Frage4", "Frage1", "Frage2", "Frage3" });

    jahrgangsstufe = gibBefüllteJahrgangsstufe();
    jahrgangsstufe.fügeVorPositionEin(frage4, 1);
    überprüfeFragenTexte(jahrgangsstufe, new String[] { "Frage1", "Frage4", "Frage2", "Frage3" });

    jahrgangsstufe = gibBefüllteJahrgangsstufe();
    jahrgangsstufe.fügeVorPositionEin(frage4, 2);
    überprüfeFragenTexte(jahrgangsstufe, new String[] { "Frage1", "Frage2", "Frage4", "Frage3" });

    jahrgangsstufe = gibBefüllteJahrgangsstufe();
    jahrgangsstufe.fügeVorPositionEin(frage4, 3);
    überprüfeFragenTexte(jahrgangsstufe, new String[] { "Frage1", "Frage2", "Frage3", "Frage4" });

    jahrgangsstufe = gibBefüllteJahrgangsstufe();
    jahrgangsstufe.fügeVorPositionEin(frage4, 4);
    überprüfeFragenTexte(jahrgangsstufe, new String[] { "Frage1", "Frage2", "Frage3", "Frage4" });
  }
}
