package mainpackage;

import mainpackage.Screens.MenuScreen;
import mainpackage.Screens.SplashScreen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

public class GameInputProcessor implements InputProcessor {
	
	// variables
	private Game game;
	
	// constructor to keep a reference to the main Game class
	public GameInputProcessor(Game game){
		super();
		this.game = game;
	}

	// called when a key was pressed
	public boolean keyDown(int keycode) {		
		Screen screen = game.getScreen();
		
		if(screen instanceof MenuScreen){
			((MenuScreen)screen).keyDown(keycode);
			return true;
		}		
		return false;
	}

	// Called when a key was released
	public boolean keyUp(int keycode) {		
		return false;
	}

	// called when a key was typed
	public boolean keyTyped(char character) {		
		return false;
	}

	// called when the screen was touched or a mouse button was pressed
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {		
		return false;
	}

	// called when a finger was lifted or a mouse button was released
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {		
		return false;
	}

	// called when a finger or the mouse was dragged.
	public boolean touchDragged(int screenX, int screenY, int pointer) {		
		return false;
	}

	// called when the mouse was moved without any buttons being pressed
	public boolean mouseMoved(int screenX, int screenY) {		
		return false;
	}

	// called when the mouse wheel was scrolled
	public boolean scrolled(int amount) {		
		return false;
	}

}
