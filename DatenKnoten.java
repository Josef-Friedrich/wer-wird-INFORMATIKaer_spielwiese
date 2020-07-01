public class DatenKnoten extends ListenElement {

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

  public DatenKnoten hintenEinfuegen(Frage frage) {
    nächstes = nächstes.hintenEinfuegen(frage);
    return this;
  }

  public DatenElement datenLetzterGeben(Frage aktuellerInhalt) {
    return nächstes.datenLetzterGeben(frage);
  }
}