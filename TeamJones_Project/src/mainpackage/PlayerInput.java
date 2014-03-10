package mainpackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class PlayerInput {
	
	
	public PlayerInput()
	{
	}
	
	public boolean wasDownPressed()
	{
		boolean isDownPressed = Gdx.input.isKeyPressed(Keys.DOWN);
		return isDownPressed;
	}

	public boolean wasUpPressed()
	{
		boolean isUpPressed = Gdx.input.isKeyPressed(Keys.UP);
		return isUpPressed;
	}
	
	public boolean wasEnterPressed()
	{
		boolean isEnterPressed = Gdx.input.isKeyPressed(Keys.ENTER);
		return isEnterPressed;
	}
}
