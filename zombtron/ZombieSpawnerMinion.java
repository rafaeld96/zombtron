package zombtron;

import javax.swing.ImageIcon;

/**
 * This class is an enemy that chases the player. It is solely made by ZombieSpawner and is given that class' position.
 * It isn't entirely necessary, but since this class is not a "real enemy", it isn't spawned at the start of the round, it doesn't add to the enemyUnitCount
 * and can be entirely ignored if the rest of the enemies can be destroyed. Doesn't give the player any score on death either.
 * @author Rafael
 *
 */
public class ZombieSpawnerMinion extends ChasingEnemy
{
	public static final double ZOMBIE_SPAWNER_MINION_SPEED = 0.75;
	
	public static ImageIcon zombieminion = new ImageIcon("zombieminion.png");

	public ImageIcon getImageIcon() 
	{
		return zombieminion;
	}
	public double getObjectSpeed() 
	{
		return ZOMBIE_SPAWNER_MINION_SPEED;
	}
	
	public ZombieSpawnerMinion(double xPosition, double yPosition)
	{
		ON_DEATH_GIVE_PLAYER_SCORE = 0;
		GameArea.enemyUnitCount --; //Minions, as these are, do not count towards enemyUnitCount
		
		setPosition(xPosition,yPosition);
		setBoundingBox(xPosition,yPosition,getImageIcon().getIconWidth(),getImageIcon().getIconHeight());	
		
		onCollideDoDamage = 1;
		damageTakenOnCollide = 1;
	}	
}
