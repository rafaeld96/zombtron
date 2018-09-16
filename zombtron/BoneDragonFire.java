package zombtron;

import javax.swing.ImageIcon;
/**
 * Projectile type object which shoots towards one direction until it goes off-map. This projetile has different images
 * depending on what direction is has when it is fired. This function of changing the imageicon is unique to this object, and therefore
 * it is only written in this class.
 * @author Rafael
 *
 */
public class BoneDragonFire extends Projectile
{

	private static final double BONE_DRAGON_FIRE_SPEED = 2.25;

	private ImageIcon boneDragonFire;

	public ImageIcon getImageIcon() 
	{
		return boneDragonFire;  
	}
	public double getObjectSpeed()
	{
		return BONE_DRAGON_FIRE_SPEED;
	}

	public BoneDragonFire(String faceDirection, int xDirection, int yDirection,double x, double y) 
	{
		setDirection(xDirection,yDirection);
		setPosition(x,y);

		if(faceDirection == "ImageFacingLeft")
			boneDragonFire = new ImageIcon("bonedragonfire_facingleft.png");
		if(faceDirection == "ImageFacingRight")
			boneDragonFire = new ImageIcon("bonedragonfire_facingright.png");
		if(faceDirection == "ImageFacingUp")
			boneDragonFire = new ImageIcon("bonedragonfire_facingup.png");
		if(faceDirection == "ImageFacingDown")
			boneDragonFire = new ImageIcon("bonedragonfire_facingdown.png");

	}

	public void update()
	{
		super.update();
		for(GameObject object : GameArea.objectList)
		{
			if(this.getBoundingBox().intersects(object.getBoundingBox()) || this.getBoundingBox().intersects(Player.getPlayer().getBoundingBox()))
			{
				if(object instanceof Player)
				{
					Player.getPlayer().takeDamage(1);
					this.destroyObject();	
				}
				if(object instanceof Bullet)
				{
					object.takeDamage(1);	
				}
			}
		}
	}
}