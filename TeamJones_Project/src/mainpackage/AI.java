package mainpackage;

import mainpackage.Screens.GameScreen;
import mainpackage.SpriteClass;

public class AI {
	
	private GameScreen gameScreen;
	private Battle battle;
	private static SpriteClass spriteClass = new SpriteClass();
	private static int aiAttackRange = 100;
	private static int moveSpeedP2 = 4;
	
	public static int curActionP2 = 0;
	
	final static int IDLE_RIGHT = 0;
	final static int IDLE_LEFT = 1;
	final static int RUN_RIGHT = 2;
	final static int RUN_LEFT = 3;
	final static int ATTACK_RIGHT = 4;
	final static int ATTACK_LEFT = 5;
	
	public static int runLogic(int playerXPos, int player2XPos)
	{
		
		
		if(playerXPos < player2XPos - aiAttackRange)
		{
			
			player2XPos -= moveSpeedP2;
			curActionP2 = RUN_LEFT;
			
		}
		else if(playerXPos > player2XPos - aiAttackRange)
		{
			player2XPos += moveSpeedP2;
			curActionP2 = RUN_RIGHT;
		}
		else if(playerXPos == player2XPos - aiAttackRange)
		{
			curActionP2 = ATTACK_LEFT;
		}
		else
		{
			curActionP2 = IDLE_LEFT;
		}
		
		
		
		return player2XPos;
		
		}
		
	}

	
	


