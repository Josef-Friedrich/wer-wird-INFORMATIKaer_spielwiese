package nuernberg.team.spiel;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

public class ThemenGebietTest {

  @Test
  public void testeKonstruktor() throws Exception {
    ThemenGebiet gebiet = new ThemenGebiet("/fragen/musik/musik01.xml");
    assertEquals("Musik", gebiet.gibFach());
  }

  @Test
  public void erzeugeThemenGebiet() throws Exception {
    File tmpDatei = File.createTempFile("wwim", ".xml");
    System.out.println(tmpDatei.toString());

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
}
