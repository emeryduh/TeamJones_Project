package mainpackage.Screens;

import mainpackage.Game;
import mainpackage.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameScreen implements Screen {
	// variables
	private Game game;
	private SpriteBatch batch;
	private BitmapFont font;
	private Player player;

	// constructor to keep a reference to the main Game class
	public GameScreen(Game game) {
		super();
		this.game = game;
		// player = new Player(select);
	}

	// called when the screen should render itself
	public void render(float delta) {
		batch.begin();
		/*font.setColor(Color.WHITE);
		font.draw(batch, "Game Screen", Gdx.graphics.getWidth() / 2 - 50,
				Gdx.graphics.getHeight() - 50);
		player.render(batch);*/
		batch.end();

	}

	// called when the screen resized
	public void resize(int width, int height) {
	}

	// called when this screen becomes the current screen for a Game
	public void show() {
		batch = new SpriteBatch();
		font = new BitmapFont();

	}

	// called when current screen changes from this to a different screen
	public void hide() {
	}

	// called when game paused
	public void pause() {
	}

	// called when game resume
	public void resume() {

	}

	// called when this screen should release all resources
	public void dispose() {
		batch.dispose();
	}

}
