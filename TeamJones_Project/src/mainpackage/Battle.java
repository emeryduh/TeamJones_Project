package mainpackage;

public class Battle 
{
	
	public void matchOutcome() 
	{
		int player1Health = 0;
		int player2Health = 0;
		int player1Wins = 0;
		int player2Wins = 0;
		int Winner = 0;
		int timer = 0;
		if ((player1Wins < 2) && (player2Wins  < 2))
		{
			if (timer > 0)
			{
				if (player1Health == 0) 
				{
					player2Wins++;
				}
				if (player2Health == 0)
				{
					player1Wins++;
				}
			}
			if (timer == 0) 
			{
				if (player1Health < player2Health)
				{
					player2Wins++;
				}
				if (player1Health > player2Health)
				{
					player1Wins++;
				}
				else 
				{
				}
			}	
		startNextMatch(player1Wins, player2Wins);
		}
			
		if ((player1Wins == 2) || (player2Wins == 2))
		{
			if (player1Wins == 2) 
			{
				Winner = 1;
			}
			if (player2Wins == 2) 
			{
				Winner = 2;
			}
			scoreScreen(Winner);
		
		}
	}
}
