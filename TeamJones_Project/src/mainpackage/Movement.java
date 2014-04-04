package mainpackage;

import com.badlogic.gdx.math.Rectangle;

public class Movement {
	
	private Game game;
	private Battle battle;
	private Player player;
	private Player player2;
	public Movement()
	{
		
		this.game = game;
		this.battle = battle;
		this.player = player;
			
	}
	
	
	//Punch method to be called from Input class when punch key has been hit
	public int Punch()
	{
		final int punchDamage = 20; //Punch Damage
		
		
		//Creating the rectangle for the attackers fist for collision detection
		Rectangle fistRectangle = new Rectangle();
		//fistRectangle.x = ((Rectangle) player.sprite).getX() + 50; //Offset value '50' to be adjusted later on
		//fistRectangle.y = ((Rectangle) player.sprite).getY() + 50; //Offset value '50' to be adjusted later on
		fistRectangle.width = 20; //Will be adjusted later on
		fistRectangle.height = 20; //Will be adjusted later on
		
		//Creating defenders' hit box rectangle for collision detection
		Rectangle hitBoxRectangle = new Rectangle();
		//hitBoxRectangle.x = ((Rectangle) player2.sprite).getX(); //X and Y values of player2 sprite
		//hitBoxRectangle.x = ((Rectangle) player2.sprite).getY();
		hitBoxRectangle.width = 50;
		hitBoxRectangle.height = 50;
		
		
		/*if(fistRectangle.contains(hitBoxRectangle) && player2.defend == true) //Added a placeholder boolean for opponent defend
		{
			return battle.player1Health - punchDamage/4;
		}
		else if(fistRectangle.contains(hitBoxRectangle) && player2.defend == false)
		{
			return battle.player1Health - punchDamage;
		}
		*/
	return battle.player1Health;
	}
	
	//Kick method to be called from Input class when punch key has been hit
	public int Kick()
	{
		final int kickDamage = 40; //Kick Damage
			
			
		//Creating the rectangle for the attackers foot for collision detection
		Rectangle kickRectangle = new Rectangle();
		//kickRectangle.x = ((Rectangle) player.sprite).getX() + 50; //Offset value '50' to be adjusted later on
		//kickRectangle.y = ((Rectangle) player.sprite).getY() + 100; //Offset value '100' to be adjusted later on
		kickRectangle.width = 20; //Will be adjusted later on
		kickRectangle.height = 20; //Will be adjusted later on
			
		//Creating defenders' hit box rectangle for collision detection
		Rectangle hitBoxRectangle = new Rectangle();
		//hitBoxRectangle.x = ((Rectangle) player2.sprite).getX(); //X and Y values of player2 sprite
		//hitBoxRectangle.x = ((Rectangle) player2.sprite).getY();
		hitBoxRectangle.width = 50;
		hitBoxRectangle.height = 50;
			
			
		/*if(kickRectangle.contains(hitBoxRectangle) && player2.defend == true) //Added a placeholder boolean for opponent defend
		{
			return battle.player1Health - kickDamage/4;
		}
		else if(kickRectangle.contains(hitBoxRectangle) && player2.defend == false)
		{
			return battle.player1Health - kickDamage;
		}
		*/		
	return battle.player1Health;
	}
		
}
