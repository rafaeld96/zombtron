package zombtron;

/**
 *  This class is for all the enemies (except boss) that chase the player using the same code. The code calculates whether the players position variables are bigger
 *  or smaller than it's own and goes towards a certain direction depending on that.
 * @author Rafael
 *
 */
public abstract class ChasingEnemy extends Enemy
{
	
	public void determineDirection()
	{
		int x = 0;
		int y = 0;
		
		if(Player.getPlayer().getPosition()[0] < getPosition()[0])
			x--;
		if(Player.getPlayer().getPosition()[0] > getPosition()[0])
			x++;
		if(Player.getPlayer().getPosition()[1] < getPosition()[1])
			y--;
		if(Player.getPlayer().getPosition()[1] > getPosition()[1])
			y++;
		
		setDirection(x,y);	
	}	
}
