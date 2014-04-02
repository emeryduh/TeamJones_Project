package mainpackage;

import mainpackage.Screens.GameScreen;
import mainpackage.SpriteClass;

public class AI {
	
	GameScreen gameScreen;
	private Battle battle;
	private static SpriteClass spriteClass = new SpriteClass();
	private static int aiAttackRange = 75;
	
	private static int curActionPlayer2 = 1;
	
	final static int IDLE_RIGHT = 0;
	final static int IDLE_LEFT = 1;
	final static int RUN_RIGHT = 2;
	final static int RUN_LEFT = 3;
	
	public static int runLogic(int playerXPos, int player2XPos)
	{
		
		if(playerXPos < player2XPos-aiAttackRange)
		{
			player2XPos -= 4;
			curActionPlayer2 = RUN_LEFT;
			
			
		}
		
		
		return player2XPos;
		
		/*
		if(isAIFacingLeft)
		{
			//Move towards player
			while(gameScreen.playerXPos <= aiPosition-aiAttackRange)
			{
				
				//if player is in the attack range, attack
				if(gameScreen.playerXPos == aiPosition-aiAttackRange)
				{
					//attack();

					//if AI health is lower than the player health, run away
					if(battle.aiHealth < battle.player1Health)
					{
						aiPosition += gameScreen.moveSpeed;
						
						//after a 2 second delay, walk towards player again
						if(gameScreen.timer == gameScreen.timer+2)
						{
							aiPosition -= gameScreen.moveSpeed;
						}
						
					}
				}
				//else, move towards the player to get in the attack range
				else
				{
					aiPosition -= gameScreen.moveSpeed;
				}
			}
		}
		else
		{
			//Move towards player
			while(gameScreen.playerXPos >= aiPosition+aiAttackRange)
			{
				
				//if player is in the attack range, attack
				if(gameScreen.playerXPos == aiPosition-aiAttackRange)
				{
					//attack();
					
					//if AI health is lower than the player health, run away
					if(battle.aiHealth < battle.player1Health)
					{
						aiPosition -= gameScreen.moveSpeed;
						
						//after a 2 second delay, walk towards player again
						if(gameScreen.timer == gameScreen.timer+2)
						{
							aiPosition += gameScreen.moveSpeed;
						}
					}
				}
				//else, move towards the player to get in the attack range
				else
				{
					aiPosition += gameScreen.moveSpeed;
				}
				
			}*/
		}
	
		
	}

	
	


