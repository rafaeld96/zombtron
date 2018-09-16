package zombtron;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

/**
 * Every object in the game extends this class. This class has methods for retrieving each objects current position, to set its position, 
 * it promises that every object has an image icon, a certain speed and a variable determineDirection. It has many getters and setters of 
 * certain variables that all class inheriting this class also have, most notably direction, position boundingBox and health.
 * 
 * Most important methods are update(), move() and draw(), as they are the fundamentals of all movement and imaging.
 * 
 * Collision is dependant on the boundingBox variable. boundingBox is a variable of type Rectangle2D which can use the method intersect
 * to check for collision with other rectangles. This is responsible for all collision in the game. The position and size of the rectangle depends solely
 * on each particular instance of GameObject.
 * 
 * How to setup the boundingBox and ImageIcon to suit the position of each object was a bit tricky, so many different small code changes were made, 
 * such as changing the position of the boundingBox not to be equal to each objects respective position (instead slightly translated in both x and y-axis). 
 * @author Rafael
 *
 */
public abstract class GameObject
{
    public static final int DIRECTION_LEFT = -1;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_UP = -1;
    public static final int DIRECTION_DOWN = 1;
    
	public static final int OUTSIDE_AREA = -80;
	
	public int ON_DEATH_GIVE_PLAYER_SCORE = 0;
	public boolean rewardFromThisObject = true;
	
    public abstract ImageIcon getImageIcon();
    public abstract double getObjectSpeed();
    public abstract void determineDirection();
    
    private int[] direction = new int[2];
    private double[] position = new double[2];
    private Rectangle2D boundingBox = new Rectangle2D.Double();
    private int health = 1;
    
    public void draw(Graphics graphic)
    {
        int x = (int) position[0] - getImageIcon().getIconWidth()/2;
        int y = (int) position[1] - getImageIcon().getIconHeight()/2;
        
        graphic.drawImage(getImageIcon().getImage(),x,y, null);
    }
    public void move()
    {        
        double x= 0;
        double y= 0;
        
        x = getPosition()[0] + getDirection()[0]*getObjectSpeed();
        y = getPosition()[1] + getDirection()[1]*getObjectSpeed();
                
        setPosition(x,y);
    }
    public void update()
    {
    	determineDirection();
        move();
        setBoundingBox(getPosition()[0],getPosition()[1],getImageIcon().getIconWidth(),getImageIcon().getIconHeight()); 
    }
    public void takeDamage(int damageToTake)
    {
    	setHealth(health - damageToTake);
    }
    public void destroyObject()
    {
       health = 0;
    }
    
    public double[] getPosition ()
    {
        return position;
    }
    
    public void setPosition(double x, double y)
    {
        position[0] = x;
        position[1] = y;
    }
    public int[] getDirection()
    {
        return direction;
    }
    public void setDirection(int x, int y)
    {
        direction[0] = x;
        direction[1] = y;
    }
    public Rectangle2D getBoundingBox()
    {
    	return boundingBox;
    }
    public void setBoundingBox(double x, double y, double width, double height)
    {
    	boundingBox.setRect(x-width/2, y-height/2, width, height);
    }
    public int getHealth()
    {
        return health;
    }
    
    public void setHealth(int changeToThis)
    {
        health = changeToThis;
    }
}