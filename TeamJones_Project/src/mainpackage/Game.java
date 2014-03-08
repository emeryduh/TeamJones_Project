package mainpackage;

import mainpackage.Screens.MenuScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class Game implements ApplicationListener {
	
	// variables
	private Screen screen;
	// change the version number for every release
	public static final String VERSION = " 0.0.0 - PreAlpha";
	// log string for tracking 
	public static final String LOG = "Street Fighter";
	
	 

	// called when the Application is first created.
	public void create() {
		// whether to enforce power of two images in OpenGL ES 1.0 or not
		Texture.setEnforcePotImages(false);		
		
		setScreen(new MenuScreen(this));
		//Gdx.app.log(LOG, "Create");
		
		
	}	

	// called when the Application should render itself.
	public void render() {
		screen.render(Gdx.graphics.getDeltaTime());
	}

	// called when the Application is resized.
	public void resize(int width, int height) {

	}

	// called when the Application is paused.
	public void pause() {

	}

	// called when the Application is resumed from a paused state.
	public void resume() {

	}

	// called when the Application is destroyed.
	public void dispose() {
		screen.dispose();
	}
	
	// sets the current screen.
	public void setScreen(Screen screen) {		
		this.screen = screen;
		screen.show();
		screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
}
