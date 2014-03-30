package mainpackage.Screens;

import org.lwjgl.input.Keyboard;

import mainpackage.Game;
import mainpackage.PlayerInput;
import mainpackage.SoundFiles;
import mainpackage.SpriteClass;
import mainpackage.TextureFiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class GameScreen implements Screen {
	// variables
	private Game game;
	private Stage stage;
	private SpriteClass spriteClass = new SpriteClass();
	private SpriteClass selectorSprite = new SpriteClass();
	private SpriteBatch batch;
	private TextureAtlas atlas;
	private Skin skin;
	private Music battleMusic;
	private Texture backgroundTex, hpBarLeftTex, hpBarRightTex, roundsTex, player01SmallTex, selectorTex;
	public int playerXPos = 50, playerYPos = 15, moveSpeed = 4, player01State = 0;
	private Sound attack01;
	private boolean isKeyPressed, isPaused;
	public boolean isFacingRight;
	private int curAction = 0;
	private Texture curAnimation;
	private BitmapFont blackFont;
	private int state;
	public int timer;
	private Window pause;
	private int menuIndex = 0;
	private Image menuSelector;
	private SoundFiles soundFiles;
	private int[] optionPositions = new int[4];
	private int selectorXPos = 300, selectorYPos, optionIndex = 0;
	
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	
	

	// constructor to keep a reference to the main Game class
	public GameScreen(Game game) {
		super();
		this.game = game;
		state = GAME_RUNNING;
		attack01 = Gdx.audio.newSound(Gdx.files.internal("assets/audioFiles/ichigoCombat/ichigoAttack01.wav"));
		backgroundTex = new Texture(Gdx.files.internal("assets/sprites/backgrounds/battle_BG_01.png"));
		blackFont = new BitmapFont(Gdx.files.internal("assets/gui/blackfont.fnt"), false);
		
		// stores the y coordinates in pixels of each option into an array
		optionPositions[0] = 320;
		optionPositions[1] = 275;
		optionPositions[2] = 225;
		optionPositions[3] = 190;
		
	}

	// called when the screen should render itself
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		selectorYPos = optionPositions[optionIndex];
		selectorSprite.setSheetVals(0,0);
		selectorTex = selectorSprite.setAnimation();
		
		spriteClass.setSheetVals(1, curAction);
		curAnimation = spriteClass.setAnimation();
		batch.begin();
		batch.draw(backgroundTex, 0, 0, 800, 600, 0, 0, backgroundTex.getWidth(), backgroundTex.getHeight(), false, false);
		batch.draw(new TextureRegion(curAnimation, spriteClass.getFrameIndex() * (curAnimation.getWidth() / 6), 0, curAnimation.getWidth() / 6, curAnimation.getHeight()), playerXPos, playerYPos);
		batch.end();
		stage.draw();
		//enabling keyboard events
		PlayerInput playerInput = new PlayerInput(game);
		// set the input processor
		Gdx.input.setInputProcessor(playerInput);
				
		if(isKeyPressed == Gdx.input.isKeyPressed(Keyboard.KEY_NONE) && player01State == 0)
		{
			if(isFacingRight == true)
			{
				curAction = 0;
			}
			else
			{
				curAction = 1;
			}
		}
			//handles continuous keyboard input
		if (state == GAME_RUNNING) {
			if(playerXPos > 0)
			{
				if(isKeyPressed = Gdx.input.isKeyPressed(Keys.LEFT))
				{
					curAction = 3;
					isFacingRight = false;
					playerXPos -= moveSpeed;
				}
			}
			if(playerXPos < 800 - (curAnimation.getWidth() / 6))
			{
				if (isKeyPressed = Gdx.input.isKeyPressed(Keys.RIGHT))
				{
					curAction = 2;
					isFacingRight = true;
					playerXPos += moveSpeed;
				}
			}
			pause.setVisible(false);
			timer++;
			if (Gdx.input.isKeyPressed(Keys.P) && timer > 10)
			{
				timer = 0;
				state = GAME_PAUSED;
			}
		}
		if (state == GAME_PAUSED) {
			if(isFacingRight == true)
			{
				curAction = 0;
			}
			else
			{
				curAction = 1;
			}
			timer ++;
			pause.setVisible(true);
			batch.begin();
			batch.draw(new TextureRegion(selectorTex, selectorSprite.getFrameIndex() * (selectorTex.getWidth() / 6), 0, selectorTex.getWidth() / 6, selectorTex.getHeight()), selectorXPos, selectorYPos);
			batch.end();
			if (Gdx.input.isKeyPressed(Keys.P) && timer > 10)
			{
				timer = 0;
				state = GAME_RUNNING;
			}
		}
	}

	// called when the screen resized
	public void resize(int width, int height) {
		
	}

	// called when this screen becomes the current screen for a Game
	public void show() {
		stage = new Stage();
		batch = new SpriteBatch();
		atlas = new TextureAtlas("assets/gui/button.pack");
		skin = new Skin(Gdx.files.internal("assets/gui/menuSkin.json"), atlas);
		skin.addRegions(atlas);
		battleMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/audioFiles/battleMusic/battleMusic01.mp3"));
		battleMusic.play();
		battleMusic.setLooping(true);
		battleMusic.setVolume(this.game.masterVolume);
		pause = new Window("PAUSE",skin);
		pause.setVisible(false);
		TextButtonStyle txtResume = new TextButtonStyle();
		txtResume.up = skin.getDrawable("button");
		txtResume.down = skin.getDrawable("buttonpressed");
		txtResume.font = blackFont;
		TextButton resumeButton = new TextButton("Resume",txtResume);
		TextButtonStyle txtOptions = new TextButtonStyle();
		txtOptions.up = skin.getDrawable("button");
		txtOptions.down = skin.getDrawable("buttonpressed");
		txtOptions.font = blackFont;
		TextButton optionsButton = new TextButton("Options",txtOptions);
		TextButtonStyle txtCombos = new TextButtonStyle();
		txtCombos.up = skin.getDrawable("button");
		txtCombos.down = skin.getDrawable("buttonpressed");
		txtCombos.font = blackFont;
		TextButton combosButton = new TextButton("Combos",txtCombos);
		TextButtonStyle txtQuit = new TextButtonStyle();
		txtQuit.up = skin.getDrawable("button");
		txtQuit.down = skin.getDrawable("buttonpressed");
		txtQuit.font = blackFont;
		TextButton quitButton = new TextButton("Quit",txtQuit);
	
		pause.padTop(64);
		pause.add(resumeButton).row();
		pause.add(optionsButton).row();
		pause.add(combosButton).row();
		pause.add(quitButton).row();
		pause.setSize(stage.getWidth()/1.5f, stage.getHeight()/1.5f);
		pause.setPosition(stage.getWidth()/2 -pause.getWidth()/2, stage.getHeight()/2 - pause.getHeight()/2);
		Texture cursorTex = TextureFiles.geUtilityTexture("cursor");
		cursorTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		menuSelector = new Image(new TextureRegion(cursorTex));
		menuSelector.setBounds(530, 140, 60, 30);
		pause.addActor(menuSelector);
		
		
		
		stage.addActor(pause); 
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
		battleMusic.dispose();
	}
	
	public boolean pausedState()
	{
		return isPaused;
	}

	public void keyDown(int keycode)
	{
		if (state == GAME_RUNNING) {
			switch(keycode)
			{
			case Keys.A:
				spriteClass.resetFrameIndex();
				attack01.play();
				if(isFacingRight == true)
				{
					curAction = 4;
				}
				else
				{
					curAction = 5;
				}
				return;
			case Keys.DOWN:
				return;
			case Keys.UP:
				return;
			}
		}
		if (state == GAME_PAUSED) {
			soundFiles = new SoundFiles();
			switch (keycode) 
			{
			case Keys.ENTER:
				if(optionIndex == 0)
				{
					state = GAME_RUNNING;
					pause.setVisible(false);
					
				}
				if(optionIndex == 1)
				{
					game.setScreen(new OptionScreen(game));
					battleMusic.stop();
					
				}
				if(optionIndex == 2)
				{
					game.setScreen(new MenuScreen(game));
					battleMusic.stop();
					
				}
				if(optionIndex == 3)
				{
					game.setScreen(new MenuScreen(game));
					battleMusic.stop();
					
				}
				return;
			//allows user to to go up and down in the options menu
			case Keys.UP:
				optionIndex--;
				if (optionIndex < 0) 
				{
					optionIndex = 0;
				}
				soundFiles.playSound("menuTraverse", game.sfxVolume);
				return;
			case Keys.DOWN:
				optionIndex++;
				if (optionIndex > 3) 
				{
					optionIndex = 3;
				}
				soundFiles.playSound("menuTraverse", game.sfxVolume);
				return;
			}
		}
	}
}
