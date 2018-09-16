package zombtron;

import javax.swing.ImageIcon;

/**
 * A stronger and bigger, but slower, version of the normal walker. It works basically identical to the walker except changes in it's variables.
 * @author Rafael
 *
 */
public class StrongWalker extends ChasingEnemy
{
	private static final double STRONG_WALKER_SPEED = 0.37;
	private static final int maxHealth = 4;
	
	public static ImageIcon strongWalker = new ImageIcon("zombie_fat.png");

	public ImageIcon getImageIcon() 
	{
		return strongWalker;
	}
	public double getObjectSpeed() 
	{
		return STRONG_WALKER_SPEED;
	}
	public StrongWalker()
	{
		if(rewardFromThisObject)
		{
			ON_DEATH_GIVE_PLAYER_SCORE = 40;
			setHealth(maxHealth);
		}
		onCollideDoDamage = 3;
		damageTakenOnCollide = maxHealth;
	}
	public void update()
	{
		super.update();	
	}
}