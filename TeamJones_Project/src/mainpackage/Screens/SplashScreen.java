package mainpackage.Screens;

import mainpackage.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen {

	// variables
	private Texture splashTexture;
	private Sprite splashSprite;
	private SpriteBatch batch;
	private Game game;
	
	// constructor to keep a reference to the main Game class
	public SplashScreen(Game game) {
		this.game = game;
	}

	// called when the screen should render itself.
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);		
		batch.begin();
		splashSprite.draw(batch);
		batch.end();
	}

	// called when the screen resized
	public void resize(int width, int height) {

	}

	// called when this screen becomes the current screen for a Game.
	public void show() {
		splashTexture = new Texture(Gdx.files.internal("assets/gui/bg.png"));
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		splashSprite = new Sprite(splashTexture);		
		splashSprite.setX(Gdx.graphics.getWidth() / 2
				- (splashSprite.getWidth() / 2));
		splashSprite.setY(Gdx.graphics.getHeight() / 2
				- (splashSprite.getHeight() / 2));
		batch = new SpriteBatch();
	}

	// called when current screen changes from this to a different screen
	public void hide() {
		dispose();
	}

	// called when game paused.
	public void pause() {

	}

	// called when game resume
	public void resume() {

	}

	// called when this screen should release all resources.
	public void dispose() {
		splashTexture.dispose();
		batch.dispose();
	}

}
