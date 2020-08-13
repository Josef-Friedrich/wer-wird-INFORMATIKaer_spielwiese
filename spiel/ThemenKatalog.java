package spiel;

import java.io.File;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Gibt einen Überblick über alle verfügbaren Themengebiete. Die
 * Themengebiete werden in der Datei ./spiel/fragen/index.xml
 * konfiguriert.
 */
public class ThemenKatalog extends XMLDatei {

  NodeList knotenListe;

  public ThemenKatalog() {
    super(new File("./spiel/fragen/index.xml"));
    knotenListe = dokument.getElementsByTagName("themenGebiet");
  }

  private String gibTextAttributVonKnoten (Node knoten, String attributName) {
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

  /**
   * @param nummer Die Nummer in der Themen Liste beginnend mit 0.
   */
  public ThemenGebiet ladeGebietDurchNummer(int nummer) {
    Node knoten = knotenListe.item(nummer);
    ThemenGebiet gebiet = new ThemenGebiet(new File("./spiel/fragen/" + gibPfadVonKnoten(knoten)));
    return gebiet;
  }

  public static void main(String[] args) {
    ThemenKatalog katalog = new ThemenKatalog();
    katalog.listeGebieteAuf();
    ThemenGebiet gebiet = katalog.ladeGebietDurchNummer(0);
    System.out.println(gebiet.gibThema());
  }

}
