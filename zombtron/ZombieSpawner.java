package zombtron;

import javax.swing.ImageIcon;

/**
 * This unit is a SummonerEnemy that creates ZombieSpawnerMinions periodically. Cannot move. Stores the created object firstly in a storeList.
 * @author Rafael
 *
 */
public class ZombieSpawner extends SummonerEnemy
{
	public static final double ZOMBIE_SPAWNER_SPEED = 0;
	private static final int maxHealth = 8;
	
	public static ImageIcon zombieSpawner = new ImageIcon("zombiespawner.png");
	
	public ImageIcon getImageIcon() 
	{
		return zombieSpawner;
	}
	public double getObjectSpeed() 
	{
		return ZOMBIE_SPAWNER_SPEED;
	}
	public void determineDirection()
	{
		//Has no direction since it doesn't move.
	}
	public ZombieSpawner()
	{
		timeBetweenObjectCreate = 90;
		randomizeFirstSpawn();
		if (rewardFromThisObject)
		{
			setHealth(maxHealth);
			ON_DEATH_GIVE_PLAYER_SCORE = 150;
		}
	}
	@Override
	public void doSummonerAction()
	{
		GameArea.storeList.add(new ZombieSpawnerMinion(getPosition()[0],getPosition()[1]));
	}
}
