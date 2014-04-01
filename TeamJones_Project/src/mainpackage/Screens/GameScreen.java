package mainpackage.Screens;

import org.lwjgl.input.Keyboard;
import mainpackage.Game;
import mainpackage.PlayerInput;
import mainpackage.SpriteClass;
import mainpackage.TextureFiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameScreen implements Screen {
	// variables
	private Game game;
	private SpriteClass spriteClass = new SpriteClass();
	private SpriteBatch batch;
	private TextureAtlas atlas;
	private Skin skin;
	private Music battleMusic;
	private Sound attack01;
	private Texture backgroundTex, hpBarLeftTex, hpBarRightTex, roundsTex, player01SmallTex;
	private int playerXPos = 50, playerYPos = 15, moveSpeed = 4, player01State = 0, curAction = 0, optionIndex =0;
	private boolean isKeyPressed, isFacingRight = true, isPaused = false, grounded = true, timeUp;
	private float velocityX, velocityY, gravity = 5, elapsedTime, jumpStrength = 100;
	private Texture curAnimation, selectorTex, pauseFilterTex, pauseMenuTex;
	private int[] pauseOptions = new int[3];
	private Texture[] pauseHelpTxts = new Texture[3];
	private int[] optionPositions = new int[4];
	

	// constructor to keep a reference to the main Game class
	public GameScreen(Game game) {
		super();
		this.game = game;
		battleMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/audioFiles/battleMusic/battleMusic01.mp3"));
		attack01 = Gdx.audio.newSound(Gdx.files.internal("assets/audioFiles/ichigoCombat/ichigoAttack01.wav"));
		backgroundTex = new Texture(Gdx.files.internal("assets/sprites/backgrounds/battle_BG_01.png"));
		selectorTex = new Texture(Gdx.files.internal("assets/gui/selectorTex.png"));
		pauseFilterTex = new Texture(Gdx.files.internal("assets/gui/pauseFilter.png"));
		pauseMenuTex = new Texture(Gdx.files.internal("assets/gui/pauseMenuTex.png"));
		pauseHelpTxts[0] = new Texture(Gdx.files.internal("assets/gui/pauseHelpTxt01.png"));
		pauseHelpTxts[1] = new Texture(Gdx.files.internal("assets/gui/pauseHelpTxt02.png"));
		pauseHelpTxts[2] = new Texture(Gdx.files.internal("assets/gui/pauseHelpTxt03.png"));
		
		//stores the Y coordinates of the pause menu options in pixels
		pauseOptions[0] = 350;
		pauseOptions[1] = 300;
		pauseOptions[2] = 250;
	}

	// called when the screen should render itself
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//enabling keyboard events
		PlayerInput playerInput = new PlayerInput(game);
		// set the input processor
		Gdx.input.setInputProcessor(playerInput);
		
		//checks for when the player is touching the ground
		if(playerYPos <= 15)
		{
			grounded = true;
		}
		
		//applies gravity
		if(grounded == false)
		{
			playerYPos -= gravity;
		}
		
		//sent to SpriteClass to tell it what the current character and animation are
		spriteClass.setSheetVals(1, curAction);
		//calls SpriteClass to get the current animation and stores it
		curAnimation = spriteClass.setAnimation();
		batch.begin();
		//draws the background texture (texture, x-coordinate, y-coordinate, width, height, source width, source height, horizontal flip, vertical flip)
		batch.draw(backgroundTex, 0, 0, 800, 600, 0, 0, backgroundTex.getWidth(), backgroundTex.getHeight(), false, false);
		//draws player 1 (texture, source x-coordinate, source y-coordinate, source width, source height, x-coordinate, y-coordinate)
		batch.draw(new TextureRegion(curAnimation, spriteClass.getFrameIndex() * (curAnimation.getWidth() / 6), 0, curAnimation.getWidth() / 6, curAnimation.getHeight()) , playerXPos, playerYPos);
		//draws the pause menu
		if(isPaused == true)
		{
			//draws the black filter to create dimming effect
			batch.draw(pauseFilterTex, 0, 0);
			//draws the pause menu
			batch.draw(pauseMenuTex, 250, 200);
			//draws the help text-boxes
			batch.draw(pauseHelpTxts[optionIndex], 360, 450);
			//draws the selector (texture, source x-coordinate, source y-coordinate, source width, source height, x-coordinate, y-coordinate)
			batch.draw(new TextureRegion(selectorTex, spriteClass.getFrameIndex() * (selectorTex.getWidth() / 6), 0, selectorTex.getWidth() / 6, selectorTex.getHeight()) , 300, pauseOptions[optionIndex]);
		}
		batch.end();
		
		//checks if the game is not paused
		if(isPaused == false)
		{
		//checks if player 1 is grounded
		if(grounded == true)
		{
			// checks if no key is pressed down
			if (isKeyPressed == Gdx.input.isKeyPressed(Keyboard.KEY_NONE) && player01State == 0) 
			{
				if (isFacingRight == true) 
				{
					curAction = 0;
				} 
				else 
				{
					curAction = 1;
				}
			}

			// checks if an attack has finished
			if (player01State == 1 || player01State == 3)
			{
				if (spriteClass.getFrameIndex() >= 5) 
				{
					player01State = 0;
				}
			}

			// handles keyboard input involving held down key actions
			
			//checks if player 1 is at the left edge of the screen
			if (playerXPos > 0) 
			{
				//checks if the left arrow key has been held down
				if (isKeyPressed = Gdx.input.isKeyPressed(Keys.LEFT) && player01State == 0) 
				{
					curAction = 3;
					isFacingRight = false;
					playerXPos -= moveSpeed;
				}
			}
			//checks if the player is at the right edge of the screen
			if (playerXPos < 800 - (curAnimation.getWidth() / 6)) 
			{
				//checks if the right arrow key has been held down
				if (isKeyPressed = Gdx.input.isKeyPressed(Keys.RIGHT) && player01State == 0) {
					curAction = 2;
					isFacingRight = true;
					playerXPos += moveSpeed;
				}
			}

			//checks if the down arrow key has been held down
			if (isKeyPressed = Gdx.input.isKeyPressed(Keys.DOWN) && (player01State == 0 || player01State == 2)) 
			{
				player01State = 3;
				if (isFacingRight == true) 
				{
					curAction = 6;
				} 
				else 
				{
					curAction = 7;
				}
				
				//checks if the block key is also held down while the down arrow key is held down
				if (isKeyPressed = Gdx.input.isKeyPressed(Keys.N)) 
				{
					player01State = 2;
					if (isFacingRight == true) 
					{
						curAction = 10;
					} 
					else
					{
						curAction = 11;
					}
				}
			}

			//checks if the block key is held down
			if (isKeyPressed = Gdx.input.isKeyPressed(Keys.N) && player01State == 0) {
				player01State = 2;
				if (isFacingRight == true) {
					curAction = 8;
				} 
				else 
				{
					curAction = 9;
				}
			}
		}
		}
		else
		{
			//pauses the music whenever the game is paused
			battleMusic.pause();
		}
	}

	// called when the anything needs to be resized
	public void resize(int width, int height) {
		
	}

	// called when this screen becomes the current screen
	public void show() {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("assets/gui/button.pack");
		skin = new Skin();
		skin.addRegions(atlas);
		battleMusic.play();
		battleMusic.setLooping(true);
		battleMusic.setVolume(this.game.masterVolume);
	}

	// called when current screen changes to a different screen
	public void hide() {
	}

	// called when the game is paused
	public void pause() {
		isPaused = true;
	}

	// called when the game resumes
	public void resume() {
		battleMusic.play();
		isPaused = false;
	}

	// called when this screen should release all resources
	public void dispose() {
		batch.dispose();
		skin.dispose();
		atlas.dispose();
		battleMusic.dispose();
	}
	
	//returns whether the game is paused or not (called in SpriteClass to pause frame Index updating)
	public boolean pausedState()
	{
		return isPaused;
	}

	//handles keyboard input involving single press key actions
	public void keyDown(int keycode)
	{
		switch(keycode)
		{
		//checks whether the attack key was pressed
		case Keys.M:
			if(isPaused == false)
			{
				//checks whether the player is standing up or ducking and plays the coinciding attack animation
				switch(player01State)
				{
				case 0:
					spriteClass.resetFrameIndex();
					attack01.play();
					player01State = 1;
					if(isFacingRight == true)
					{
						curAction = 4;
					}
					else
					{
						curAction = 5;
					}
					break;
				case 3:
					spriteClass.resetFrameIndex();
					attack01.play();
					player01State = 1;
					if(isFacingRight == true)
					{
						curAction = 12;
					}
					else
					{
						curAction = 13;
					}
					break;
				}
			}
			//changes the functionality of the attack key to select when the game is paused
			else
			{
				switch(optionIndex)
				{
				case 0:
					resume();
					break;
				case 1:
					break;
				case 2:
					game.setScreen(new MenuScreen(game));
					break;
				}
			}
			return;
		//checks whether the P key was pressed
		case Keys.P:
			if(isPaused == false)
			{
				pause();
			}
			else
			{
				resume();
			}
			return;
		//checks whether the up arrow key was pressed
		case Keys.UP:
			if(isPaused == false)
			{
				//makes player 1 jump
				grounded = false;
				if(grounded == false)
				{
					playerYPos += jumpStrength;
				}
			}
			//changes the functionality of the up arrow key when the game is paused
			else
			{
				optionIndex --;
				if(optionIndex < 0)
				{
					optionIndex = 2;
				}
			}
			return;
		//checks whether the down arrow key was pressed
		case Keys.DOWN:
			//changes the functionality of the down arrow key when the game is paused
			if(isPaused == true)
			{
				optionIndex ++;
				if(optionIndex > 2)
				{
					optionIndex = 0;
				}
			}
			return;
		}
	}
	
	//checks when keys are released
	public void keyUp(int keycode)
	{
		switch(keycode)
		{
		//checks whether the block key was released
		case Keys.N:
			player01State = 0;
			return;
		//checks whether the down arrow key was released
		case Keys.DOWN:
			player01State = 0;
			return;
		}
	}
}
