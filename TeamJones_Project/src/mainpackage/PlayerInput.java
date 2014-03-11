package mainpackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerInput implements KeyListener 
{
	//Keeps track of which type of gameScreen is currently on screen
	private int gameScreen;

	//Constructor
	public PlayerInput() 
	{
	}

	//Takes an integer argument, 0: Any of the menus, 1: Battle screen 
	//This method must be called in every screen that requires keyboard input
	public void menuOrGame(int gameScreen) 
	{
		this.gameScreen = gameScreen;
	}

	//Handles keyboard input
	@Override
	public void keyPressed(KeyEvent e) 
	{
		int keyPressed = e.getKeyCode();

		//If the current game screen is a menu type screen, the following controls are configured
		if (gameScreen == 0) 
		{
			switch (keyPressed) 
			{
			case KeyEvent.VK_UP: //Allows user to move menu cursor up
				break;
			case KeyEvent.VK_DOWN://Allows user to move menu cursor down
				break;
			case KeyEvent.VK_ENTER://Allows user to select the option the cursor is currently on
				break;
			}
		}
		else
		{
			//If the current game screen is the battle screen, the following controls are configured
			switch (keyPressed) 
			{
			case KeyEvent.VK_LEFT://Allows user to move the character left
				break;
			case KeyEvent.VK_RIGHT: //Allows user to move the character right
				break;
			case KeyEvent.VK_UP: //Allows user to make the character jump
				break;
			case KeyEvent.VK_DOWN://Allows user to make the character duck
				break;
			case KeyEvent.VK_M://Allows user to make the character attack
				break;
			case KeyEvent.VK_N: //Allows user to make the character block
				break;
			case KeyEvent.VK_B://Allows user to make the character use their special attack
				break;
			case KeyEvent.VK_SPACE://Allows user to make the character charge energy
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
	}

}
