package zombtron;

/**
 * Superclass for traps SlowTrap and DeathTrap. Just contains a small amount of code to make them look cleaner and structurize it all.
 * @author Rafael
 *
 */
public abstract class Trap extends Enemy 
{
	public static final int TRAP_SPEED = 0;

	public double getObjectSpeed()
	{
		return TRAP_SPEED;
	}
	public void determineDirection()
	{
		//Has no direction since traps don't move.
	}
	public Trap()
	{
		GameArea.enemyUnitCount --; //Traps do not count as enemies in terms of unit count
	}
}
