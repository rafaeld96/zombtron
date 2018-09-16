package zombtron;

/**
 * Inherits the enemy class and is an abstract class for the boss enemy.
 * This class has a constructor which makes it so that the boss will not be removed even if it spwawns too close to the player.
 * This is to ensure the important boss monster will spawn when it should (its position is randomized until on an appropriate location).
 * @author Rafael
 *
 */
public abstract class BossMonster extends ChasingEnemy
{
	
	public BossMonster ()
	{
		rewardFromThisObject = true;
		
		double x = (Math.random() * 760 + 20);
		double y = (Math.random() * 560 + 20);
		
		double x2 = Player.getPlayer().getPosition()[0];
		double y2 = Player.getPlayer().getPosition()[1];
		
		double distance = Math.sqrt((x-x2)*(x-x2) + (y-y2)*(y-y2));
		
		while (distance < 100)
		{
			x = (Math.random() * 760 + 20);
			y = (Math.random() * 560 + 20);
			
			distance = Math.sqrt((x-x2)*(x-x2) + (y-y2)*(y-y2));
			
		}
		
		setPosition(x,y);
		setBoundingBox(x,y,getImageIcon().getIconWidth(),getImageIcon().getIconHeight());		
		setHealth(20);
	}
	
}
