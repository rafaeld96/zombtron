package zombtron;

import javax.swing.ImageIcon;

/**
 * The players weapon. Bullets are fired whenever the player has an arrowkey pressed.
 * Direction depends on which arrowkey. This unit collides with all units that are of type enemy and reduces their health with 1.
 * It collides with necromancer bullets and bone dragon fire as well, but doesn't budge on the bone dragon's fire.
 * @author Rafael
 *
 */
public class Bullet extends Projectile
{
  
  public static final double BULLET_SPEED = 10;
  
  public static ImageIcon bullet = new ImageIcon("bullet.png");
  
  public ImageIcon getImageIcon() 
  {
  return bullet;  
  }
  public double getObjectSpeed()
  {
   return BULLET_SPEED;
  }
  
  public Bullet(int xDirection, int yDirection,double x, double y) 
  {
    setDirection(xDirection,yDirection);
    setPosition(x,y);
    
  }
  public void update()
  {
	  super.update();

	  for(GameObject object : GameArea.objectList)
	  {
		  if(object.getBoundingBox().intersects(this.getBoundingBox()))
		  {
			  if(object instanceof Enemy && !(object instanceof Trap))
			  {
				  object.takeDamage(1);
				  this.destroyObject();
			  }
		  }
      }
      
  }
    
    
    
}