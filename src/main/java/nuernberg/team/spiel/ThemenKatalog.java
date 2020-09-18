package nuernberg.team.spiel;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Gibt einen Überblick über alle verfügbaren Themengebiete. Die Themengebiete
 * werden in der Datei <code>./spiel/fragen/index.xml</code> konfiguriert.
 */
public class ThemenKatalog extends XMLDatei {

  /**
   * Eine kleine Klasse um eine paar Daten über ein Themengebiet zu speichern.
   */
  public static class GebietDaten {

    public String pfad;

    public String titel;

    public int nummer;

    public GebietDaten(String pfad, String titel, int nummer) {
      this.pfad = pfad;
      this.titel = titel;
      this.nummer = nummer;
    }
  }

  NodeList knotenListe;

  public ThemenKatalog() {
    super("/fragen/index.xml");
    knotenListe = dokument.getElementsByTagName("themenGebiet");
  }

  private String gibTextAttributVonKnoten(Node knoten, String attributName) {
    NamedNodeMap attributes = knoten.getAttributes();
    Node pfad = attributes.getNamedItem(attributName);
    return pfad.getTextContent();
  }

  private String gibPfadVonKnoten(Node knoten) {
    return gibTextAttributVonKnoten(knoten, "pfad");
  }

  private String gibTitelVonKnoten(Node knoten) {
    return gibTextAttributVonKnoten(knoten, "titel");
  }

  public void listeGebieteAuf() {
    for (int i = 0; i < knotenListe.getLength(); i++) {
      Node knoten = knotenListe.item(i);
      System.out.println(gibTitelVonKnoten(knoten) + " " + gibPfadVonKnoten(knoten));
    }
  }

  public GebietDaten[] gibGebietDaten() {
    GebietDaten[] gebiete = new GebietDaten[knotenListe.getLength()];
    for (int i = 0; i < knotenListe.getLength(); i++) {
      Node knoten = knotenListe.item(i);
      gebiete[i] = new GebietDaten(gibPfadVonKnoten(knoten), gibTitelVonKnoten(knoten), i);
    }
    return gebiete;
  }

  public GebietDaten gibGebietDatenDurchNummer(int nummer) {
    Node knoten = knotenListe.item(nummer);
    return new GebietDaten(gibPfadVonKnoten(knoten), gibTitelVonKnoten(knoten), nummer);
  }

  /**
   * @param nummer Die Nummer in der Themenliste beginnend mit 0.
   *
   * @return Das ausgewählte Themengebiet.
   */
  public ThemenGebiet gibGebietDurchNummer(int nummer) {
    Node knoten = knotenListe.item(nummer);
    ThemenGebiet gebiet = new ThemenGebiet(gibPfadVonKnoten(knoten));
    return gebiet;
  }

}
