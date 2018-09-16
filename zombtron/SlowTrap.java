package zombtron;

import javax.swing.ImageIcon;

/**
 * If the player steps on this trap, then he will be slowed significantly. This object has the method move() but has no direction nor speed.
 * @author Rafael
 *
 */
public class SlowTrap extends Trap
{
	
	public static ImageIcon slowTrap = new ImageIcon("slowtrap.png");
	
	
	public ImageIcon getImageIcon()
	{
		return slowTrap;
	}

    public void update()
    {
    	super.update();
		if(getBoundingBox().intersects(Player.getPlayer().getBoundingBox()))
		{
			Player.PLAYER_SPEED = 0.5; // Player is slowed when going on this trap
		}
		else if (!(getHealth() < 1)) Player.PLAYER_SPEED = 2;
    }
	
	
	
	
	
	
	
	
	
	
}