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
	private Texture backgroundTex, hpBarLeftTex, hpBarRightTex, roundsTex, player01SmallTex;
	private int playerXPos = 50, playerYPos = 15, moveSpeed = 4, player01State = 0;
	private Sound attack01;
	private boolean isKeyPressed, isFacingRight;
	private int curAction = 0;
	private Texture curAnimation;

	

	// constructor to keep a reference to the main Game class
	public GameScreen(Game game) {
		super();
		this.game = game;
		attack01 = Gdx.audio.newSound(Gdx.files.internal("assets/audioFiles/ichigoCombat/ichigoAttack01.wav"));
		backgroundTex = new Texture(Gdx.files.internal("assets/sprites/backgrounds/battle_BG_01.png"));
	}

	// called when the screen should render itself
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		spriteClass.setSheetVals(1, curAction);
		curAnimation = spriteClass.setAnimation();
		batch.begin();
		batch.draw(backgroundTex, 0, 0, 800, 600, 0, 0, backgroundTex.getWidth(), backgroundTex.getHeight(), false, false);
		batch.draw(new TextureRegion(curAnimation, spriteClass.getFrameIndex() * (curAnimation.getWidth() / 6), 0, curAnimation.getWidth() / 6, curAnimation.getHeight()), playerXPos, playerYPos);
		batch.end();

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
	}

	// called when the screen resized
	public void resize(int width, int height) {
		
	}

	// called when this screen becomes the current screen for a Game
	public void show() {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("assets/gui/button.pack");
		skin = new Skin();
		skin.addRegions(atlas);
		battleMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/audioFiles/battleMusic/battleMusic_01.mp3"));
		battleMusic.play();
		battleMusic.setLooping(true);
		battleMusic.setVolume(this.game.masterVolume);
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

	public void keyDown(int keycode)
	{
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
}
