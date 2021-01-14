
/**
 *
 * @author david
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Grille extends JPanel implements MouseListener {

    JCase buttons[][] = new JCase[16][16]; // 16 x 16 array

    public Grille() { // constructeur
        init();
    }

    // when the mouse is clicked call this method
    public void check(MouseEvent me) {
        for (int i = 0; i < buttons.length; i++) {
            for (int x = 0; x < buttons[i].length; x++) {
                if (me.getSource().equals(buttons[i][x])) {
                    //we found the button that was clicked
                    if (me.getButton() != MouseEvent.BUTTON3 && buttons[i][x].getIcon() == null) {
                        // If it was not a right click
                        System.out.println(buttons[i][x].getIcon());
                        reveal(i, x);
                    } else if(me.getButton() == MouseEvent.BUTTON3){
                        // if it was a right click
                        System.out.println(me.getButton());
                        if (buttons[i][x].isEnabled() && buttons[i][x].getIcon() == null) { // if it's not disabled we can put a flag
                            buttons[i][x].setIcon(new ImageIcon("imagesDemineur\\drapeau.gif"));
                        }else if(buttons[i][x].getIcon().toString().equals("imagesDemineur\\drapeau.gif")){
                            buttons[i][x].setIcon(new ImageIcon("imagesDemineur\\point.gif"));
                        }else{
                            buttons[i][x].setIcon(null);
                        }
                    }
                }
            }
        }
    }

    public void test() {
        for (JCase[] button : buttons) {
            for (int j = 0; j < buttons.length; j++) {
                if (button[j].getValue() != 9) {
                    button[j].setEnabled(false);
                    button[j].setIcon(false);
                }
            }
        }
    }

    // method to check if the client won (to be continued)
    public void checkWin() {
        int counter = 0;
        for (JCase[] button : buttons) {
            for (int j = 0; j < buttons.length; j++) {
                if (button[j].isEnabled()) {
                    counter++;
                }
            }
        }
        if (counter == 40) {
            clearBoard();
            message("Congratulations ! You've won !", "Game Win");
        }
    }

    // Reveal method 'When user clicks reveals all the free space'
    public void reveal(int x, int y) {
        checkWin();
        if (x < 0 || x > 15 || y < 0 || y > 15) {
            return;
        }
        // reveals all close numbers
        if (buttons[x][y].getValue() < 9 && buttons[x][y].isEnabled()) { // 
            buttons[x][y].setEnabled(false);
            buttons[x][y].setIcon(false);
            if (buttons[x][y].getValue() == 0) {
                reveal(x + 1, y);
                reveal(x - 1, y);
                reveal(x, y + 1);
                reveal(x, y - 1);
            }
        } else if (buttons[x][y].getValue() == 9) {// lose
            lose();
        }
    }

    // method to clear the board (win or lose)
    private void clearBoard() {
        for (JCase[] button : buttons) {
            for (int j = 0; j < buttons.length; j++) {
                button[j].setEnabled(false);
                button[j].setIcon(false);
            }
        }
    }

    // method to simplify messages
    public void message(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
    }

    // method we get when we lose
    private void lose() {
        clearBoard();
        message("\tYou lost !\n\nPress Reset to restart", "Game Lost");
    }

    // generate random Mines
    private void generateMines() {
        //int counter = 0;
        for (JCase[] button : buttons) {
            for (int j = 0; j < buttons.length; j++) {
                button[j].setValue(0);
            }
        }

        for (int i = 0; i < 40; i++) {
            int a = (int) (Math.random() * 256);

            if (buttons[a / 16][a % 16].getValue() != 9) {
                buttons[a / 16][a % 16].setValue(9);
            } else {
                i--;
            }
        }

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                if (buttons[i][j].getValue() != 9) {
                    buttons[i][j].setValue(calculValue(i, j));
                }
            }
        }

        // Print to see mines if everything is ok
        /*int counter = 0;
        for (JCase[] button : buttons) {
            for (int j = 0; j < buttons.length; j++) {
                if (button[j].getValue() == 9) {
                    counter++;
                }
                System.out.print(button[j].getValue() + " - ");
            }
            System.out.println();
        }

        System.out.println(counter);*/
    }

    private int calculValue(int cx, int cy) {
        int counter = 0;
        for (int x = cx - 1; x <= cx + 1; x++) {
            for (int y = cy - 1; y <= cy + 1; y++) {
                try {
                    if (buttons[x][y].getValue() == 9) {
                        counter++;
                    }
                } catch (ArrayIndexOutOfBoundsException ae) {
                    // if we go out of bounds -> do nothing
                }
            }
        }
        return counter;
    }

    // init buttons
    public void init() {
        for (JCase[] button : buttons) {
            for (int j = 0; j < buttons.length; j++) {
                button[j] = new JCase(); // initialize buttons
                button[j].addMouseListener(this);
                button[j].setEnabled(true);
                add(button[j]); // add buttons to the grille (JPanel)
            }
        }
        generateMines();
        addMouseListener(this); // listen to mouse actions on the Grille ( JPanel )
    }

    // mouse events
    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON3) {
            // right mouse clicked
            check(me);
        } else {
            // check which button was pressed ( Left Click)
            check(me);
        }

    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    // method that resets the game
    public void preparerJeu() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                buttons[i][j].setEnabled(true);
                buttons[i][j].setIcon(true);
            }
        }
        generateMines();
    }
}
