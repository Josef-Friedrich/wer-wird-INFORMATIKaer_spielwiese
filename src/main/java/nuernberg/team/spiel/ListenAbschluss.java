package nuernberg.team.spiel;

public class ListenAbschluss extends ListenElement {
  public ListenElement gibNächstes() {
    return null;
  }

  public Frage gibFrage() {
    return null;
  }

  public DatenKnoten fügeHintenEin(Frage frage) {
    return new DatenKnoten(this, frage);
  }

  public void setzeNächstes(ListenElement nächstes) {
    return;
  }

  public int gibAnzahlDatenKnoten() {
    return 0;
  }

}
