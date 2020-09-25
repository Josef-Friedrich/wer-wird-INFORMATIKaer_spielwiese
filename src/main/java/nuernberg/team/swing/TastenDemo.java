package nuernberg.team.swing;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class TastenDemo extends JPanel implements ActionListener {
  protected JButton b1, b2, b3;

  public TastenDemo() {
    b1 = new JButton("Deaktiviere mittlere Taste");
    b1.setVerticalTextPosition(AbstractButton.CENTER);
    b1.setHorizontalTextPosition(AbstractButton.LEADING);
    b1.setMnemonic(KeyEvent.VK_D);
    b1.setActionCommand("disable");

    b2 = new JButton("Mittlere Taste");
    b2.setVerticalTextPosition(AbstractButton.BOTTOM);
    b2.setHorizontalTextPosition(AbstractButton.CENTER);
    b2.setMnemonic(KeyEvent.VK_M);

    b3 = new JButton("Aktiviere miiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiittlere Taste");
    b3.setMnemonic(KeyEvent.VK_E);
    b3.setActionCommand("enable");
    b3.setEnabled(false);

    b1.addActionListener(this);
    b3.addActionListener(this);

    add(b1);
    add(b2);
    add(b3);
  }

  public void actionPerformed(ActionEvent e) {
    if ("disable".equals(e.getActionCommand())) {
      b2.setEnabled(false);
      b1.setEnabled(false);
      b3.setEnabled(true);
    } else {
      b2.setEnabled(true);
      b1.setEnabled(true);
      b3.setEnabled(false);
    }
  }

  private static void createAndShowGUI() {
    JFrame frame = new JFrame("Wer wird INFORMATIKär: Tasten-Demonstration");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    TastenDemo newContentPane = new TastenDemo();
    newContentPane.setOpaque(true);
    frame.setContentPane(newContentPane);
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
}
