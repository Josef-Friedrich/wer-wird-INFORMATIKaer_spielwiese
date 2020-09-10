package nuernberg.team.spiel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ThemenKatalogTest {

  @Test
  public void methodeGibGebietDurchNummer() throws Exception {
    ThemenKatalog katalog = new ThemenKatalog();
    ThemenGebiet gebiet = katalog.gibGebietDurchNummer(0);
    assertEquals("6. Jahrgangsstufe", gebiet.gibThema());
  }

}
