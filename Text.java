import ch.aplu.jgamegrid.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Text extends TextActor implements GGKeyListener {

  public Text(String text) {
    super(false, text, java.awt.Color.WHITE, java.awt.Color.BLACK, new Font("Sans", Font.BOLD, 20));
  }

  public boolean keyPressed(KeyEvent evt) {
    return false; // Don't consume the key
  }

  public boolean keyReleased(KeyEvent evt) {
    return false;
  }

}
