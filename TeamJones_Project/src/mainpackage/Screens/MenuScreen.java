package mainpackage.Screens;

import org.lwjgl.input.Keyboard;

import mainpackage.Game;
import mainpackage.PlayerInput;
import mainpackage.SoundFiles;
import mainpackage.SpriteClass;
import mainpackage.TextureFiles;
import mainpackage.UserConfig;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
	private SpriteClass spriteClass = new SpriteClass();
	private TextureAtlas atlas;
	private Skin skin;
	private SpriteBatch batch;
	private Texture backgroundTex, selectorTex;
	public Music openingMusic;
	private SoundFiles soundFiles;
	private int selectorXPos = 310, selectorYPos, menuIndex = 0;;
	private float masterVolume, bgmVolume, sfxVolume;
	private int[] optionPositions = new int[3];

	// constructor to keep a reference to the main Game class
	public MenuScreen(Game game) {
		this.game = game;
		bgmVolume = UserConfig.getBGMVolume(false);
		sfxVolume = UserConfig.getSFXVolume(false);
		openingMusic = Gdx.audio.newMusic(Gdx.files
				.internal("assets/audioFiles/menuSounds/mainMenuMusic.mp3"));
		openingMusic.setVolume(bgmVolume);
		openingMusic.setLooping(true);
		
		backgroundTex = TextureFiles.getBackgroundTexture("menuScreen");
		
		// stores the y coordinates in pixels of each option into an array
		optionPositions[0] = 305;
		optionPositions[1] = 265;
		optionPositions[2] = 225;
		
		// setup selector texture
		spriteClass.setSheetValsGUI(0, 0);

		// get user config volumes
		masterVolume = UserConfig.getMasterVolume();
		bgmVolume = UserConfig.getBGMVolume(true);
		sfxVolume = UserConfig.getSFXVolume(true);
	}

	// called when the screen should render itself.
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// to enable the keyboard events
		PlayerInput playerInput = new PlayerInput(game);
		Gdx.input.setInputProcessor(playerInput);
		
		// positions the selector on the Y axis according to the option selected
		selectorYPos = optionPositions[menuIndex];
		
		// sets the current gui element to draw as the selector
		selectorTex = spriteClass.setAnimationGUI();

		// draw objects into the screen
		batch.begin();
		// draws the background texture
		batch.draw(backgroundTex, 0, 0, 800, 600, 0, 0, backgroundTex.getWidth(), backgroundTex.getHeight(), false, false);
		// draws the animating selector texture
		batch.draw(new TextureRegion(selectorTex, spriteClass.getFrameIndexGUI() * (selectorTex.getWidth() / spriteClass.getNumOfFramesGUI()), 0, selectorTex.getWidth() / spriteClass.getNumOfFramesGUI(), selectorTex.getHeight()), selectorXPos, selectorYPos);
		batch.end();

		// this will enable the continuous key press
		Keyboard.enableRepeatEvents(false);
	}

	// called when the screen resized
	public void resize(int width, int height) {
	}

	// called when this screen becomes the current screen for a Game.
	public void show() {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("assets/gui/button.pack");
		skin = new Skin();
		skin.addRegions(atlas);
		// plays background music for menus
		if (openingMusic.isPlaying() == false) {
			openingMusic.play();
		}
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
	}

	// method to return the key selection
	public void keyDown(int keycode) {
		soundFiles = new SoundFiles();
		if (menuIndex < 0)
			return;
		if (keycode == Keys.UP) {
			menuIndex--;
			if(menuIndex < 0)
			{
				menuIndex = 2;
			}
			soundFiles.playSound("menuTraverse", sfxVolume);
			return;
		}
		if (keycode == Keys.DOWN) {
			menuIndex++;
			if(menuIndex > 2)
			{
				menuIndex = 0;
			}
			soundFiles.playSound("menuTraverse", sfxVolume);
			return;
		}
		if (keycode == Keys.ENTER && menuIndex == 0) {
			game.setScreen(new CharacterSelectScreen(game));
			soundFiles.playSound("menuSelect", sfxVolume);
			openingMusic.stop();
			return;
		}
		
		else if (keycode == Keys.ENTER && menuIndex == 1) {
			game.setScreen(new TutorialScreen(game));
			soundFiles.playSound("menuSelect", sfxVolume);
			openingMusic.stop();
			return;
		}
		
		if (keycode == Keys.ENTER && menuIndex == 2) {
			game.setScreen(new OptionScreen(game));
			soundFiles.playSound("menuSelect", sfxVolume);
			openingMusic.stop();
			return;
		}

	}
}
