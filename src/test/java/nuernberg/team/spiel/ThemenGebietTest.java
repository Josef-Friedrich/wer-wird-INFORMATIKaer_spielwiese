package nuernberg.team.spiel;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ThemenGebietTest {

  @Test
  public void testeKonstruktor() throws Exception {
    ThemenGebiet gebiet = new ThemenGebiet("musik/musik01.xml");
    assertEquals("Musik", gebiet.gibFach());
  }

  @Test
  public void erzeugeThemenGebiet() throws Exception {
    File tmpDatei = File.createTempFile("wwim", ".xml");

    ThemenGebiet schreiben = new ThemenGebiet(tmpDatei);
    schreiben.setzeFach("Informatik");
    schreiben.setzeThema("6. Jahrgangsstufe");
    schreiben.setzeAutor("Josef Friedrich");
    schreiben.schreibeInDatei();

    ThemenGebiet lesen = new ThemenGebiet(tmpDatei);
    assertEquals("Informatik", lesen.gibFach());
    assertEquals("6. Jahrgangsstufe", lesen.gibThema());
    assertEquals("Josef Friedrich", lesen.gibAutor());
  }

  @Test
  public void methodeKonvertiereCSV() throws Exception {
    File tmpDatei = File.createTempFile("wwim", ".xml");

    ThemenGebiet schreiben = new ThemenGebiet(tmpDatei);
    schreiben.konvertiereCSV("/fragen/fragen.csv", 6);

    ThemenGebiet lesen = new ThemenGebiet(tmpDatei);
    assertEquals("Informatik", lesen.gibFach());
    assertEquals("6. Jahrgangsstufe", lesen.gibThema());
    assertEquals("Team Nürnberg", lesen.gibAutor());

    Document dokument = lesen.gibDokument();
    NodeList knotenListe = dokument.getElementsByTagName("frage");
    Node frage = knotenListe.item(0);
    assertEquals("frage", frage.getNodeName());
  }

}
