package spiel;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class SpielTest {

  private Spiel spiel;

  @Before
  public void erzeugeFrischesTestSpiel() {
    spiel = new Spiel();

    String[][] fragen = {
      {"Frage1", "richtig", "falsch1", "falsch2", "falsch3", "1"},
      {"Frage2", "richtig", "falsch1", "falsch2", "falsch3", "2"},
      {"Frage3", "richtig", "falsch1", "falsch2", "falsch3", "3"},
      {"Frage4", "richtig", "falsch1", "falsch2", "falsch3", "4"},
      {"Frage5", "richtig", "falsch1", "falsch2", "falsch3", "5"},
      {"Frage6", "richtig", "falsch1", "falsch2", "falsch3", "1"},
      {"Frage7", "richtig", "falsch1", "falsch2", "falsch3", "2"},
      {"Frage8", "richtig", "falsch1", "falsch2", "falsch3", "3"},
      {"Frage9", "richtig", "falsch1", "falsch2", "falsch3", "4"},
      {"Frage10", "richtig", "falsch1", "falsch2", "falsch3", "5"},
    };

    spiel.fügeFragenAlsFeldEin(fragen);
  }

  @Test
  public void testeMethodeIstAntwortRichtig() {
    Frage frage = spiel.gibNächsteFrage();
    assertTrue(spiel.istAntwortRichtig(frage.gibRichtigeAntwort()));
  }

  @Test
  public void testeMethodeGibFragenNummer() {
    assertEquals(spiel.gibFragenNummer(), 0);
    spiel.gibNächsteFrage();
    assertEquals(spiel.gibFragenNummer(), 1);
    spiel.gibNächsteFrage();
    assertEquals(spiel.gibFragenNummer(), 2);
  }

  @Test
  public void testeMethodeGibAnzahlUnbeantworterFragen() {
    assertEquals(spiel.gibAnzahlUnbeantworterFragen(), 10);
    spiel.gibNächsteFrage();
    assertEquals(spiel.gibAnzahlUnbeantworterFragen(), 9);
    spiel.gibNächsteFrage();
    assertEquals(spiel.gibAnzahlUnbeantworterFragen(), 8);
  }

  @Test
  public void testeMethodeBeantworteFrage() {
    assertEquals(spiel.gibAnzahlUnbeantworterFragen(), 10);
    assertEquals(spiel.gibAnzahlBeantworterFragen(), 0);

    spiel.gibNächsteFrage();
    spiel.beantworteFrage(0);
    assertEquals(spiel.gibAnzahlUnbeantworterFragen(), 9);
    assertEquals(spiel.gibAnzahlBeantworterFragen(), 1);

    spiel.gibNächsteFrage();
    spiel.beantworteFrage(0);
    assertEquals(spiel.gibAnzahlUnbeantworterFragen(), 8);
    assertEquals(spiel.gibAnzahlBeantworterFragen(), 2);
  }
}
