package mainpackage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {
	
	private static boolean isPaused = false;
	public static int player1HP = 100;
	public static int player2HP = 100;
	public boolean defend;
	public Object sprite;
	
	public Player() {
		
	}
	
	public static int getPlayer1HP()
	{
		return player1HP;
	}
	
	public static int getPlayer2HP()
	{
		return player2HP;
	}
	
	/**
	 * Sets the pause state of the GameScreen
	 * @param paused
	 */
	public static void setPauseState(boolean paused) {
		isPaused = paused;
	}
	
	/**
	 * Gets the pause state of the GameScreen
	 * @return
	 */
	public static boolean getPausedState() {
		return isPaused;
	}
}
