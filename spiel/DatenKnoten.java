package spiel;

public class DatenKnoten extends ListenElement {

  private Frage frage;

  public DatenKnoten(ListenElement n, Frage f) {
    nächstes = n;
    frage = f;
  }

  public void setzeNächstes(ListenElement n) {
    nächstes = n;
  }

  public void setzeFrage(Frage f) {
    frage = f;
  }

  public ListenElement gibNächstes() {
    return nächstes;
  }

  public Frage gibFrage() {
    return frage;
  }

  public DatenKnoten fügeHintenEin(Frage frage) {
    nächstes = nächstes.fügeHintenEin(frage);
    return this;
  }

  public int gibAnzahlDatenKnoten() {
    return 1 + nächstes.gibAnzahlDatenKnoten();
  }
}
