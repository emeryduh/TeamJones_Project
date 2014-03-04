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

	private Game game;
	private SpriteBatch batch;
	private BitmapFont font;
	private Player player;

	public GameScreen(Game game, String select) {
		super();
		this.game = game;
		player = new Player(select);
		
	}


	@Override
	public void render(float delta) {
		ShapeRenderer s = new ShapeRenderer();
		s.begin(ShapeType.Filled);

		// background
		s.setColor(Color.BLACK);
		s.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		s.end();

		batch.begin();

		font.setColor(Color.WHITE);
		font.draw(batch, "Game Screen", Gdx.graphics.getWidth() / 2 - 50,
				Gdx.graphics.getHeight() - 50);
		
		player.render(batch);

		batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		// System.out.println("batch created");

		font = new BitmapFont();

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
