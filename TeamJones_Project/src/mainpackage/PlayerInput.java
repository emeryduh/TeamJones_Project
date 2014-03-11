package mainpackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerInput implements KeyListener 
{

	private int gameScreen;

	public PlayerInput() 
	{
	}

	public void menuOrGame(int gameScreen) 
	{
		this.gameScreen = gameScreen;
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int keyPressed = e.getKeyCode();

		if (gameScreen == 0) 
		{
			switch (keyPressed) 
			{
			case KeyEvent.VK_LEFT:
				break;
			case KeyEvent.VK_RIGHT:
				break;
			case KeyEvent.VK_ENTER:
				break;
			}
		}
		else
		{
			switch (keyPressed) 
			{
			case KeyEvent.VK_LEFT:
				break;
			case KeyEvent.VK_RIGHT:
				break;
			case KeyEvent.VK_UP:
				break;
			case KeyEvent.VK_DOWN:
				break;
			case KeyEvent.VK_M:
				break;
			case KeyEvent.VK_N:
				break;
			case KeyEvent.VK_B:
				break;
			case KeyEvent.VK_SPACE:
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
