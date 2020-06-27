public class Spiel
{
    public static void main (String[] args) {
        Frage frage = new Frage("Frage", "richtig", "falsch1", "falsch2", "falsch3", 1);
        
        for (int i = 0; i < 10; i++) {
            frage.stelleFrageAlsTextausgabe();
        }
        
    }
}
