
public class ListenAbschluss extends ListenElement {
  public ListenElement gibNächstes() {
    return this;
  }

  public Frage gibFrage() {
    return null;
  }

  public DatenKnoten fügeHintenEin(Frage frage) {
    return new DatenKnoten(this, frage);
  }

  public int gibAnzahlFragen() {
    return 0;
  }

  public void setzeNächstes(ListenElement nächstes) {
    return;
  }
}
