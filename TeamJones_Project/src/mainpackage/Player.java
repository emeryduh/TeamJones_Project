package mainpackage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {

	private int player1HP = 100;
	private int player2HP = 100;
	public boolean defend;
	public Object sprite;
	
	public Player() {
		
	}
	
	public int getPlayer1HP()
	{
		return player1HP;
	}
	
	public int getPlayer2HP()
	{
		return player2HP;
	}
}
