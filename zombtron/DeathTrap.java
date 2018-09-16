package zombtron;

import javax.swing.ImageIcon;

/**
 * If the player steps on this trap, then he will lose instantly. This object inherits the method move() but has no direction nor speed.
 * @author Rafael
 *
 */
public class DeathTrap extends Trap
{
	private static ImageIcon deathTrap = new ImageIcon("deathtrap.png");
	
	public ImageIcon getImageIcon()
	{
		return deathTrap;
	}
    public double getObjectSpeed()
    {
    	return TRAP_SPEED;
    }
    public DeathTrap()
    {
    	onCollideDoDamage = 5;
    }
}
