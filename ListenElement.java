
public abstract class ListenElement {

  protected ListenElement nächstes;
  protected DatenKnoten datenKnoten;

  public ListenElement gibNächstes() {
      return nächstes;
  }

  public DatenKnoten gibDatenKnoten() {
      return datenKnoten;
  }

  public abstract DatenKnoten fügeHintenEin(Frage frage);

  public abstract int gibAnzahlFragen();

  public abstract Frage gibFrage();
}
