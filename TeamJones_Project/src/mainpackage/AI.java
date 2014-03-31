package mainpackage;

import mainpackage.Screens.GameScreen;

public class AI {
	
	GameScreen gameScreen;
	private Battle battle;
	private int aiAttackRange = 25;
	
	
	public static int runLogic(int playerXPos, int player2XPos)
	{
		
		if(playerXPos < player2XPos)
		{
			player2XPos -= 4;
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
	
	


