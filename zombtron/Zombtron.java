package zombtron;

import javax.swing.JFrame;

/**
 * Zombtron is the class containing the main method. It is a JFrame which contains the GameArea JPanel, in which the game plays out.
 * @author Rafael
 *
 */
public class Zombtron extends JFrame
{
	static final long serialVersionUID = 42L;
   
    public Zombtron ()
    {
        add(new GameArea()); //Create a JPanel GameArea() inside of the JFrame Zombtron()
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //Makes it so the location of the window that is created is going to be situated in the middle of the screen
        setVisible(true); //Makes it so that the JPanel itself is visible
    }
    
    
    public static void main(String [ ] args)
    {
		System.out.println("Got in");
        new Zombtron(); //Create an instance of the Zombtron JFrame, which also executes the code in the constructor Zombtron()
    }
        
}