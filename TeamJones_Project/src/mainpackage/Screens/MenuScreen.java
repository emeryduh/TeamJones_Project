package mainpackage.Screens;

import mainpackage.Game;
import mainpackage.SoundFiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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

public class MenuScreen implements Screen {

	// variables
	private Game game;
	private Stage stage;
	private BitmapFont whiteFont, blackFont;
	private TextureAtlas atlas;
	private Skin skin;
	private SpriteBatch batch;
	private TextButton btnStartGame;
	private TextButton btnOptions;
	private Texture splashTexture;
	private Music openingMusic;
	private SoundFiles soundFiles;

	// constructor to keep a reference to the main Game class
	public MenuScreen(Game game) {
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

	}

	// called when the screen resized
	public void resize(int width, int height) {
		if (stage == null) {
			stage = new Stage(width, height, true);
		}

		stage.clear();
		Gdx.input.setInputProcessor(stage);

		// set the background image to the menu screen
		splashTexture = new Texture(
				Gdx.files.internal("assets/gui/mainMenuBG.png"));
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image img = new Image(new TextureRegion(splashTexture));
		// fit the full image to the screen
		img.setFillParent(true);
		// adds an actor to the root of the stage.
		stage.addActor(img);

		// creates start game button
		TextButtonStyle txtStartgameStyle = new TextButtonStyle();
		txtStartgameStyle.up = skin.getDrawable("button");
		txtStartgameStyle.down = skin.getDrawable("buttonpressed");
		txtStartgameStyle.font = blackFont;
		btnStartGame = new TextButton("Start Game", txtStartgameStyle);
		btnStartGame.setWidth(140f);
		btnStartGame.setHeight(40f);
		btnStartGame.setX(600);
		btnStartGame.setY(130);

		// start game events
		btnStartGame.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// this is where functionality for next screen should be called
				// play menu select sound
				soundFiles = new SoundFiles();
				soundFiles.playSound("menuSelect");
				// goto Char Select Screen
				game.setScreen(new CharacterSelectScreen(game));
			}
		});

		// creates the option button
		TextButtonStyle txtOptionStyle = new TextButtonStyle();
		txtOptionStyle.up = skin.getDrawable("button");
		txtOptionStyle.down = skin.getDrawable("buttonpressed");
		txtOptionStyle.font = blackFont;
		btnOptions = new TextButton("Options", txtOptionStyle);
		btnOptions.setWidth(140f);
		btnOptions.setHeight(40f);
		btnOptions.setX(600);
		btnOptions.setY(60);

		// option button events
		btnOptions.addListener(new InputListener() {

			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// this is where functionality for next screen should be called
				soundFiles = new SoundFiles();
				soundFiles.playSound("menuSelect");
				game.setScreen(new OptionScreen(game));
				openingMusic.stop();
			}
		});

		// adds an start game and option actors to the root of the stage.
		stage.addActor(btnStartGame);
		stage.addActor(btnOptions);
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
		openingMusic = Gdx.audio.newMusic(Gdx.files
				.internal("assets/audioFiles/menuSounds/mainMenuMusic.mp3"));
		openingMusic.play();
		openingMusic.setLooping(true);

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
		openingMusic.dispose();
	}

	// method to return the key selection
	public void keyDown(int keycode) {
		if (keycode == Keys.UP) {

			return;
		}
		if (keycode == Keys.DOWN) {

			return;
		}
		if (keycode == Keys.ENTER) {
			game.setScreen(new CharacterSelectScreen(game));
			return;
		}
	}
}
