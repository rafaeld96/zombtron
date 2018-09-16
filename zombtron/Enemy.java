package zombtron;

/**
 * All enemies in the game inherits this class. This includes the traps which you can walk, even though they cannot be killed with bullets.
 * This class has a constructor which gives enemies that spawn a random position and calculates if it is too close to the player.
 * If they are too close, they will be put outside the map and destroyed shortly thereafter.
 * Also adds to the variable enemyUnitCount in GameArea, to keep track on the amount of units in the map (so that when all are dead, a new round initiates).
 * Not all enemies count as "enemies" in this sense, however; traps and the ZombieSpawnMinion spawned by ZombieSpawner will not be required to be killed for
 * the next round to start.
 * @author Rafael
 *
 */
public abstract class Enemy extends GameObject 
{		
	public int onCollideDoDamage = 0;
	public int damageTakenOnCollide = 0;

	public Enemy()
	{
		double x = (Math.random() * 760 + 20);
		double y = (Math.random() * 560 + 20);

		double x2 = Player.getPlayer().getPosition()[0];
		double y2 = Player.getPlayer().getPosition()[1];

		double distance = Math.sqrt((x-x2)*(x-x2) + (y-y2)*(y-y2));

		GameArea.enemyUnitCount ++;

		setPosition(x,y);
		setBoundingBox(x,y,getImageIcon().getIconWidth(),getImageIcon().getIconHeight());		

		if(distance < 100)
		{
			destroyObject();
			rewardFromThisObject = false;
			setPosition(OUTSIDE_AREA,OUTSIDE_AREA);
			setBoundingBox(OUTSIDE_AREA,OUTSIDE_AREA,getImageIcon().getIconWidth(),getImageIcon().getIconHeight());	
		}
	}		
	public void update()
	{
		super.update();
		if(getBoundingBox().intersects(Player.getPlayer().getBoundingBox()))
		{
			takeDamage(damageTakenOnCollide);
			Player.getPlayer().takeDamage(onCollideDoDamage);
			rewardFromThisObject = false;
		}
	}
}
