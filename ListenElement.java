
public abstract class ListenElement {
    
  protected ListenElement nächstes;
  protected Frage frage;
    
  public ListenElement gibNächstes() {
      return nächstes;
    
  }

  public Frage gibFrage() {
      return frage;
  }
}
