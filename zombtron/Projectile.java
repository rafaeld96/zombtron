package zombtron;

/**
 * Abstract class for all the projectiles for the game (BoneDragonFire, Bullet and NecromancerBullet). It includes the code for when each projectile
 * is removed when it's position goes beyond the borders of the JFrame.
 * @author Rafael
 *
 */
public abstract class Projectile extends GameObject
{
	  public void determineDirection()
	  {
		  //Direction (and location) is determined in respective objects constructor
	  }
	  
	  public void update()
	  {
		  super.update();	  
		  
		  if(getPosition()[0]<GameArea.LEFT_EDGE)
			  destroyObject();
		  if(getPosition()[0]>GameArea.RIGHT_EDGE)
			  destroyObject();
		  if(getPosition()[1]<GameArea.UPPER_EDGE)
			  destroyObject();
		  if(getPosition()[1]>GameArea.BOTTOM_EDGE)
			  destroyObject();
	  }
	
	
	
	
	
	
	
	
	
	
	
	
}
