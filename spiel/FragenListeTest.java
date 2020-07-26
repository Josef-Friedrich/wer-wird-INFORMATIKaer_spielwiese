package spiel;

import static org.junit.Assert.*;
import org.junit.Test;

public class FragenListeTest {

  Frage frage1;
  Frage frage2;
  Frage frage3;
  FragenListe liste;

  public FragenListeTest() {
    frage1 = new Frage("Frage1", "richtig", "falsch1", "falsch2", "falsch3", 1);
    frage2 = new Frage("Frage2", "richtig", "falsch1", "falsch2", "falsch3", 2);
    frage3 = new Frage("Frage3", "richtig", "falsch1", "falsch2", "falsch3", 3);
  }

  void überprüfeFragenTexte(FragenListe jahrgangsstufe, String[] fragenTexte) {
    Frage frage;
    for (String fragenText: fragenTexte) {
      frage = jahrgangsstufe.entnimmErsteFrage();
      assertEquals(frage.gibFragenText(), fragenText);
    }
  }

  FragenListe gibBefüllteListe() {
    liste = new FragenListe();
    liste.fügeHintenEin(frage1);
    liste.fügeHintenEin(frage2);
    liste.fügeHintenEin(frage3);
    return liste;
  }

  @Test
  public void testeMethodeFügeVorneEin() {
    liste = new FragenListe();
    liste.fügeVorneEin(frage1);
    liste.fügeVorneEin(frage2);
    liste.fügeVorneEin(frage3);
    überprüfeFragenTexte(liste, new String[] { "Frage3", "Frage2", "Frage1" });
  }

  @Test
  public void testeMethodeFügeHintenEin() {
    liste = new FragenListe();
    liste.fügeHintenEin(frage1);
    liste.fügeHintenEin(frage2);
    liste.fügeHintenEin(frage3);
    überprüfeFragenTexte(liste, new String[] { "Frage1", "Frage2", "Frage3" });
  }

  @Test
  public void testeMethodeGibAnzahl() {
    liste = new FragenListe();
    liste.fügeVorneEin(frage1);

    assertEquals(liste.gibAnzahlFragen(), 1);
    liste.fügeVorneEin(frage2);

    assertEquals(liste.gibAnzahlFragen(), 2);

    liste.fügeVorneEin(frage3);
    assertEquals(liste.gibAnzahlFragen(), 3);

    liste.entnimmErsteFrage();
    assertEquals(liste.gibAnzahlFragen(), 2);

    liste.entnimmErsteFrage();
    assertEquals(liste.gibAnzahlFragen(), 1);

    liste.entnimmErsteFrage();
    assertEquals(liste.gibAnzahlFragen(), 0);
  }

  @Test
  public void testeMethodeFügeVorPositionEin() {
    Frage frage4 = new Frage("Frage4", "richtig", "falsch", "falsch", "falsch", 1);
    FragenListe l;

    l = gibBefüllteListe();
    l.fügeVorPositionEin(frage4, 0);
    überprüfeFragenTexte(l, new String[] { "Frage4", "Frage1", "Frage2", "Frage3" });

    l = gibBefüllteListe();
    l.fügeVorPositionEin(frage4, 1);
    überprüfeFragenTexte(l, new String[] { "Frage1", "Frage4", "Frage2", "Frage3" });

    l = gibBefüllteListe();
    l.fügeVorPositionEin(frage4, 2);
    überprüfeFragenTexte(l, new String[] { "Frage1", "Frage2", "Frage4", "Frage3" });

    l = gibBefüllteListe();
    l.fügeVorPositionEin(frage4, 3);
    überprüfeFragenTexte(l, new String[] { "Frage1", "Frage2", "Frage3", "Frage4" });

    l = gibBefüllteListe();
    l.fügeVorPositionEin(frage4, 4);
    überprüfeFragenTexte(l, new String[] { "Frage1", "Frage2", "Frage3", "Frage4" });
  }

  @Test
  public void testeMethodeFügeZufälligEin() {
    Frage frage4 = new Frage("Frage4", "richtig", "falsch", "falsch", "falsch", 1);
    FragenListe jahrgangsstufe = new FragenListe();
    jahrgangsstufe.fügeZufälligEin(frage1);
    jahrgangsstufe.fügeZufälligEin(frage2);
    jahrgangsstufe.fügeZufälligEin(frage3);
    jahrgangsstufe.fügeZufälligEin(frage4);
    assertEquals(jahrgangsstufe.gibAnzahlFragen(), 4);
  }

  @Test
  public void testeMethodeEntnimmErsteFrage() {
    FragenListe l = gibBefüllteListe();

    l.entnimmErsteFrage();
    assertEquals(l.gibAnzahlFragen(), 2);
    assertEquals(l.gibAnzahlDatenKnoten(), 2);

    l.entnimmErsteFrage();
    assertEquals(l.gibAnzahlFragen(), 1);
    assertEquals(l.gibAnzahlDatenKnoten(), 1);

    l.entnimmErsteFrage();
    assertEquals(l.gibAnzahlFragen(), 0);
    assertEquals(l.gibAnzahlDatenKnoten(), 0);

    Frage frage = l.entnimmErsteFrage();
    assertEquals(frage, null);
  }

  @Test
  public void testeMethodeEntnimmFrageZuerstSchwierige() {
    FragenListe l = gibBefüllteListe();
    Frage frage;
    frage = l.entnimmFrage(3);
    assertEquals(frage.gibSchwierigkeit(), 3);
    assertEquals(l.gibAnzahlFragen(), 2);

    frage = l.entnimmFrage(2);
    assertEquals(frage.gibSchwierigkeit(), 2);
    assertEquals(l.gibAnzahlFragen(), 1);

    frage = l.entnimmFrage(1);
    assertEquals(frage.gibSchwierigkeit(), 1);
    assertEquals(l.gibAnzahlFragen(), 0);
  }

  @Test
  public void testeMethodeEntnimmFrageZuerstLeichte() {
    FragenListe l = gibBefüllteListe();
    Frage frage;
    frage = l.entnimmFrage(1);
    assertEquals(frage.gibSchwierigkeit(), 1);
    assertEquals(l.gibAnzahlFragen(), 2);
    assertEquals(l.gibAnzahlDatenKnoten(), 2);

    frage = l.entnimmFrage(2);
    assertEquals(frage.gibSchwierigkeit(), 2);
    assertEquals(l.gibAnzahlFragen(), 1);
    assertEquals(l.gibAnzahlDatenKnoten(), 1);

    frage = l.entnimmFrage(3);
    assertEquals(frage.gibSchwierigkeit(), 3);
    assertEquals(l.gibAnzahlFragen(), 0);
    assertEquals(l.gibAnzahlDatenKnoten(), 0);
  }

  @Test
  public void testeMethodeEntnimmFrageNichtVorhandeneSchwierigkeit() {
    FragenListe l = gibBefüllteListe();
    Frage frage;
    frage = l.entnimmFrage(4);
    assertEquals(frage.gibSchwierigkeit(), 1);
    assertEquals(l.gibAnzahlFragen(), 2);
  }

  @Test
  public void testeMethodeGibAnzahlDatenKnoten() {
    FragenListe l = new FragenListe();
    assertEquals(l.gibAnzahlDatenKnoten(), 0);
    l.fügeVorneEin(frage1);
    assertEquals(l.gibAnzahlDatenKnoten(), 1);
    l.fügeHintenEin(frage1);
    assertEquals(l.gibAnzahlDatenKnoten(), 2);
    l.fügeZufälligEin(frage1);
    assertEquals(l.gibAnzahlDatenKnoten(), 3);

    l.entnimmFrage(1);
    assertEquals(l.gibAnzahlDatenKnoten(), 2);
    l.entnimmFrage(1);
    assertEquals(l.gibAnzahlDatenKnoten(), 1);
    l.entnimmFrage(1);
    assertEquals(l.gibAnzahlDatenKnoten(), 0);
  }
}
