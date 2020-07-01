import java.util.Random;

public class Jahrgangsstufe {
  private ListenElement kopf;

  public Jahrgangsstufe() {
    kopf = new ListenAbschluss();
  }

  public void fügeHintenEin(Frage frage) {
    kopf = kopf.fügeHintenEin(frage);
  }

  public void fügeVorneEin(Frage frage) {
    DatenKnoten neuerKnoten = new DatenKnoten(kopf, frage);
    kopf = neuerKnoten;
  }

  public void fügeZufälligEin(Frage frage) {
    Random zufall = new Random();
    if (zufall.nextBoolean()) {
      fügeVorneEin(frage);
    } else {
      fügeHintenEin(frage);
    }
  }

  public int gibAnzahlFragen() {
    return kopf.gibAnzahlFragen();
  }

  public Frage entnimmFrage() {
    Frage frage = kopf.gibFrage();
    kopf = kopf.gibNächstes();
    return frage;
  }

}
