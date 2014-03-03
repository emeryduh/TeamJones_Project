package mainpackage.Screens;

import mainpackage.Game;
import mainpackage.Battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class ScoreScreen implements Screen {

	// variables
	private Game game;
	private Stage stage;
	private BitmapFont whiteFont, blackFont;
	private TextureAtlas atlas;
	private Skin skin;
	private SpriteBatch batch;
	private TextButton btnNewMatch;
	private TextButton btnMenu;
	private Texture splashTexture;
	private Battle battle = new Battle();
	int winner = battle.gameOutcome();

	// constructor to keep a reference to the main Game class
	public ScoreScreen(Game game) {
		this.game = game;
		
	}
	

	// called when the screen should render itself.
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		// draw objects into the screen
		batch.begin();
		stage.draw();
		batch.end();
		batch.begin();
		blackFont.setColor(0,0,0,1);
		if (winner == 1)
		{
		blackFont.draw(batch, "You Win!" , 300, 600);
		}
		else if (winner == 2)
		{
			blackFont.draw(batch, "You Lose!", 300, 600);
		}
		else 
		{
			CharSequence str = "Draw!";
			blackFont.draw(batch, str, 300, 600);
		}
		batch.end();
	}
	
	// called when the screen resized
	public void resize(int width, int height) {
		if (stage == null) {
			stage = new Stage(width, height, true);
		}

		stage.clear();
		Gdx.input.setInputProcessor(stage);

		// set the background image to the menu screen
		splashTexture = new Texture(Gdx.files.internal("assets/gui/bg.png"));
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image img = new Image(new TextureRegion(splashTexture));
		// fit the full image to the screen
		img.setFillParent(true);
		// adds an actor to the root of the stage.
		stage.addActor(img);

		// creates new match button
		TextButtonStyle txtNewMatchStyle = new TextButtonStyle();
		txtNewMatchStyle.up = skin.getDrawable("button");
		txtNewMatchStyle.down = skin.getDrawable("buttonpressed");
		txtNewMatchStyle.font = blackFont;
		btnNewMatch = new TextButton("New Match", txtNewMatchStyle);
		btnNewMatch.setWidth(140f);
		btnNewMatch.setHeight(40f);
		btnNewMatch.setX(600);
		btnNewMatch.setY(130);

		// new match events
		btnNewMatch.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// this is where functionality for next screen should be called
				//game.setScreen(new SplashScreen(game));
			}
		});

		// creates the menu button
		TextButtonStyle txtMenuStyle = new TextButtonStyle();
		txtMenuStyle.up = skin.getDrawable("button");
		txtMenuStyle.down = skin.getDrawable("buttonpressed");
		txtMenuStyle.font = blackFont;
		btnMenu = new TextButton("Main Menu", txtMenuStyle);
		btnMenu.setWidth(140f);
		btnMenu.setHeight(40f);
		btnMenu.setX(600);
		btnMenu.setY(60);

		// menu button events
		btnMenu.addListener(new InputListener() {
			
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new MenuScreen(game));
			}
		});

		// adds an new match and menu actors to the root of the stage.
		stage.addActor(btnNewMatch);
		stage.addActor(btnMenu);
	}
	
	// called when this screen becomes the current screen for a Game.
	public void show() {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("assets/gui/button.pack");
		skin = new Skin();
		skin.addRegions(atlas);
		whiteFont = new BitmapFont(
				Gdx.files.internal("assets/gui/whitefont.fnt"), false);
		blackFont = new BitmapFont(
				Gdx.files.internal("assets/gui/blackfont.fnt"), false);
	}

	// called when current screen changes from this to a different screen
	public void hide() {		

	}

	// called when game paused.
	public void pause() {		

	}

	// called when game resume
	public void resume() {		

	}
	
	// called when this screen should release all resources.
	public void dispose() {
		batch.dispose();
		skin.dispose();
		atlas.dispose();
		stage.dispose();
	}
}
