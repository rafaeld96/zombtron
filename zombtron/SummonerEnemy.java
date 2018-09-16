package zombtron;

/**
 * Superclass for the clases that create new objects perodically. This is for the ZombieSpawner and the Necromancer. Basically, it is a loop with 
 * countdown and spawnCooldownUp() working to dictate the amount of time between each call, 
 * and doSummonerAction() being the code to execute after countdown reaches zero.
 * @author Rafael
 *
 */
public abstract class SummonerEnemy extends Enemy
{
	public static int timeBetweenObjectCreate;

	private int countdown;
	
	public SummonerEnemy()
	{
		countdown = (int) Math.random() * timeBetweenObjectCreate;
	}
	
	
    private boolean spawnCooldownUp() 
    {
        if(countdown < 1)
            return true;
        else
            return false;
    }
    
	public void update()
	{
		super.update();
		if(spawnCooldownUp())
		{
			doSummonerAction();
			countdown = timeBetweenObjectCreate;
		}
		else 
			countdown --;
	}	
	public void doSummonerAction()
	{
		
	}
	public void randomizeFirstSpawn()
	{
		countdown = (int) Math.random() * timeBetweenObjectCreate; //This is to make zombiespawners not spawn exactly at the same time
	}
}
