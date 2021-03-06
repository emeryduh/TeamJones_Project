package mainpackage.Screens;

import mainpackage.Game;
import mainpackage.SoundFiles;
import mainpackage.PlayerInput;
import mainpackage.TextureFiles;
import mainpackage.SpriteClass;
import mainpackage.UserConfig;

import org.lwjgl.input.Keyboard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class OptionScreen implements Screen {
	// variables
	private Game game;
	private SpriteClass spriteClass = new SpriteClass();
	private TextureAtlas atlas;
	private Skin skin;
	private SpriteBatch batch;
	private Texture backgroundTex, selectorTex, volumeEmptyTex, volumeFullTex,
			helpTxt01, helpTxt02, helpTxt03, helpTxt04;
	private SoundFiles soundFiles = new SoundFiles();
	private float masterVolume, bgmVolume, sfxVolume;
	private int selectorXPos = 40, selectorYPos, optionIndex = 0;
	private int[] optionPositions = new int[4];
	private Texture[] helpTxts = new Texture[4];
	// private UserConfig userConfig;
	// This is how fast the sound changes occur on user input
	private float tickSpeed = 0.04f;

	// constructor to keep a reference to the main Game class
	public OptionScreen(Game game) {
		this.game = game;
		// loads the textures
		backgroundTex = new Texture(
				Gdx.files.internal("assets/gui/optionsMenuBG.png"));
		volumeEmptyTex = new Texture(
				Gdx.files.internal("assets/gui/volumeBar_Empty.png"));
		volumeFullTex = new Texture(
				Gdx.files.internal("assets/gui/volumeBar_Full.png"));
		helpTxt01 = new Texture(
				Gdx.files.internal("assets/gui/masterVolumeText.png"));
		helpTxt02 = new Texture(
				Gdx.files.internal("assets/gui/bgmVolumeText.png"));
		helpTxt03 = new Texture(
				Gdx.files.internal("assets/gui/sfxVolumeText.png"));
		helpTxt04 = new Texture(Gdx.files.internal("assets/gui/backText.png"));

		// stores the y coordinates in pixels of each option into an array
		optionPositions[0] = 490;
		optionPositions[1] = 405;
		optionPositions[2] = 325;
		optionPositions[3] = 190;

		// stores the help text textures into an array
		helpTxts[0] = helpTxt01;
		helpTxts[1] = helpTxt02;
		helpTxts[2] = helpTxt03;
		helpTxts[3] = helpTxt04;

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

		// positions the selector on the Y axis according to the option selected
		selectorYPos = optionPositions[optionIndex];

		// sets the current gui element to draw as the selector
		selectorTex = spriteClass.setAnimationGUI();

		// draw objects into the screen
		batch.begin();
		// draws the background texture
		batch.draw(backgroundTex, 0, 0, 800, 600, 0, 0,
				backgroundTex.getWidth(), backgroundTex.getHeight(), false,
				false);
		// draws the master volume bar texture
		batch.draw(volumeEmptyTex, 340, optionPositions[0]);
		batch.draw(volumeFullTex, 340, optionPositions[0],
				(int) (volumeEmptyTex.getWidth() * masterVolume),
				volumeEmptyTex.getHeight(), 0, 0,
				(int) (volumeEmptyTex.getWidth() * masterVolume),
				volumeEmptyTex.getHeight(), false, false);
		// draws the bgm volume bar texture
		batch.draw(volumeEmptyTex, 340, optionPositions[1]);
		batch.draw(volumeFullTex, 340, optionPositions[1],
				(int) (volumeEmptyTex.getWidth() * bgmVolume),
				volumeEmptyTex.getHeight(), 0, 0,
				(int) (volumeEmptyTex.getWidth() * bgmVolume),
				volumeEmptyTex.getHeight(), false, false);
		// draws the sfx volume bar texture
		batch.draw(volumeEmptyTex, 340, optionPositions[2]);
		batch.draw(volumeFullTex, 340, optionPositions[2],
				(int) (volumeEmptyTex.getWidth() * sfxVolume),
				volumeEmptyTex.getHeight(), 0, 0,
				(int) (volumeEmptyTex.getWidth() * sfxVolume),
				volumeEmptyTex.getHeight(), false, false);
		// draws the help text
		batch.draw(helpTxts[optionIndex], 0, 50);
		// draws the animating selector texture
		batch.draw(
				new TextureRegion(selectorTex, spriteClass.getFrameIndexGUI()
						* (selectorTex.getWidth() / spriteClass.getNumOfFramesGUI()), 0, selectorTex
						.getWidth() / spriteClass.getNumOfFramesGUI(), selectorTex.getHeight()),
				selectorXPos, selectorYPos);
		batch.end();

		// this will enable the continuous key press
		Keyboard.enableRepeatEvents(true);
	}

	// called when the screen resized
	public void resize(int width, int height) {
		// to enable the keyboard events
		PlayerInput playerInput = new PlayerInput(game);

		Gdx.input.setInputProcessor(playerInput);
	}

	// called when this screen becomes the current screen for a Game.
	public void show() {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("assets/gui/button.pack");
		skin = new Skin();
		skin.addRegions(atlas);
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
		switch (keycode) {
		case Keys.ENTER:
			if (optionIndex == 3) {
				// Set option screen changes to user config
				UserConfig.setMasterVolume(masterVolume);
				UserConfig.setBGMVolume(bgmVolume);
				UserConfig.setSFXVolume(sfxVolume);

				game.setScreen(new MenuScreen(game));
			}
			return;
			// allows user to to go up and down in the options menu
		case Keys.UP:
			optionIndex--;
			if (optionIndex < 0) {
				optionIndex = 3;
			}
			soundFiles.playSound("menuTraverse", sfxVolume * masterVolume);
			return;
		case Keys.DOWN:
			optionIndex++;
			if (optionIndex > 3) {
				optionIndex = 0;
			}
			soundFiles.playSound("menuTraverse", sfxVolume * masterVolume);
			return;
		case Keys.LEFT:
			soundFiles.playSound("volumeChange", sfxVolume * masterVolume);
			switch (optionIndex) {
			// lowers the current volume level of the current option
			case 0:
				if (masterVolume > 0.001) {
					masterVolume -= tickSpeed;

					if (masterVolume < 0) {
						masterVolume = 0.0f;
					}

				}
				break;
			case 1:
				if (bgmVolume > 0.001) {
					bgmVolume -= tickSpeed;

					if (bgmVolume < 0) {
						bgmVolume = 0.0f;
					}

				}
				break;
			case 2:
				if (sfxVolume > 0.001) {
					sfxVolume -= tickSpeed;

					if (sfxVolume < 0) {
						sfxVolume = 0.0f;
					}

				}
				break;
			}
			return;
		case Keys.RIGHT:
			soundFiles.playSound("volumeChange", sfxVolume * masterVolume);
			switch (optionIndex) {
			// raises the current volume level of the current option
			case 0:
				if (masterVolume < 0.999) {
					masterVolume += tickSpeed;

					if (masterVolume > 1) {
						masterVolume = 1.0f;
					}

				}
				break;
			case 1:
				if (bgmVolume < 0.999) {
					bgmVolume += tickSpeed;

					if (bgmVolume > 1) {
						bgmVolume = 1.0f;
					}

				}
				break;
			case 2:
				if (sfxVolume < 0.999) {
					sfxVolume += tickSpeed;

					if (sfxVolume > 1) {
						sfxVolume = 1.0f;
					}

				}
				break;
			}
			return;
		}
	}
}
