package mainpackage.Screens;

import org.lwjgl.input.Keyboard;

import mainpackage.Game;
import mainpackage.PlayerInput;
import mainpackage.Sprite;

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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameScreen implements Screen {
	// variables
	private Game game;
	private Sprite sprite;
	private SpriteBatch batch;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private int groundOffset = 15;
	private int frameIndex = 0;
	private Music battleMusic;
	private Texture backgroundTex, hpBarLeftTex, hpBarRightTex, roundsTex, player01Tex, player01SmallTex, player01NameTex;
	private Image backgroundImg, hpBarLeftImg, hpBarRightImg, roundsImg, player01Img, player01SmallImg, player01NameImg;
	private int playerPos = 50, moveSpeed = 5;
	private Sound attack01;
	

	// constructor to keep a reference to the main Game class
	public GameScreen(Game game) {
		super();
		this.game = game;
	}

	// called when the screen should render itself
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		attack01 = Gdx.audio.newSound(Gdx.files.internal("assets/audioFiles/ichigoCombat/ichigoAttack01.wav"));

		stage.act(delta);
		batch.begin();
		stage.draw();
		batch.end();

		//enabling keyboard events
		PlayerInput playerInput = new PlayerInput(game);
		// set the input processor
		Gdx.input.setInputProcessor(playerInput);
		// this will enable continuous key pressing
		Keyboard.enableRepeatEvents(true);
	}

	// called when the screen resized
	public void resize(int width, int height) {
		if (stage == null) {
			stage = new Stage(width, height, true);
		}

		stage.clear();
		
		//loads the background texture
		backgroundTex = new Texture(Gdx.files.internal("assets/sprites/backgrounds/battle_BG_01.png"));
		backgroundImg = new Image(new TextureRegion(backgroundTex));
		//fill the screen with background texture
		backgroundImg.setFillParent(true);
		//add background image to screen
		stage.addActor(backgroundImg);
		
		//loads left hp bar texture
		hpBarLeftTex = new Texture(Gdx.files.internal("assets/gui/healthBarLeft.png"));
		hpBarLeftImg = new Image(new TextureRegion(hpBarLeftTex));
		//positions the hp bar texture
		hpBarLeftImg.setBounds(0, 400, 400, 200);
		//add hp bar image to screen
		stage.addActor(hpBarLeftImg);
		
		//loads right hp bar texture
		hpBarRightTex = new Texture(Gdx.files.internal("assets/gui/healthBarRight.png"));
		hpBarRightImg = new Image(new TextureRegion(hpBarRightTex));
		//positions the hp bar texture
		hpBarRightImg.setBounds(400, 400, 400, 200);
		//add hp bar image to screen
		stage.addActor(hpBarRightImg);
		
		//loads rounds texture on the left
		roundsTex = new Texture(Gdx.files.internal("assets/gui/roundsWon_empty.png"));
		roundsImg = new Image(new TextureRegion(roundsTex));
		//positions the rounds texture on the left
		roundsImg.setBounds(350, 430, 25, 25);
		//add rounds texture on the left side of screen
		stage.addActor(roundsImg);
		
		//loads rounds texture on the right
		roundsTex = new Texture(Gdx.files.internal("assets/gui/roundsWon_empty.png"));
		roundsImg = new Image(new TextureRegion(roundsTex));
		//positions the rounds texture on the right
		roundsImg.setBounds(425, 430, 25, 25);
		//add rounds texture on the right side of screen
		stage.addActor(roundsImg);
		
		//loads player 1 texture
		player01Tex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Standing_Right.png"));
		player01Img = new Image(new TextureRegion(player01Tex, frameIndex, 0, player01Tex.getWidth() / 6, player01Tex.getHeight()));
		//positions player 1 texture
		player01Img.setPosition(playerPos, groundOffset);
		//add player 1 image to screen
		stage.addActor(player01Img);
		
		//loads small face texture for player 1
		player01SmallTex = new Texture(Gdx.files.internal("assets/sprites/standAlone/ichigoSmall.png"));
		player01SmallImg = new Image(new TextureRegion(player01SmallTex));
		//positions small face texture
		player01SmallImg.setBounds(70, 460, 80, 80);
		//add small face texture to the screen
		stage.addActor(player01SmallImg);
		
		//loads name texture for player 1
		player01NameTex = new Texture(Gdx.files.internal("assets/gui/ichigoName.png"));
		player01NameImg = new Image(new TextureRegion(player01NameTex));
		//positions name texture
		player01NameImg.setBounds(150, 400, 200, 50);
		//add name texture to the screen
		stage.addActor(player01NameImg);
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
		stage.dispose();
		battleMusic.dispose();
	}

	public void keyDown(int keycode)
	{
		switch(keycode)
		{
		case Keys.RIGHT:
			playerPos += moveSpeed;
			player01Img.setPosition(playerPos, groundOffset);
			return;
		case Keys.LEFT:
			playerPos -= moveSpeed;
			player01Img.setPosition(playerPos, groundOffset);
			return;
		case Keys.A:
			attack01.play();
			return;
		case Keys.DOWN:
			return;
		case Keys.UP:
			return;
		}
	}
}
