import java.util.Random; 

public class Frage
{
    private String frage;
    private String[] antworten = new String[4];
    private int positionRichtigeAntwort;
    private int schwierigkeit = 1;
    
    private String[] fragenAnfangsBuchstaben = { "A", "B", "C", "D" };

    public Frage(String frage, String richtigeAntwort, String falscheAntwort1, String falscheAntwort2, String falscheAntwort3, int schwierigkeit)
    {
        this.frage = frage;
        antworten[0] = richtigeAntwort;
        positionRichtigeAntwort = 0;
        antworten[1] = falscheAntwort1;
        antworten[2] = falscheAntwort2;
        antworten[3] = falscheAntwort3;
    }
    
    // https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
    private void mischeAntworten() {
        // Creating a object for Random class 
        Random zufall = new Random(); 
          
        // Start from the last element and swap one by one. We don't 
        // need to run for the first element that's why i > 0 
        for (int i = antworten.length - 1; i > 0; i--) { 
            // Pick a random index from 0 to i 
            int j = zufall.nextInt(i + 1); 
            System.out.println(j + "<>" + i);             

            if (j == positionRichtigeAntwort) {
                positionRichtigeAntwort = i;
            } else if (i == positionRichtigeAntwort) {
                positionRichtigeAntwort = j;
            }
            // Swap arr[i] with the element at random index 
            String tmp = antworten[i]; 
            antworten[i] = antworten[j]; 
            antworten[j] = tmp; 
        }
    }

    public void stelleFrageAlsTextausgabe () {
        mischeAntworten();
        System.out.println(frage);
        System.out.println("A: " + antworten[0]);
        System.out.println("B: " + antworten[1]);
        System.out.println("C: " + antworten[2]);
        System.out.println("D: " + antworten[3]);
        System.out.println("Richtige Antwort: " + fragenAnfangsBuchstaben[positionRichtigeAntwort]);
    }
}
