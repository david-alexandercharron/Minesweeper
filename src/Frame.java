
// imports
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author david
 */
public class Frame implements ActionListener { // class frame 

    JFrame frame = new JFrame("Minesweeper");
    JButton reset = new JButton("Reset");
    Grille grid = new Grille();

    //int counter[][] = new int[16][16];
    public Frame() {
        frame.setSize(400, 500); // size of frame
        frame.setLayout(new BorderLayout()); // 
        frame.add(reset, BorderLayout.NORTH); // reset button on the top with layout
        grid.setLayout(new GridLayout(16, 16)); // layout for game buttons

        reset.addActionListener(this);
        frame.add(grid, BorderLayout.CENTER); // put the layout in the middle
        frame.setResizable(false); // set frame not resizable

        frame.setLocationRelativeTo(null); // set frame to center of screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit on close
        frame.setVisible(true); // set visible( false by default )
    }

    public static void main(String[] args) { // main
        Frame frame = new Frame();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(reset)) { // reset button performed
            // reset
            grid.preparerJeu();
        }
    }

}
