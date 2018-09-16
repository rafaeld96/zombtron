package zombtron;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

/**
 * This object is the player. It takes variables stored in keyPressedList and sets a certain direction accordingly, as well as
 * shooting bullets according to what arrowkeys are pressed down (the bullets direction is the same as the arrowkey pressed).
 * The player has only one instance of it created, so therefore I made it into a singleton class with one static instance of the object being reachable with
 * getPlayer(). 
 * @author Rafael
 *
 */
public class Player extends GameObject
{
	private static final int PLAYER_MOVE_UP_KEY = KeyEvent.VK_W;
	private static final int PLAYER_MOVE_DOWN_KEY = KeyEvent.VK_S;
	private static final int PLAYER_MOVE_LEFT_KEY = KeyEvent.VK_A;
	private static final int PLAYER_MOVE_RIGHT_KEY = KeyEvent.VK_D;
    private static final int PLAYER_TIME_BETWEEN_SHOTS = 10;
    
  //This was originally 5 instead of 50, but since you might just want to test the game without stressing the difficulty, I upped it a bit. :)
    private static final int MAX_PLAYER_HEALTH = 5; 
    
    public static double PLAYER_SPEED = 2;
    
    private int countdown = 0;
	
	private static Player instance = null;
	
	public static Player getPlayer()
	{
		if (instance == null)
			instance = new Player();
		return instance;
	}
    
    public static ImageIcon player = new ImageIcon("player.png"); 
    
    public ImageIcon getImageIcon() 
    {
        return player;  
    }
    public double getObjectSpeed()
    {
        return PLAYER_SPEED;
    }
    
    private boolean shootCooldownUp() 
    {
        if(countdown < 1)
            return true;
        else
            return false;
    }
    
    public Player()
    {    	
    	double x = Math.random() * 770 + 15;
    	double y = Math.random() * 570 + 15;
    	setPosition(x,y);
    	
    	setHealth(MAX_PLAYER_HEALTH);
    }
    
    public void update()
    {
    	super.update();
    	
        //COLLSION WITH EDGE FIX
        if(getPosition()[0]<GameArea.LEFT_EDGE)
            setPosition(GameArea.LEFT_EDGE,getPosition()[1]);
        if(getPosition()[0]>GameArea.RIGHT_EDGE)
            setPosition(GameArea.RIGHT_EDGE,getPosition()[1]);
        if(getPosition()[1]<GameArea.UPPER_EDGE)
            setPosition(getPosition()[0],GameArea.UPPER_EDGE);
        if(getPosition()[1]>GameArea.BOTTOM_EDGE)
            setPosition(getPosition()[0],GameArea.BOTTOM_EDGE); 
        
        if(getHealth() < 1)
        	GameArea.gameLost = true;
        
        for (Integer key : GameArea.keyPressedList)
        {
            if(shootCooldownUp())
            {
                if(key == GameArea.LEFT_KEY)
                {
                    GameArea.storeList.add(new Bullet(DIRECTION_LEFT,0,getPosition()[0],getPosition()[1]));
                    countdown = PLAYER_TIME_BETWEEN_SHOTS;
                    break;
                }
                
                if(key == GameArea.RIGHT_KEY)
                {
                    GameArea.storeList.add(new Bullet(DIRECTION_RIGHT,0,getPosition()[0],getPosition()[1]));
                    countdown = PLAYER_TIME_BETWEEN_SHOTS;
                    break;
                }
                
                if(key == GameArea.UP_KEY)
                {
                    GameArea.storeList.add(new Bullet(0,DIRECTION_UP,getPosition()[0],getPosition()[1]));
                    countdown = PLAYER_TIME_BETWEEN_SHOTS;
                    break;
                }
                
                if(key == GameArea.DOWN_KEY)
                {
                    GameArea.storeList.add(new Bullet(0,DIRECTION_DOWN,getPosition()[0],getPosition()[1]));   
                    countdown = PLAYER_TIME_BETWEEN_SHOTS;
                    break;
                }
            }
            else 
            {
                countdown -=1;
                break;
            }
            
        }
    }
    public void determineDirection()
    {
        int x = 0;
        int y = 0;
        
        for(Integer key : GameArea.keyPressedList)
        {
            if(key == PLAYER_MOVE_LEFT_KEY)
                x--;
            if(key == PLAYER_MOVE_RIGHT_KEY)
                x++;
            if(key == PLAYER_MOVE_UP_KEY)
                y--;
            if(key == PLAYER_MOVE_DOWN_KEY)
                y++;
        }
        
        setDirection(x,y);
    }
}