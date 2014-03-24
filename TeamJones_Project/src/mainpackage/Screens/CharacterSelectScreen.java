package mainpackage.Screens;

import org.lwjgl.input.Keyboard;

import mainpackage.Game;
import mainpackage.PlayerInput;
import mainpackage.SoundFiles;
import mainpackage.TextureFiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class CharacterSelectScreen implements Screen {
	// variables
	private Game game;

	// sprite
	private SpriteBatch batch;
	private Stage stage;
	private Texture splashTexture;
	private BitmapFont whiteFont, blackFont;
	private TextureAtlas atlas;
	private Skin skin;
	private TextButton btnPlayGame;

	// music
	private Music charSelectionMusic;
	private SoundFiles soundFiles;

	// character selection
	private Texture p1Texture;
	private Texture p2Texture;
	private Image imgP1;
	private Image imgP2;
	private Image chP1;
	private Image chP2;
	private Image chP1Ready;
	private Image chP2Ready;
	private int ch1 = 0;
	private int ch2 = 0;
	private int ch1Index = 0;
	private int ch2Index = 0;

	// highlight the character
	private ShapeRenderer sRenderer;

	// constructor to keep a reference to the main Game class
	public CharacterSelectScreen(Game game) {
		this.game = game;
	}

	// called when the screen should render itself
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		// draw objects into the screen
		batch.begin();
		stage.draw();
		batch.end();

		// this will enable the continuous key press
		Keyboard.enableRepeatEvents(true);
	}

	// called when the screen resized
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		if (stage == null) {
			stage = new Stage(width, height, true);
		}

		stage.clear();
		// to enable the keyboard events
		PlayerInput playerInput = new PlayerInput(game);
		// set the input processor
		Gdx.input.setInputProcessor(playerInput);

		// set the background image to the menu screen
		splashTexture = TextureFiles.getBackgroundTexture("characterSelection");
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image img = new Image(new TextureRegion(splashTexture));
		// fit the full image to the screen
		img.setFillParent(true);
		// adds an actor to the root of the stage.
		stage.addActor(img);

		// creates the play game button
		TextButtonStyle txtPlayStyle = new TextButtonStyle();
		txtPlayStyle.up = skin.getDrawable("button");
		txtPlayStyle.down = skin.getDrawable("buttonpressed");
		txtPlayStyle.font = blackFont;
		btnPlayGame = new TextButton("Play", txtPlayStyle);
		btnPlayGame.setWidth(140f);
		btnPlayGame.setHeight(40f);
		btnPlayGame.setX(325);
		btnPlayGame.setY(235);
		btnPlayGame.toFront();
		btnPlayGame.setDisabled(true);
		stage.addActor(btnPlayGame);

		btnPlayGame.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				soundFiles = new SoundFiles();
				soundFiles.playSound("menuSelect");
				// hides/disables current screen
				hide();
				// go to Game Screen
				game.setScreen(new GameScreen(game));
			}
		});

		// texture for player1
		// default character is ichigo
		p1Texture = TextureFiles.getCharacterTexture("ichigo");
		p1Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		imgP1 = new Image(new TextureRegion(p1Texture));
		// adds an actor to the root of the stage.
		imgP1.setBounds(10, 50, 300, 550);
		stage.addActor(imgP1);

		// texture for player2
		// default character is renji
		p2Texture = TextureFiles.getCharacterTexture("renji");
		p2Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		imgP2 = new Image(new TextureRegion(p2Texture));
		// adds an actor to the root of the stage.
		imgP2.setBounds(480, 50, 300, 550);
		stage.addActor(imgP2);

		// ichigo texture
		Texture texIchigo = TextureFiles.getCharacterSelectionTexture("ichigo");
		texIchigo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image imgIchigo = new Image(new TextureRegion(texIchigo));
		// adds an actor to the root of the stage.
		imgIchigo.setBounds(20, 50, 70, 120);
		stage.addActor(imgIchigo);

		// byakuya texture
		Texture texByakuya = TextureFiles
				.getCharacterSelectionTexture("byakuya");
		texByakuya.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image imgByakuya = new Image(new TextureRegion(texByakuya));
		// adds an actor to the root of the stage.
		imgByakuya.setBounds(105, 50, 70, 120);
		stage.addActor(imgByakuya);

		// ikkaku texture
		Texture texIkkaku = TextureFiles.getCharacterSelectionTexture("ikkaku");
		texIkkaku.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image imgIkkaku = new Image(new TextureRegion(texIkkaku));
		// adds an actor to the root of the stage.
		imgIkkaku.setBounds(190, 50, 70, 120);
		stage.addActor(imgIkkaku);

		// ishida texture
		Texture texIshida = TextureFiles.getCharacterSelectionTexture("ishida");
		texIshida.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image imgIshida = new Image(new TextureRegion(texIshida));
		// adds an actor to the root of the stage.
		imgIshida.setBounds(275, 50, 70, 120);
		stage.addActor(imgIshida);

		// kenpachi texture
		Texture texKenpachi = TextureFiles
				.getCharacterSelectionTexture("kenpachi");
		texKenpachi.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image imgKenpachi = new Image(new TextureRegion(texKenpachi));
		// adds an actor to the root of the stage.
		imgKenpachi.setBounds(360, 50, 70, 120);
		stage.addActor(imgKenpachi);

		// urahara texture
		Texture texUrahara = TextureFiles
				.getCharacterSelectionTexture("urahara");
		texUrahara.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image imgUrahara = new Image(new TextureRegion(texUrahara));
		// adds an actor to the root of the stage.
		imgUrahara.setBounds(445, 50, 70, 120);
		stage.addActor(imgUrahara);

		// komamura texture
		Texture texkomamura = TextureFiles
				.getCharacterSelectionTexture("komamura");
		texkomamura.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image imgKomamura = new Image(new TextureRegion(texkomamura));
		// adds an actor to the root of the stage.
		imgKomamura.setBounds(530, 50, 70, 120);
		stage.addActor(imgKomamura);

		// mayuri texture
		Texture texMayuri = TextureFiles.getCharacterSelectionTexture("mayuri");
		texMayuri.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image imgMayuri = new Image(new TextureRegion(texMayuri));
		// adds an actor to the root of the stage.
		imgMayuri.setBounds(615, 50, 70, 120);
		stage.addActor(imgMayuri);

		// renji texture
		Texture texRenji = TextureFiles.getCharacterSelectionTexture("renji");
		texRenji.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image imgRenji = new Image(new TextureRegion(texRenji));
		// adds an actor to the root of the stage.
		imgRenji.setBounds(700, 50, 70, 120);
		stage.addActor(imgRenji);

		// player 1 selection texture
		Texture texP1 = TextureFiles.geUtilityTexture("player1");
		texP1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		chP1 = new Image(new TextureRegion(texP1));
		// adds an actor to the root of the stage.
		chP1.setBounds(0, 170, 100, 50);
		stage.addActor(chP1);

		// player 2 selection texture
		Texture texP2 = TextureFiles.geUtilityTexture("player2");
		texP2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		chP2 = new Image(new TextureRegion(texP2));
		// adds an actor to the root of the stage.
		chP2.setBounds(680, 0, 100, 50);
		stage.addActor(chP2);

		// player 1 ready texture
		Texture texP1Ready = TextureFiles.geUtilityTexture("ready");
		texP1Ready.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		chP1Ready = new Image(new TextureRegion(texP1Ready));
		// adds an actor to the root of the stage.
		chP1Ready.setBounds(10, 50, 300, 550);
		chP1Ready.setVisible(false);
		stage.addActor(chP1Ready);

		// player 2 ready texture
		Texture texP2Ready = TextureFiles.geUtilityTexture("ready");
		texP2Ready.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		chP2Ready = new Image(new TextureRegion(texP2Ready));
		// adds an actor to the root of the stage.
		chP2Ready.setBounds(480, 50, 300, 550);
		chP2Ready.setVisible(false);
		stage.addActor(chP2Ready);

		btnPlayGame.addListener(new InputListener() {

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
				// go to Game Screen
				hide();
				game.setScreen(new GameScreen(game));
			}
		});
	}

	// method to draw highlighted rectangle
	private void drawRect(float x, float y) {
		sRenderer = new ShapeRenderer();
		sRenderer.begin(ShapeType.Line); // background
		sRenderer.setColor(Color.RED);
		sRenderer.rect(x, y, 70, 150);
		sRenderer.end();
	}

	// called when this screen becomes the current screen for a Game
	public void show() {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("assets/gui/button.pack");
		skin = new Skin();
		skin.addRegions(atlas);
		whiteFont = new BitmapFont(
				Gdx.files.internal("assets/gui/whitefont.fnt"), false);
		blackFont = new BitmapFont(
				Gdx.files.internal("assets/gui/blackfont.fnt"), false);
		charSelectionMusic = Gdx.audio.newMusic(Gdx.files
				.internal("assets/audioFiles/menuSounds/charSelectMusic.mp3"));
		charSelectionMusic.play();
		charSelectionMusic.setLooping(true);
		charSelectionMusic.setVolume(this.game.masterVolume);
	}

	// called when current screen changes from this to a different screen
	public void hide() {
		charSelectionMusic.stop();
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
		skin.dispose();
		atlas.dispose();
		stage.dispose();
		charSelectionMusic.dispose();
	}

	// method to return the key selection
	public void keyDown(int keycode) {
		soundFiles = new SoundFiles();		
		switch(keycode)
		{
		case Keys.A: 
		{
			// return if prior than character 1
			if (ch1 <= 0 || chP1Ready.isVisible())
				break;
			// change index based on character selection
			ch1Index--;
			// value that change the texture position
			ch1 -= 85;
			// set new texture position
			chP1.setPosition(ch1, 170);
			// change the image texture
			imgP1.setDrawable(new SpriteDrawable(changeCharacter(ch1Index)));
			// play menu select sound			
			soundFiles.playSound("menuTraverse");
			break;
		}
		case Keys.D:  {
			// return if further than last character
			if (ch1 >= 680 || chP1Ready.isVisible())
				break;
			// change index based on character selection
			ch1Index++;
			// value that change the texture position
			ch1 += 85;
			// set new texture position
			chP1.setPosition(ch1, 170);
			// change the image texture
			imgP1.setDrawable(new SpriteDrawable(changeCharacter(ch1Index)));
			// play menu select sound
			soundFiles.playSound("menuTraverse");
			break;
		}
		case Keys.RIGHT:  {
			if (ch2 >= 680 || chP2Ready.isVisible())
				break;
			ch2Index++;
			// value that change the texture position
			ch2 += 85;
			// set new texture position
			chP2.setPosition(ch2, 0);
			// change the image texture
			imgP2.setDrawable(new SpriteDrawable(changeCharacter(ch2Index)));
			// play menu select sound
			soundFiles.playSound("menuTraverse");
			break;
		}
		case Keys.LEFT:  {
			if (ch2 <= 0 || chP2Ready.isVisible())
				break;
			// change index based on character selection
			ch2Index--;
			// value that change the texture position
			ch2 -= 85;
			// set new texture position
			chP2.setPosition(ch2, 0);
			// change the image texture
			imgP2.setDrawable(new SpriteDrawable(changeCharacter(ch2Index)));
			// play menu select sound
			soundFiles.playSound("menuTraverse");
			break;
		}

		case Keys.SPACE:  {
			if (chP1Ready.isVisible() && chP2Ready.isVisible()) {
				btnPlayGame.setText("Ready");
				btnPlayGame.setDisabled(false);
			}
			chP1Ready.setVisible(true);
			soundFiles.playSound("menuSelect");
			break;
		}

		case Keys.NUMPAD_0:  {
			if (chP1Ready.isVisible() && chP2Ready.isVisible()) {
				btnPlayGame.setText("Ready");
				btnPlayGame.setDisabled(false);
			}
			chP2Ready.setVisible(true);
			soundFiles.playSound("menuSelect");
			break;
		}

		case Keys.ENTER:  {
			game.setScreen(new GameScreen(game));
			soundFiles.playSound("menuSelect");
			hide();
			break;
		}

		case Keys.ESCAPE:  {
			chP1Ready.setVisible(false);
			chP2Ready.setVisible(false);
			break;
		}
		
		case Keys.BACKSPACE:  {
			soundFiles.playSound("menuBack");
			game.setScreen(new MenuScreen(game));
			break;
		}
		}
	}

	// method to change the texture based on character index
	private Sprite changeCharacter(int chIndex) {
		Sprite chTexture = null;
		switch (chIndex) {
		case 0: {
			chTexture = new Sprite(TextureFiles.getCharacterTexture("ichigo"));
			break;
		}
		case 1: {
			chTexture = new Sprite(TextureFiles.getCharacterTexture("byakuya"));
			break;
		}
		case 2: {
			chTexture = new Sprite(TextureFiles.getCharacterTexture("ikkaku"));
			break;
		}
		case 3: {
			chTexture = new Sprite(TextureFiles.getCharacterTexture("ishida"));
			break;
		}
		case 4: {
			chTexture = new Sprite(TextureFiles.getCharacterTexture("kenpachi"));
			break;
		}
		case 5: {
			chTexture = new Sprite(TextureFiles.getCharacterTexture("urahara"));
			break;
		}
		case 6: {
			chTexture = new Sprite(TextureFiles.getCharacterTexture("komamura"));
			break;
		}
		case 7: {
			chTexture = new Sprite(TextureFiles.getCharacterTexture("mayuri"));
			break;
		}
		case 8: {
			chTexture = new Sprite(TextureFiles.getCharacterTexture("renji"));
			break;
		}
		}
		return chTexture;
	}
}
