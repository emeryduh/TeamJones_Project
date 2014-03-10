package mainpackage.Screens;

import mainpackage.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class CharacterSelectScreen implements Screen {
	SpriteBatch batch;
	private Sprite char_bya;
	private Sprite char_ich;

	private String select; // which character is selected {l, r}
	private Game game;
	private BitmapFont font;

	public CharacterSelectScreen (Game game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		// System.out.println("render menu");
		ShapeRenderer s = new ShapeRenderer();
		s.begin(ShapeType.Filled);
		// System.out.println("1");
		// background
		s.setColor(Color.WHITE);
		s.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		// System.out.println("2");
		// used to move the selection outline

		// draw the selection outline
		s.setColor(Color.BLACK);
		int width = 10;
		Sprite selectedSprite = char_bya;
		if (select == "bya") {
			selectedSprite = char_bya;
		} else if (select == "ich") {
			selectedSprite = char_ich;
		}
		s.rect(selectedSprite.getX() - width, selectedSprite.getY() - width,
				selectedSprite.getWidth() + width * 2,
				selectedSprite.getHeight() + width * 2);

		// System.out.println("3");
		s.end();
		// System.out.println("3.1");

		batch.begin();
		// System.out.println("3.2");
		char_bya.draw(batch);
		// System.out.println("3.3");
		char_ich.draw(batch);
		// System.out.println("3.4");

		font.setColor(Color.BLACK);
		font.draw(batch, "Menu Screen", Gdx.graphics.getWidth() / 2 - 50,
				Gdx.graphics.getHeight() - 50);
		font.draw(batch, "Press left/right to choose",
				Gdx.graphics.getWidth() / 2 - 50, 50);
		font.draw(batch, "Press Space to start game",
				Gdx.graphics.getWidth() / 2 - 50, 30);

		batch.end();
		// System.out.println("4");
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		// System.out.println("");
		// System.out.println("Load sprites");
		char_bya = new Sprite(Art.texture_bya);
		char_bya.setSize(100, 100);
		char_bya.setPosition(150, 150);
		// System.out.println("sprite 1 loaded");

		char_ich = new Sprite(Art.texture_ich);
		char_ich.setSize(100, 100);
		char_ich.setPosition(Gdx.graphics.getWidth() - 250, 150);
		// System.out.println("sprite 2 loaded");

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

	public void keyDown(int keycode) {
		if (keycode == Keys.RIGHT) {
			select = "ich";
			return;
		}
		if (keycode == Keys.LEFT) {
			select = "bya";
			return;
		}
		if (keycode == Keys.SPACE) {
			game.setScreen(new GameScreen(game, select));
			return;
		}

	}

}
