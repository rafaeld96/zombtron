package zombtron;

import javax.swing.ImageIcon;

/**
 * This object is created by the Necromancer. It is a bullet that will collide with the classes Player and Bullet.
 * It reduces the health of the colliding object with 1 and calls this.destroyObject() simultaneously.
 * @author Rafael
 *
 */
public class NecromancerBullet extends Projectile
{
  
  public static final double NECROMANCER_BULLET_SPEED = 2.25;
  
  public static ImageIcon necromancerBullet = new ImageIcon("necromancerbullet.png");
  
  public ImageIcon getImageIcon() 
  {
  return necromancerBullet;  
  }
  public double getObjectSpeed()
  {
   return NECROMANCER_BULLET_SPEED;
  }
  public void determineDirection()
  {
	//Direction is determined when object is constructed
  }
  
  public NecromancerBullet(int xDirection, int yDirection,double x, double y) 
  {
    setDirection(xDirection,yDirection);
    setPosition(x,y);
    
  }
  public void update()
  {
	  super.update();
	  
	  for(GameObject object : GameArea.objectList)
	  {
		  if(this.getBoundingBox().intersects(object.getBoundingBox()) || this.getBoundingBox().intersects(Player.getPlayer().getBoundingBox()))
		  {
			  if(object instanceof Player || object instanceof Bullet)
			  {
				  if(object.getClass() == Player.class)
					  object.takeDamage(1);
				  else
					  object.takeDamage(1);
				  this.destroyObject();	
			  }
		  }
      }
      
  }
    
    
    
}