import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Test-Klasse JahrgangsstufeTest.
 *
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class JahrgangsstufeTest
{

  Frage frage1;
  Frage frage2;
  Frage frage3;
  Jahrgangsstufe jahrgangsstufe;

    /**
     * Konstruktor fuer die Test-Klasse JahrgangsstufeTest
     */
    public JahrgangsstufeTest()
    {
      frage1 = new Frage("Frage1", "richtig", "falsch1", "falsch2", "falsch3", 1);
      frage2 = new Frage("Frage2", "richtig", "falsch1", "falsch2", "falsch3", 2);
      frage3 = new Frage("Frage3", "richtig", "falsch1", "falsch2", "falsch3", 3);
      jahrgangsstufe = new Jahrgangsstufe();
    }

    @Test
    public void testeMethodeFügeVorneEin() {
      jahrgangsstufe.fügeVorneEin(frage1);
      jahrgangsstufe.fügeVorneEin(frage2);
      jahrgangsstufe.fügeVorneEin(frage3);

      Frage frage;
      frage = jahrgangsstufe.entnimmFrage();
      assertEquals(frage.gibFragenText(), frage3.gibFragenText());

      frage = jahrgangsstufe.entnimmFrage();
      assertEquals(frage.gibFragenText(), frage2.gibFragenText());

      frage = jahrgangsstufe.entnimmFrage();
      assertEquals(frage.gibFragenText(), frage1.gibFragenText());
    }

    @Test
    public void testeMethodeFügeHintenEin() {
      jahrgangsstufe.fügeHintenEin(frage1);
      jahrgangsstufe.fügeHintenEin(frage2);
      jahrgangsstufe.fügeHintenEin(frage3);

      Frage frage;
      frage = jahrgangsstufe.entnimmFrage();
      assertEquals(frage.gibFragenText(), frage1.gibFragenText());

      frage = jahrgangsstufe.entnimmFrage();
      assertEquals(frage.gibFragenText(), frage2.gibFragenText());

      frage = jahrgangsstufe.entnimmFrage();
      assertEquals(frage.gibFragenText(), frage3.gibFragenText());
    }
}
