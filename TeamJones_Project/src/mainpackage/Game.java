package mainpackage;

import mainpackage.Screens.SplashScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class Game implements ApplicationListener {
	private Screen screen;
	public static final String VERSION = " 0.0.0 - PreAlpha";
	public static final String LOG = "Street Fighter";

	public void create() {
		Texture.setEnforcePotImages(false);
		setScreen(new SplashScreen(this));		
		//Gdx.app.log(LOG, "Create");
	}	

	public void render() {
		screen.render(Gdx.graphics.getDeltaTime());
	}

	public void resize(int width, int height) {

	}

	public void pause() {

	}

	public void resume() {

	}

	public void dispose() {

	}
	
	private void setScreen(SplashScreen splashScreen) {		
		this.screen = splashScreen;
		screen.show();
		screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
}
