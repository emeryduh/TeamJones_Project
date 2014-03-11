package mainpackage;

import mainpackage.Screens.ScoreScreen;

public class Battle 
{
	public int player1Health = 0;
	public int player2Health = 0;
	int player1Wins = 0;
	int player2Wins = 0;
	int winner = 2;
	int timer = 0;
	private Game game;
	
	public Battle (Game game)
	{
		this.game = game;
	}
	
	public void startMatch() 
	{
		player1Health = 100;
		player2Health = 100;
	}
	
	public void matchOutcome() 
	{
		
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
		if (player2Wins == 2) 
		{
			winner = 2;
		}
		return winner;
	}
}
