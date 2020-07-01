public class Jahrgangsstufe
{
    private ListenElement kopf;

    public Jahrgangsstufe()
    {
        kopf = new ListenAbschluss();
    }

    public void fügeHintenEin(Frage frage) {
      kopf = kopf.fügeHintenEin(frage);
    }

}
