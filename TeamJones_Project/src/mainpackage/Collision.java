package mainpackage;

import mainpackage.Screens.GameScreen;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;



public class Collision {
	
	private static int punchDamage = 10;
	private static int kickDamage = 20;
	
	private static TextureRegion player1TextureRegion = GameScreen.player1TextureRegion;
	private static TextureRegion player2TextureRegion = GameScreen.player2TextureRegion;
	
	private static Rectangle player1CollisionRect;
	private static Rectangle player2CollisionRect;
	
	
	
	
	
	
	
	public static void punch()
	{
		
		player1CollisionRect = new Rectangle(player1TextureRegion.getRegionX(), player1TextureRegion.getRegionY(),
				player1TextureRegion.getRegionWidth(), player1TextureRegion.getRegionHeight());
		
		player2CollisionRect = new Rectangle(player2TextureRegion.getRegionX(), player2TextureRegion.getRegionY(),
				player2TextureRegion.getRegionWidth(), player2TextureRegion.getRegionHeight());
		
		if(player1CollisionRect.contains(player2CollisionRect))
		{
			Player.player2HP -= punchDamage;
		}
	}
	
	public static void kick()
	{
		
		player1CollisionRect = new Rectangle(player1TextureRegion.getRegionX(), player1TextureRegion.getRegionY(),
				player1TextureRegion.getRegionWidth(), player1TextureRegion.getRegionHeight());
		
		player2CollisionRect = new Rectangle(player2TextureRegion.getRegionX(), player2TextureRegion.getRegionY(),
				player2TextureRegion.getRegionWidth(), player2TextureRegion.getRegionHeight());
		
		if(player1CollisionRect.contains(player2CollisionRect))
		{
			Player.player2HP -= kickDamage;
		}
	}

	
	
	
	
	
	

}
