
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author david
 */
public class JCase extends JButton {

    // attributs
    private Icon icons[] = new Icon[12]; // 0 = pas de mine | 1 à 8 = mine proche |  9 = mine
    private int value;

    public JCase() { // constructor init images
        value = 0;
        icons[1] = new ImageIcon("imagesDemineur\\un.gif");
        icons[2] = new ImageIcon("imagesDemineur\\deux.gif");
        icons[3] = new ImageIcon("imagesDemineur\\trois.gif");
        icons[4] = new ImageIcon("imagesDemineur\\quatre.gif");
        icons[5] = new ImageIcon("imagesDemineur\\cinq.gif");
        icons[6] = new ImageIcon("imagesDemineur\\six.gif");
        icons[7] = new ImageIcon("imagesDemineur\\sept.gif");
        icons[8] = new ImageIcon("imagesDemineur\\huit.gif");
        icons[9] = new ImageIcon("imagesDemineur\\bombe.gif");
        // partiel
        icons[10] = new ImageIcon("imagesDemineur\\drapeau.gif");
        icons[11] = new ImageIcon("imagesDemineur\\point.gif");
    }

    // METHOD TO SET ICON (TRUE) IF NEW GAME
    public void setIcon(boolean newOrNot) { 
        if (newOrNot) {
            setIcon(icons[0]);
            setDisabledIcon(icons[0]);
        } else {
            setIcon(icons[value]);
            setDisabledIcon(icons[value]);

        }
    }
    
    // setter
    public void setValue(int value) {
        this.value = value;
    }
    
    // getter
    public int getValue() {
        return value;
    }
    

    
}
