package zombtron;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Timer;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.*;

/**
 * This class is the JPanel in which the game plays out. The structure is the following:
 * Three linked lists a) one for the key inputs, b) one for all of the objects inside of the JPanel,
 * c) one is a "storage list" which will add the stored objects into the object list.
 * An inner class MyKeyListener implements KeyListener to listen for key inputs, which are put into the list keyPressedList
 * GamArea keeps track of different constants relevant to zombtron like ending, change in difficulty, each wave, etc.
 * GameArea is also the class in which the loop for the game is executed. This is in form of a timer called gameTimer, which loops after 1000/FRAME_RATE seconds,
 * meaning the game has an FPS of FRAME_RATE. In this loop, objects are all updated and then objects are removed from the list (if their health == 0), and if no enemies remain, 
 * all objects in the list except for the player objet is removed and the method initiateNewRound() is called which begins the next "wave", which will be incrementally
 * more difficult than the previous wave, dictated by the constant DIFFICULTY_INCREASE.
 * @author Rafael
 * 
 */
public class GameArea extends JPanel implements ActionListener
{
	static final long serialVersionUID = 42L;

	public static final int FRAME_RATE = 60;
	public static final int TIME_BETWEEN_FRAMES = 1000/FRAME_RATE;
	public static final int DIFFICULTY_INCREASE = 3;
	public static final int DIFFICULTY_START = 5;

	public static final int LEFT_EDGE = 15;
	public static final int RIGHT_EDGE = 785;
	public static final int UPPER_EDGE = 15;
	public static final int BOTTOM_EDGE = 585;

	public static final int UP_KEY = KeyEvent.VK_UP;
	public static final int DOWN_KEY = KeyEvent.VK_DOWN;
	public static final int LEFT_KEY = KeyEvent.VK_LEFT;
	public static final int RIGHT_KEY = KeyEvent.VK_RIGHT;

	public static LinkedList<Integer> keyPressedList = new LinkedList<Integer>();
	public static Iterator<Integer> keyPressedIterator;

	public static LinkedList<GameObject> storeList = new LinkedList<GameObject>();
	public static Iterator<GameObject> storeIterator;

	public static LinkedList<GameObject> objectList = new LinkedList<GameObject>();
	public static Iterator<GameObject> objectIterator;

	public static int enemyUnitCount = 0;
	public static int bossMonsterKilled = 0;
	public static int playerScore = 0;
	public static boolean gameWin = false;
	public static boolean gameLost = false;

	private static int difficultyIncrements = 0;
	private static int gameAreaLevel = 0;

	Timer gameTimer = new Timer(TIME_BETWEEN_FRAMES, this);


	public void paintComponent(Graphics g) 
	{
		String status = Integer.toString(Player.getPlayer().getHealth());

		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.YELLOW);
		g2.fill(Player.getPlayer().getBoundingBox());
		if(Player.getPlayer().getHealth() < 1)
			status = "Zombie Food";

		g.setColor(Color.RED);

		for(GameObject object : objectList)
		{
			if(object.getClass() == DeathTrap.class)
				g2.fill(object.getBoundingBox());
		}

		g.setColor(Color.YELLOW);

		for(GameObject object : objectList)
		{
			object.draw(g);
		}
		if(!gameWin)
		{
			g.drawString("Player health: " + status + " Player score: " + playerScore + " Current wave: " + gameAreaLevel, 10, 20);
			g.drawString("Player health: " + status, 10, 20);
			g.drawString("Bosses defeated: " + bossMonsterKilled, 680, 20);
			if(gameAreaLevel < 8)
				g.drawString("Goal: Defeat three boss monsters!", 375, 20);
			else if(bossMonsterKilled == 3)
				g.drawString("Goal: Survive!", 375, 20);
			else
				g.drawString("Goal: Boss monsters will start appearing now!", 375, 20);
		}
		if(gameWin)
		{
			g.setColor(Color.BLACK);
			g2.fill(Player.getPlayer().getBoundingBox());
			//Player.player = new ImageIcon(); 
			for(GameObject object : objectList)
			{
				if(object.getClass() == Player.class)
				{
					object.draw(g);
					break;
				}
			}
			g2.fill(Player.getPlayer().getBoundingBox());
			g.setColor(Color.YELLOW);
			g.drawString("You have defeated an impressive three zombie bosses!", 220, 70);
			g.drawString("Destroying three of the zombie forces most valuable and fearsome", 220, 110);
			g.drawString("leaders, has undoubtedly crippled their forces to such a degree,", 220, 130);
			g.drawString("that the humans now might stand a chance against", 220, 150);
			g.drawString("the never-ending horrors of the zombies. Perhaps - even -", 220, 170);
			g.drawString("a crusade to permanently vanquish the zombie horde just might begin... ", 220, 190);
			g.drawString("And it is all thanks to you. Well done, hero.", 220, 230);
		}
		if(gameLost)
		{
			g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 36)); 
			g.drawString("You died. Restart.", 120, 90);
			g.setColor(Color.BLACK);
			g2.fill(Player.getPlayer().getBoundingBox());
		}
	}


	public GameArea(){

		setFocusable(true);
		setPreferredSize(new Dimension(800,600));
		MyKeyListener keyListener = new MyKeyListener();
		addKeyListener(keyListener);

		gameTimer.start();
		objectList.add(Player.getPlayer());
	}

	public void actionPerformed(ActionEvent a)
	{                
		System.out.println("actionPerformed");
		for(GameObject storedObject : storeList)
		{
			objectList.addLast(storedObject);
		}
		storeList.clear();
		for (GameObject object : objectList)
		{
			object.update();
		}
		objectIterator = objectList.iterator();
		while(objectIterator.hasNext())
		{
			GameObject gameobject = objectIterator.next();
			if(gameobject.getHealth() < 1)
			{
				if(gameobject instanceof Enemy)
				{
					if (!(gameobject instanceof ZombieSpawnerMinion) && !(gameobject instanceof Trap))
						enemyUnitCount --;
					if(gameobject.rewardFromThisObject)
					{
						playerScore += gameobject.ON_DEATH_GIVE_PLAYER_SCORE;    				
						if(gameobject instanceof BossMonster)
							bossMonsterKilled ++;
					}
				}
				objectIterator.remove();          
			}
		}

		if(enemyUnitCount == 0 )
		{
			objectIterator = objectList.iterator();
			while(objectIterator.hasNext())
			{
				if(objectIterator.next().getClass() != Player.class)
				{
					objectIterator.remove();
				}
			}
			initiateNewRound();        	
		}

		repaint();
	}

	public void initiateNewRound() 
	{
		difficultyIncrements += DIFFICULTY_INCREASE;
		gameAreaLevel ++;
		gameTimer.stop();
		if(!gameLost)
		{
			if(!(bossMonsterKilled == 3))
			{
				gameTimer.setInitialDelay(1000);
				spawnUnits(DIFFICULTY_START + difficultyIncrements);
				gameTimer.restart();
			}
			else
				gameWon();
		}
	}

	public void spawnUnits(int spawnCoefficient)
	{
		if (Math.random() * 600 < 450 && gameAreaLevel > 4)
		{
			GameArea.objectList.add(new SlowTrap());
		}
		for(int i = 0; i < spawnCoefficient*2;i++) 
		{
			if(Math.random() * 600 < 70)
				GameArea.objectList.add(new DeathTrap());
		}

		for(int i = 0; i < spawnCoefficient*2;i++) 
		{
			if(Math.random() * 600 < 225)
			{
				GameArea.objectList.add(new Walker());
			}

			if(Math.random() * 600 < 30 && gameAreaLevel > 2)
			{
				GameArea.objectList.add(new StrongWalker());
			}

			if(Math.random() * 600 < 20 && gameAreaLevel > 3)
			{
				GameArea.objectList.add(new Necromancer());
			}

			if(Math.random() * 600 < 12.5 && gameAreaLevel > 5)
			{
				GameArea.objectList.add(new ZombieSpawner());
			}

		}
		if(Math.random() * 600 < 250 && gameAreaLevel > 8)
		{
			GameArea.objectList.add(new BoneDragon()); //BOSS SPAWN
		}

	}

	public void gameWon()
	{
		objectIterator = objectList.iterator();
		while(objectIterator.hasNext())
		{
			objectIterator.next();
			objectIterator.remove();

		}

		gameWin = true;
		repaint();
	}
	private class MyKeyListener implements KeyListener 
	{

		public void keyPressed(KeyEvent e)
		{
			if(!keyPressedList.contains(e.getKeyCode()))
			{
				keyPressedList.add(e.getKeyCode());
			}
		}
		public void keyReleased(KeyEvent e)
		{
			keyPressedList.removeFirstOccurrence(e.getKeyCode());
		}
		public void keyTyped(KeyEvent e) 
		{

		}

	}


}


