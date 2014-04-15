package mainpackage.Screens;

import org.lwjgl.input.Keyboard;

import mainpackage.AI;
import mainpackage.Collision;
import mainpackage.Game;
import mainpackage.Player;
import mainpackage.PlayerInput;
import mainpackage.SpriteClass;
import mainpackage.TextureFiles;
import mainpackage.UserConfig;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
	private int player1XPos = 50, player1YPos = 15, moveSpeed = 4,
			player01State = 0, curActionP1 = 0, curActionP2 = 0,
			optionIndex = 0, gameOverIndex = 0, player2XPos = 650,
			player2YPos = 15, player02State = 0, optionIndexPlayer2 = 0,
			seconds = 5;
	private boolean isKeyPressed, isFacingRightP1 = true, grounded = true,
			timeUp, isGameOver = false, isFacingRightP2;
	private float velocityX, velocityY, gravity = 6, elapsedTime,
			jumpStrength = 150;
	private Texture curAnimationP1, curAnimationP2, selectorTex,
			pauseFilterTex, pauseMenuTex, gameOverTex, backgroundTex,
			playerInfoGUI, hpBarLTex, hpBarRTex, roundsTex, player01SmallTex,
			player02SmallTex;
	private int[] pauseOptions = new int[3];
	private int[] gameOverOptions = new int[2];
	private Texture[] pauseHelpTxts = new Texture[3];
	private int[] optionPositions = new int[4];
	long startTime = System.nanoTime();
	public TextureRegion player1TextureRegion, player2TextureRegion;
	private BitmapFont timerFont;

	public int curActionPlayer2 = 0;

	// Holds where characters are drawn to be placed on the "ground"
	private final static int groundHeight = 15;

	// Holds the modification to move speed while in the air
	private double jumpSpeedMod = 0.85;

	// constructor to keep a reference to the main Game class
	public GameScreen(Game game) {
		super();
		this.game = game;
		battleMusic = Gdx.audio.newMusic(Gdx.files
				.internal("assets/audioFiles/battleMusic/battleMusic01.mp3"));
		battleMusic.setVolume(UserConfig.getBGMVolume(false));

		attack01 = Gdx.audio.newSound(Gdx.files
				.internal("assets/audioFiles/ichigoCombat/ichigoAttack01.wav"));
		attack01.setVolume(0, UserConfig.getSFXVolume(false));

		backgroundTex = TextureFiles.getBackgroundTexture("gameScreen01");
		hpBarLTex = TextureFiles.getUtilityTexture("hpBarL");
		hpBarRTex = TextureFiles.getUtilityTexture("hpBarR");
		playerInfoGUI = TextureFiles.getUtilityTexture("playerInfoGUI");
		player01SmallTex = TextureFiles.getUtilityTexture("ichigoSmall");
		player02SmallTex = TextureFiles.getUtilityTexture("byakuyaSmall");
		selectorTex = TextureFiles.getUtilityTexture("cursor");
		pauseFilterTex = TextureFiles.getUtilityTexture("pauseBackground");
		pauseMenuTex = TextureFiles.getUtilityTexture("pauseMenu");
		gameOverTex = TextureFiles.getUtilityTexture("gameover");
		pauseHelpTxts[0] = TextureFiles.getUtilityTexture("pauseHelpTxt01");
		pauseHelpTxts[1] = TextureFiles.getUtilityTexture("pauseHelpTxt02");
		pauseHelpTxts[2] = TextureFiles.getUtilityTexture("pauseHelpTxt03");
		// stores the Y coordinates of the pause menu options in pixels
		pauseOptions[0] = 350;
		pauseOptions[1] = 300;
		pauseOptions[2] = 250;

		// stores the Y coordinates of the game over menu options in pixels
		gameOverOptions[0] = 300;
		gameOverOptions[1] = 250;

		// enabling keyboard events
		PlayerInput playerInput = new PlayerInput(game);
		// set the input processor
		Gdx.input.setInputProcessor(playerInput);
	}

	// called when the screen should render itself
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		// count the timer and convert nanotime to second
		if (System.nanoTime() - startTime >= 1000000000
				&& !Player.getPausedState() & !isGameOver) {
			seconds--;
			startTime = System.nanoTime();
		}

		// checks for when the player is touching the ground
		if (player1YPos <= groundHeight) {
			grounded = true;
		}

		// applies gravity
		if (grounded == false) {
			player1YPos -= gravity;
		}

		// sent to the spriteClass to tell it what gui element to draw
		spriteClass.setSheetValsGUI(0, 0);
		// sent to SpriteClass to tell it what the current character and
		// animation are
		spriteClass.setSheetValsP1(0, curActionP1);
		spriteClass.setSheetValsP2(1, AI.curActionP2);
		// calls SpriteClass to get the current animation for player 1
		curAnimationP1 = spriteClass.setAnimationP1();
		// calls SpriteClass to get the current animation for player 2
		curAnimationP2 = spriteClass.setAnimationP2();

		// initialize texture regions (texture, source x-coordinate, source
		// y-coordinate, source width, source height, x-coordinate,
		// y-coordinate)
		player1TextureRegion = new TextureRegion(curAnimationP1,
				spriteClass.getFrameIndexP1()
						* (curAnimationP1.getWidth() / spriteClass
								.getNumOfFramesP1()), 0,
				curAnimationP1.getWidth() / spriteClass.getNumOfFramesP1(),
				curAnimationP1.getHeight());
		player2TextureRegion = new TextureRegion(curAnimationP2,
				spriteClass.getFrameIndexP2()
						* (curAnimationP2.getWidth() / spriteClass
								.getNumOfFramesP2()), 0,
				curAnimationP2.getWidth() / spriteClass.getNumOfFramesP2(),
				curAnimationP2.getHeight());

		batch.begin();

		// draws the background texture (texture, x-coordinate, y-coordinate,
		// width, height, source width, source height, horizontal flip, vertical
		// flip)
		batch.draw(backgroundTex, 0, 0, 800, 600, 0, 0,
				backgroundTex.getWidth(), backgroundTex.getHeight(), false,
				false);
		// draws the health bars
		batch.draw(hpBarLTex, 0, 545,
				(int) (hpBarLTex.getWidth() * Player.getPlayer1HP() / 100),
				(int) hpBarLTex.getHeight(), 0, 0, (int) (hpBarLTex.getWidth()
						* Player.getPlayer1HP() / 100),
				(int) hpBarLTex.getHeight(), false, false);
		batch.draw(hpBarRTex, 400, 545,
				(int) (hpBarRTex.getWidth() * Player.getPlayer2HP() / 100),
				(int) hpBarRTex.getHeight(), 0, 0, (int) (hpBarRTex.getWidth()
						* Player.getPlayer2HP() / 100),
				(int) hpBarRTex.getHeight(), false, false);
		// draws the small character images
		batch.draw(player01SmallTex, 5, 545);
		batch.draw(player02SmallTex, 725, 542);
		// draws the player info gui
		batch.draw(playerInfoGUI, 0, 530);
		// draws player 1
		batch.draw(player1TextureRegion, player1XPos, player1YPos);
		// draws AI
		batch.draw(player2TextureRegion, player2XPos, player2YPos);

		// set timer color
		timerFont.setColor(Color.WHITE);

		// show game over screen if timer hits 0
		if (seconds == 0) {
			isGameOver = true;
			gameOver();
			// draws the black filter to create dimming effect
			batch.draw(pauseFilterTex, 0, 0);
			// draws the pause menu
			batch.draw(gameOverTex, 250, 200);
			// draws the selector (texture, source x-coordinate, source
			// y-coordinate, source width, source height, x-coordinate,
			// y-coordinate)
			if(Player.getPausedState())
			{
			batch.draw(
					new TextureRegion(selectorTex, spriteClass
							.getFrameIndexGUI()
							* (selectorTex.getWidth() / spriteClass
									.getNumOfFramesGUI()), 0, selectorTex
							.getWidth() / spriteClass.getNumOfFramesGUI(),
							selectorTex.getHeight()), 300,
					pauseOptions[optionIndex]);
			}
			else if(isGameOver)
			{
				batch.draw(
						new TextureRegion(selectorTex, spriteClass
								.getFrameIndexGUI()
								* (selectorTex.getWidth() / spriteClass
										.getNumOfFramesGUI()), 0, selectorTex
								.getWidth() / spriteClass.getNumOfFramesGUI(),
								selectorTex.getHeight()), 300,
						gameOverOptions[gameOverIndex]);
			}
			// hide the timer when paused
			timerFont.setColor(Color.CLEAR);
		}

		// draws the pause menu
		if (Player.getPausedState() && !isGameOver) {
			// draws the black filter to create dimming effect
			batch.draw(pauseFilterTex, 0, 0);
			// draws the pause menu
			batch.draw(pauseMenuTex, 250, 200);
			// draws the help text-boxes
			batch.draw(pauseHelpTxts[optionIndex], 360, 450);
			// draws the selector (texture, source x-coordinate,
			// source y-coordinate, source width, source height, x-coordinate,
			// y-coordinate)
			batch.draw(
					new TextureRegion(selectorTex, spriteClass
							.getFrameIndexP1()
							* (selectorTex.getWidth() / spriteClass
									.getNumOfFramesGUI()), 0, selectorTex
							.getWidth() / spriteClass.getNumOfFramesGUI(),
							selectorTex.getHeight()), 300,
					pauseOptions[optionIndex]);
		}

		// draw the timer font
		timerFont.draw(batch, String.valueOf(seconds), 390, 570);

		batch.end();

		// checks if the game is not paused
		if (!Player.getPausedState() && !isGameOver) {
			// AI logic initialization
			player2XPos = AI.runLogic(player1XPos, player2XPos);

			// Check for unit collision
			Collision.checkCollision(player1TextureRegion,
					player2TextureRegion, player1XPos, player1YPos,
					player2XPos, player2YPos, player01State, AI.curActionP2);

			// checks if an attack has finished
			if (player01State == 1) {
				if (grounded == true) {
					if (spriteClass.getFrameIndexP1() >= 5) {
						player01State = 0;
					}
				} else {
					if (spriteClass.getFrameIndexP1() >= 5) {
						if (isFacingRightP1 == true) {
							curActionP1 = 14;
						} else {
							curActionP1 = 15;
						}
					}
				}
			}
			// checks if player 1 is grounded
			if (grounded == true) {
				// checks if no key is pressed down
				if (isKeyPressed == Gdx.input.isKeyPressed(Keyboard.KEY_NONE)
						&& player01State == 0) {
					if (isFacingRightP1 == true) {
						curActionP1 = 0;
					} else {
						curActionP1 = 1;
					}
				}

				// handles keyboard input involving held down key actions

				// checks if player 1 is at the left edge of the screen
				if (player1XPos > 0) {
					// checks if the left arrow key has been held down
					if (isKeyPressed = Gdx.input.isKeyPressed(Keys.LEFT)
							&& player01State == 0) {
						curActionP1 = 3;
						isFacingRightP1 = false;
						// playerXPos -= moveSpeed;
						if (grounded == true) {
							player1XPos -= moveSpeed;
						} else {
							player1XPos -= moveSpeed * jumpSpeedMod;
						}
					}
				}
				// checks if the player is at the right edge of the screen
				if (player1XPos < 800 - (curAnimationP1.getWidth() / spriteClass
						.getNumOfFramesP1())) {
					// checks if the right arrow key has been held down
					if (isKeyPressed = Gdx.input.isKeyPressed(Keys.RIGHT)
							&& player01State == 0) {
						curActionP1 = 2;
						isFacingRightP1 = true;
						// playerXPos += moveSpeed;
						if (grounded == true) {
							player1XPos += moveSpeed;
						} else {
							player1XPos += moveSpeed * jumpSpeedMod;
						}
					}
				}

				// checks if the down arrow key has been held down
				if (isKeyPressed = Gdx.input.isKeyPressed(Keys.DOWN)
						&& (player01State == 0 || player01State == 2)) {
					player01State = 3;
					if (isFacingRightP1 == true) {
						curActionP1 = 6;
					} else {
						curActionP1 = 7;
					}

					// checks if the block key is also held down while the down
					// arrow key is held down
					if (isKeyPressed = Gdx.input.isKeyPressed(Keys.N)) {
						player01State = 2;
						if (isFacingRightP1 == true) {
							curActionP1 = 10;
						} else {
							curActionP1 = 11;
						}
					}
				}

				// checks if the block key is held down
				if (isKeyPressed = Gdx.input.isKeyPressed(Keys.N)
						&& player01State == 0) {
					player01State = 2;
					if (isFacingRightP1 == true) {
						curActionP1 = 8;
					} else {
						curActionP1 = 9;
					}
				}
			}
		} else {
			// pauses the music whenever the game is paused
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
		// importing the digital font
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal("assets/gui/digital-7 (mono italic).ttf"));
		// set the size of the digital font
		timerFont = generator.generateFont(30);
		skin = new Skin();
		skin.addRegions(atlas);
		battleMusic.play();
		battleMusic.setLooping(true);
		// battleMusic.setVolume(this.game.masterVolume);
	}

	// called when current screen changes to a different screen
	public void hide() {
		Player.setPauseState(false);
	}

	// called when the game is paused
	public void pause() {
		// isPaused = true;
		Player.setPauseState(true);
	}
	
	//called when the game ends
	public void gameOver()
	{
		// isPaused = true;
		Player.setPauseState(true);
	}

	// called when the game resumes
	public void resume() {
		battleMusic.play();
		// isPaused = false;
		Player.setPauseState(false);
	}

	// called when this screen should release all resources
	public void dispose() {
		batch.dispose();
		skin.dispose();
		atlas.dispose();
		battleMusic.dispose();
	}

	// handles keyboard input involving single press key actions
	public void keyDown(int keycode) {
		switch (keycode) {
		// checks whether the attack key was pressed
		case Keys.M:
			if (!Player.getPausedState()) {
				// checks if the player is jumping and plays the jumping attack
				// animation
				if (grounded == false && player01State == 0) {
					// plays the sound for a basic attack
					attack01.play();

					// State the the attack hasn't hit P2 yet
					Player.p1AttackHit = false;

					spriteClass.resetFrameIndexP1();
					player01State = 1;
					if (isFacingRightP1 == true) {
						curActionP1 = 16;
					} else {
						curActionP1 = 17;
					}
					break;
				} else {
					// checks whether the player is standing up or ducking and
					// plays
					// the coinciding attack animation
					switch (player01State) {
					case 0:
						// plays the sound for a basic attack
						attack01.play();

						// State the the attack hasn't hit P2 yet
						Player.p1AttackHit = false;

						// resets the frameIndex for player 1 to animate the
						// attack
						// for the first frame
						spriteClass.resetFrameIndexP1();
						// sets the player state to attacking
						player01State = 1;
						if (isFacingRightP1 == true) {
							curActionP1 = 4;
						} else {
							curActionP1 = 5;
						}
						break;
					case 3:
						// plays the sound for a basic attack
						attack01.play();

						// State the the attack hasn't hit P2 yet
						Player.p1AttackHit = false;

						// resets the frameIndex for player 1 to animate the
						// attack
						// for the first frame
						spriteClass.resetFrameIndexP1();
						// sets the player state to attacking
						player01State = 1;
						if (isFacingRightP1 == true) {
							curActionP1 = 12;
						} else {
							curActionP1 = 13;
						}
						break;
					}
				}
			}
			// changes the functionality of the attack key to select when the
			// game is paused
			else {
				switch (optionIndex) {
				case 0:
					resume();
					break;
				case 1:
					break;
				case 2:
					hide();
					game.setScreen(new MenuScreen(game));
					break;
				}
			}
			return;
			// checks whether the P key was pressed
		case Keys.P:
			if (!Player.getPausedState()) {
				pause();
			} else {
				resume();
			}
			return;
			// checks whether the up arrow key was pressed
		case Keys.UP:
			if (!Player.getPausedState() && !isGameOver) {
				// resets the frameIndex for player 1 to animate the jump
				// from the first frame
				spriteClass.resetFrameIndexP1();
				// makes player 1 jump
				if (grounded == true) {
					grounded = false;
					player1YPos += jumpStrength;
					if (isFacingRightP1 == true) {
						curActionP1 = 14;
					} else {
						curActionP1 = 15;
					}
					break;
				}
			}
			// changes the functionality of the up arrow key when the game is
			// paused
			else if (Player.getPausedState()) {
				optionIndex--;
				if (optionIndex < 0) {
					optionIndex = 2;
				}
			}
			// changes the functionality of the up arrow key when the game is
			// over
			else if (isGameOver) {
				gameOverIndex--;
				if (gameOverIndex < 0) {
					gameOverIndex = 1;
				}
			}
			return;
			// checks whether the down arrow key was pressed
		case Keys.DOWN:
			// changes the functionality of the down arrow key when the game is
			// paused
			if (Player.getPausedState()) {
				optionIndex++;
				if (optionIndex > 2) {
					optionIndex = 0;
				}
			}
			// changes the functionality of the down arrow key when the game is
			// over
			else if (isGameOver) {
				gameOverIndex++;
				if (gameOverIndex > 1) {
					gameOverIndex = 0;
				}
			}
			return;

			// checks whether the enter key was pressed
		case Keys.ENTER:
			if (isGameOver) {
				if (gameOverIndex == 0) {
					// if index is 0 redirect to character selection screen
					game.setScreen(new CharacterSelectScreen(game));
				} else {
					// if index is 1 redirect to menu screen
					game.setScreen(new MenuScreen(game));
				}
			}
			return;
		}
	}

	// checks when keys are released
	public void keyUp(int keycode) {
		switch (keycode) {
		// checks whether the block key was released
		case Keys.N:
			player01State = 0;
			return;
			// checks whether the down arrow key was released
		case Keys.DOWN:
			player01State = 0;
			return;
		}
	}
}
