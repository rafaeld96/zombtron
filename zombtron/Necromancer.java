package zombtron;

import javax.swing.ImageIcon;

/**
 * This object has a very different moving pattern. It will move towards the closest vertical or horizontal line to the player's x and respective y coordinate that
 * it can achieve, while simultaneously moving away from the player if the player is within a certain distance (200 units).
 * Periodically, dictated by the constant timeBetweenObjectCreate, it will shoot NecromancerBullets towards the player. 
 * 
 * Also, Necromancer is different in the way it's boundingBox is. Since the ImageIcon is of a person stretching his hands out (making his "body" very big), I've
 * made it so that the area that can be shot with bullets is his width/2*height. Since this class runs away from the character, it might run into the edges, and so
 * a mechanism to avoid that is in place, with the edges being not exactly 600 in x and 800 in y because of its own image size and position.
 * @author Rafael
 *
 */
public class Necromancer extends SummonerEnemy
{

	public static final double NECROMANCER_SPEED = 0.75;
	private static final int maxHealth = 3;
	
	public static ImageIcon necromancer = new ImageIcon("necromancer.png");
	
	public ImageIcon getImageIcon() 
	{
		return necromancer;
	}

	public double getObjectSpeed() 
	{
		return NECROMANCER_SPEED;
	}
	public Necromancer()
	{
		timeBetweenObjectCreate = 75;
		randomizeFirstSpawn(); //This is to make necromancers not shoot exactly at the same time
		if(rewardFromThisObject)
		{
			ON_DEATH_GIVE_PLAYER_SCORE = 70;
			setHealth(maxHealth);
		}
		
	}
	public void determineDirection()
	{
		int x = 0;
		int y = 0;
		
		double xDistance = Player.getPlayer().getPosition()[0] - getPosition()[0];
		double yDistance = Player.getPlayer().getPosition()[1] - getPosition()[1];

		double distance = Math.sqrt((xDistance)*(xDistance) + (yDistance)*(yDistance));

		if (Math.abs(xDistance) > Math.abs(yDistance) && yDistance > 0)
			y++;
		if (Math.abs(xDistance) > Math.abs(yDistance) && yDistance < 0)  
			y--;
		if (Math.abs(xDistance) < Math.abs(yDistance) && xDistance > 0)
			x++;
		if (Math.abs(xDistance) < Math.abs(yDistance) && xDistance < 0)
			x--;
		
		if(distance < 200)
		{
			if (Math.abs(xDistance) > Math.abs(yDistance) && xDistance > 0)
				x--;
			if (Math.abs(xDistance) > Math.abs(yDistance) && xDistance < 0)
				x++;
			if (Math.abs(xDistance) < Math.abs(yDistance) && yDistance > 0)
				y--;
			if (Math.abs(xDistance) < Math.abs(yDistance) && yDistance < 0)
				y++;
		}
		
		setDirection(x,y);
	}
	
	public void update()
	{
		super.update();
		//SO THIS ENEMY DOES NOT GO BEYOND THE GAME AREA and no closer than 30 units from the edge
        if(getPosition()[0]<GameArea.LEFT_EDGE+30)
            setPosition(GameArea.LEFT_EDGE+30,getPosition()[1]);
        if(getPosition()[0]>GameArea.RIGHT_EDGE-30)
            setPosition(GameArea.RIGHT_EDGE-30,getPosition()[1]);
        if(getPosition()[1]<GameArea.UPPER_EDGE+30)
            setPosition(getPosition()[0],GameArea.UPPER_EDGE+30);
        if(getPosition()[1]>GameArea.BOTTOM_EDGE-30)
            setPosition(getPosition()[0],GameArea.BOTTOM_EDGE-30); 
        
        //Only this object is designed to have a different width on its bounding box
        //therefore it is written specifically here.
        setBoundingBox(getPosition()[0],getPosition()[1],getImageIcon().getIconWidth()/2,getImageIcon().getIconHeight()); 
	}
	public void doSummonerAction()
	{
		double xDistance = Player.getPlayer().getPosition()[0] - getPosition()[0];
		double yDistance = Player.getPlayer().getPosition()[1] - getPosition()[1];
		
		if (Math.abs(xDistance) > Math.abs(yDistance) && xDistance > 0)
		{
			GameArea.storeList.add(new NecromancerBullet(DIRECTION_RIGHT,0,getPosition()[0],getPosition()[1]));
		}
		if (Math.abs(xDistance) > Math.abs(yDistance) && xDistance < 0)
		{
			GameArea.storeList.add(new NecromancerBullet(DIRECTION_LEFT,0,getPosition()[0],getPosition()[1]));
		}
		if (Math.abs(xDistance) < Math.abs(yDistance) && yDistance > 0)
		{
			GameArea.storeList.add(new NecromancerBullet(0,DIRECTION_DOWN,getPosition()[0],getPosition()[1]));
		}
		if (Math.abs(xDistance) < Math.abs(yDistance) && yDistance < 0)
		{
			GameArea.storeList.add(new NecromancerBullet(0,DIRECTION_UP,getPosition()[0],getPosition()[1]));   
		}			

	}
}