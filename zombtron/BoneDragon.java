package zombtron;

import javax.swing.ImageIcon;

/** Boss monster which walks towards the enemy like a regular zombie and shoots flame waves just like the way
 * necromancers shoot bullets.
 * When killed, the variable bossMonsterKilled is increased by one. When bossMonsterKilled = 3, then
 * gameWin is initiated before when the current wave is over.
 * 
 * The bone dragon is the only boss monster in the game. It uses the chasing
 * function from ChasingEnemy to kill the player by collision (instantly kills player)
 * and also shoots fires at the player.
 * @author Rafael
 *
 */
public class BoneDragon extends BossMonster
{
	private static final double BONE_DRAGON_SPEED = 0.65;
	private static final int BONE_DRAGON_TIME_BETWEEN_SHOTS = 60;
	
	public static ImageIcon boneDragon = new ImageIcon("bonedragon.png");

	private int countdown;
	
	public ImageIcon getImageIcon() 
	{
		return boneDragon;
	}

	public double getObjectSpeed() 
	{
		return BONE_DRAGON_SPEED;
	}
	public BoneDragon()
	{
		if(rewardFromThisObject)
			ON_DEATH_GIVE_PLAYER_SCORE = 300;
		onCollideDoDamage = 5;
	}
    private boolean shootCooldownUp() 
    {
        if(countdown < 1)
            return true;
        else
            return false;
    }
	public void update()
	{
		super.update();
		
		double xDistance = Player.getPlayer().getPosition()[0] - getPosition()[0];
		double yDistance = Player.getPlayer().getPosition()[1] - getPosition()[1];
		
		if(shootCooldownUp())
		{
			if (Math.abs(xDistance) > Math.abs(yDistance) && xDistance > 0)
			{
				GameArea.storeList.add(new BoneDragonFire("ImageFacingRight",DIRECTION_RIGHT,0,getPosition()[0],getPosition()[1]));
				countdown = BONE_DRAGON_TIME_BETWEEN_SHOTS;
			}

			if (Math.abs(xDistance) > Math.abs(yDistance) && xDistance < 0)
			{
				GameArea.storeList.add(new BoneDragonFire("ImageFacingLeft",DIRECTION_LEFT,0,getPosition()[0],getPosition()[1]));
				countdown = BONE_DRAGON_TIME_BETWEEN_SHOTS;
			}

			if (Math.abs(xDistance) < Math.abs(yDistance) && yDistance > 0)
			{
				GameArea.storeList.add(new BoneDragonFire("ImageFacingDown",0,DIRECTION_DOWN,getPosition()[0],getPosition()[1]));
				countdown = BONE_DRAGON_TIME_BETWEEN_SHOTS;
			}

			if (Math.abs(xDistance) < Math.abs(yDistance) && yDistance < 0)
			{
				GameArea.storeList.add(new BoneDragonFire("ImageFacingUp",0,DIRECTION_UP,getPosition()[0],getPosition()[1]));   
				countdown = BONE_DRAGON_TIME_BETWEEN_SHOTS;
			}			
		}
		else 
			countdown -=1;
	} 
}
