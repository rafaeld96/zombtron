package zombtron;

import javax.swing.ImageIcon;

/**
 * A chasing enemy that has one health and does one damage when it collides with the player. 
 * Very simple unit, identical to the code used by Strongwalker (except varaibles)
 * @author Rafael
 *
 */
public class Walker extends ChasingEnemy
{

	private static final double WALKER_SPEED = 0.5;
	
	public static ImageIcon walker = new ImageIcon("zombie_resized.png");

	public ImageIcon getImageIcon() 
	{
		return walker;
	}

	public double getObjectSpeed() 
	{
		return WALKER_SPEED;
	}
	public Walker()
	{
		if(rewardFromThisObject)
			ON_DEATH_GIVE_PLAYER_SCORE = 10;
	    
		onCollideDoDamage = 1;
		damageTakenOnCollide = 1;
	}
	public void update()
	{
		super.update();		
	}
}