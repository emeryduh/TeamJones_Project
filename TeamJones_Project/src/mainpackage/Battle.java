package mainpackage;

import mainpackage.Screens.ScoreScreen;

public class Battle 
{
	public int player1Health = 0;
	public int aiHealth = 0;
	int player1Wins = 0;
	int aiWins = 0;
	int winner = 2;
	int timer = 60;
	private Game game;
	
	public Battle (Game game)
	{
		this.game = game;
	}
	
	public void startMatch() 
	{
		player1Health = 100;
		aiHealth = 100;
	}
	
	public void matchOutcome() 
	{
		
		if ((player1Wins < 2) && (aiWins  < 2))
		{
			if (timer > 0)
			{
				if (player1Health == 0) 
				{
					aiWins++;
				}
				if (aiHealth == 0)
				{
					player1Wins++;
				}
			}
			if (timer == 0) 
			{
				if (player1Health < aiHealth)
				{
					aiWins++;
				}
				if (player1Health > aiHealth)
				{
					player1Wins++;
				}
			}
			//startMatch();
		}
		else 
		{
			gameOutcome();
			game.setScreen(new ScoreScreen(game));
		}
	}
		
		
	public int gameOutcome()
	{
		
		if (player1Wins == 2) 
		{
			winner = 1;
		}
		if (aiWins == 2) 
		{
			winner = 2;
		}
		return winner;
	}
}
