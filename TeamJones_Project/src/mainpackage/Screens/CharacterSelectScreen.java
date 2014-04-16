package mainpackage.Screens;

import org.lwjgl.input.Keyboard;

import mainpackage.Game;
import mainpackage.PlayerInput;
import mainpackage.SoundFiles;
import mainpackage.TextureFiles;
import mainpackage.UserConfig;

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
	private Texture texIchigo, texByakuya, texIkkaku, texIshida, texKenpachi,
			texUrahara, texkomamura, texMayuri, texRenji, texP1, texP2,
			texP1Ready, texP2Ready;
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

	// Holds the screens volume levels when loaded in
	private float bgmVolume;
	private float sfxVolume;

	// constructor to keep a reference to the main Game class
	public CharacterSelectScreen(Game game) {
		this.game = game;
	}

	// called when the screen should render itself
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// stage.act(delta);
		// draw objects into the screen
		batch.begin();

		batch.draw(splashTexture, 0, 0, 800, 600, 0, 0,
				splashTexture.getWidth(), splashTexture.getHeight(), false,
				false);

		batch.draw(p1Texture, 10, 50, 300, 550, 0, 0, p1Texture.getWidth(),
				p1Texture.getHeight(), false, false);

		batch.draw(p2Texture, 480, 50, 300, 550, 0, 0, p2Texture.getWidth(),
				p2Texture.getHeight(), false, false);

		batch.draw(texIchigo, 20, 50, 70, 120, 0, 0, texIchigo.getWidth(),
				texIchigo.getHeight(), false, false);

		batch.draw(texByakuya, 105, 50, 70, 120, 0, 0, texByakuya.getWidth(),
				texByakuya.getHeight(), false, false);

		batch.draw(texIkkaku, 190, 50, 70, 120, 0, 0, texIkkaku.getWidth(),
				texIkkaku.getHeight(), false, false);

		batch.draw(texIshida, 275, 50, 70, 120, 0, 0, texIshida.getWidth(),
				texIshida.getHeight(), false, false);

		batch.draw(texKenpachi, 360, 50, 70, 120, 0, 0, texKenpachi.getWidth(),
				texKenpachi.getHeight(), false, false);

		batch.draw(texUrahara, 445, 50, 70, 120, 0, 0, texUrahara.getWidth(),
				texUrahara.getHeight(), false, false);

		batch.draw(texkomamura, 530, 50, 70, 120, 0, 0, texkomamura.getWidth(),
				texkomamura.getHeight(), false, false);

		batch.draw(texMayuri, 615, 50, 70, 120, 0, 0, texMayuri.getWidth(),
				texMayuri.getHeight(), false, false);

		batch.draw(texRenji, 700, 50, 70, 120, 0, 0, texRenji.getWidth(),
				texRenji.getHeight(), false, false);

		/*
		 * batch.draw(texP1, 0, 170, 100, 50, 0, 0, texP1.getWidth(),
		 * texP1.getHeight(), false, false);
		 * 
		 * batch.draw(texP2, 680, 0, 100, 50, 0, 0, texP2.getWidth(),
		 * texP2.getHeight(), false, false);
		 */

		/*
		 * batch.draw(texP1Ready, 10, 50, 300, 550, 0, 0, texP1Ready.getWidth(),
		 * texP1Ready.getHeight(), false, false);
		 * 
		 * batch.draw(texP2Ready, 480, 50, 300, 550, 0, 0,
		 * texP2Ready.getWidth(), texP2Ready.getHeight(), false, false);
		 */

		// stage.draw();
		batch.end();
		batch.begin();
		stage.draw();
		batch.end();

		// this will enable the continuous key press
		Keyboard.enableRepeatEvents(true);
	}

	// called when the screen resized
	public void resize(int width, int height) {

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

		// texture for player1
		// default character is ichigo
		p1Texture = TextureFiles.getCharacterTexture("ichigo");

		// texture for player2
		// default character is byakuya
		p2Texture = TextureFiles.getCharacterTexture("byakuya");

		// ichigo texture
		texIchigo = TextureFiles.getCharacterSelectionTexture("ichigo");

		// byakuya texture
		texByakuya = TextureFiles.getCharacterSelectionTexture("byakuya");

		// ikkaku texture
		texIkkaku = TextureFiles.getCharacterSelectionTexture("ikkaku");

		// ishida texture
		texIshida = TextureFiles.getCharacterSelectionTexture("ishida");

		// kenpachi texture
		texKenpachi = TextureFiles.getCharacterSelectionTexture("kenpachi");

		// urahara texture
		texUrahara = TextureFiles.getCharacterSelectionTexture("urahara");

		// komamura texture
		texkomamura = TextureFiles.getCharacterSelectionTexture("komamura");

		// mayuri texture
		texMayuri = TextureFiles.getCharacterSelectionTexture("mayuri");

		// renji texture
		texRenji = TextureFiles.getCharacterSelectionTexture("renji");

		// player 1 selection texture
		texP1 = TextureFiles.getUtilityTexture("player1");
		texP1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		chP1 = new Image(new TextureRegion(texP1));
		// adds an actor to the root of the stage.
		chP1.setBounds(0, 170, 100, 50);
		stage.addActor(chP1);

		// player 2 selection texture
		texP2 = TextureFiles.getUtilityTexture("player2");
		texP2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		chP2 = new Image(new TextureRegion(texP2));
		// adds an actor to the root of the stage.
		chP2.setBounds(85, 0, 100, 50);
		stage.addActor(chP2);

		// player 1 ready texture
		texP1Ready = TextureFiles.getUtilityTexture("ready");
		texP1Ready.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		chP1Ready = new Image(new TextureRegion(texP1Ready));
		// adds an actor to the root of the stage.
		chP1Ready.setBounds(10, 50, 300, 550);
		chP1Ready.setVisible(false);
		stage.addActor(chP1Ready);

		// player 2 ready texture
		texP2Ready = TextureFiles.getUtilityTexture("ready");
		texP2Ready.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		chP2Ready = new Image(new TextureRegion(texP2Ready));
		// adds an actor to the root of the stage.
		chP2Ready.setBounds(480, 50, 300, 550);
		chP2Ready.setVisible(false);
		stage.addActor(chP2Ready);
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
		// Set volume levels upon loading
		bgmVolume = UserConfig.getBGMVolume(false);
		sfxVolume = UserConfig.getSFXVolume(false);

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
		charSelectionMusic.setVolume(UserConfig.getBGMVolume(false));
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
		skin.dispose();
		atlas.dispose();
		stage.dispose();
		charSelectionMusic.dispose();
	}

	// method to return the key selection
	public void keyDown(int keycode) {
		soundFiles = new SoundFiles();

		switch (keycode) {
		case Keys.A: {
			// return if prior than character 1
			if (ch1 <= 0 || chP1Ready.isVisible())
				break;
			p1Texture.dispose();
			// change index based on character selection
			ch1Index--;
			// value that change the texture position
			ch1 -= 85;
			// set new texture position
			chP1.setPosition(ch1, 170);
			// change the image texture
			p1Texture = changeCharacter(ch1Index);
			// play menu select sound
			soundFiles.playSound("menuTraverse", sfxVolume);
			break;
		}
		case Keys.D: {
			// return if further than last character
			if (ch1 >= 680 || chP1Ready.isVisible())
				break;
			p1Texture.dispose();
			// change index based on character selection
			ch1Index++;
			// value that change the texture position
			ch1 += 85;
			// set new texture position
			chP1.setPosition(ch1, 170);
			// change the image texture
			p1Texture = changeCharacter(ch1Index);
			// play menu select sound
			soundFiles.playSound("menuTraverse", sfxVolume);
			break;
		}
		case Keys.RIGHT: {
			if (ch2 >= 680 || chP2Ready.isVisible())
				break;
			p2Texture.dispose();
			ch2Index++;
			// value that change the texture position
			ch2 += 85;
			// set new texture position
			chP2.setPosition(ch2, 0);
			// change the image texture
			p2Texture = changeCharacter(ch2Index);
			// play menu select sound
			soundFiles.playSound("menuTraverse", sfxVolume);
			break;
		}
		case Keys.LEFT: {
			if (ch2 <= 0 || chP2Ready.isVisible())
				break;
			p2Texture.dispose();
			// change index based on character selection
			ch2Index--;
			// value that change the texture position
			ch2 -= 85;
			// set new texture position
			chP2.setPosition(ch2, 0);
			// change the image texture
			p2Texture = changeCharacter(ch2Index);
			// play menu select sound
			soundFiles.playSound("menuTraverse", sfxVolume);
			break;
		}

		case Keys.SPACE: {			
			chP1Ready.setVisible(true);
			soundFiles.playSound("menuSelect", sfxVolume);
			break;
		}

		case Keys.NUMPAD_0: {			
			chP2Ready.setVisible(true);
			soundFiles.playSound("menuSelect", sfxVolume);
			break;
		}

		case Keys.ENTER: {
			if (chP1Ready.isVisible() && chP2Ready.isVisible()) {
				charSelectionMusic.stop();
				game.setScreen(new GameScreen(game));
				soundFiles.playSound("menuSelect", sfxVolume);
				hide();
			}
			break;
		}

		case Keys.ESCAPE: {
			chP1Ready.setVisible(false);
			chP2Ready.setVisible(false);
			break;
		}

		case Keys.BACKSPACE: {
			soundFiles.playSound("menuBack", sfxVolume);
			charSelectionMusic.stop();
			game.setScreen(new MenuScreen(game));
			break;
		}
		}
	}

	// method to change the texture based on character index
	private Texture changeCharacter(int chIndex) {
		Texture chTexture = null;
		switch (chIndex) {
		case 0: {
			chTexture = TextureFiles.getCharacterTexture("ichigo");
			break;
		}
		case 1: {
			chTexture = TextureFiles.getCharacterTexture("byakuya");
			break;
		}
		case 2: {
			chTexture = TextureFiles.getCharacterTexture("ikkaku");
			break;
		}
		case 3: {
			chTexture = TextureFiles.getCharacterTexture("ishida");
			break;
		}
		case 4: {
			chTexture = TextureFiles.getCharacterTexture("kenpachi");
			break;
		}
		case 5: {
			chTexture = TextureFiles.getCharacterTexture("urahara");
			break;
		}
		case 6: {
			chTexture = TextureFiles.getCharacterTexture("komamura");
			break;
		}
		case 7: {
			chTexture = TextureFiles.getCharacterTexture("mayuri");
			break;
		}
		case 8: {
			chTexture = TextureFiles.getCharacterTexture("renji");
			break;
		}
		}
		return chTexture;
	}
}
