package mainpackage.Screens;

import mainpackage.Game;
import mainpackage.SoundFiles;
import mainpackage.PlayerInput;
import mainpackage.TextureFiles;
import mainpackage.SpriteClass;

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
	private Texture backgroundTex, selectorTex, volumeEmptyTex, volumeFullTex;
	private SoundFiles soundFiles = new SoundFiles();
	private float masterVolume = 1.0f, bgmVolume = 0.7f, sfxVolume = 0.5f;
	private int selectorXPos = -30, selectorYPos = 410, offSet = 85;
	

	// constructor to keep a reference to the main Game class
	public OptionScreen(Game game) {
		this.game = game;		
		backgroundTex = new Texture(Gdx.files.internal("assets/gui/optionsMenuBG.png"));
		volumeEmptyTex = new Texture(Gdx.files.internal("assets/gui/volumeBar_Empty.png"));
		volumeFullTex = new Texture(Gdx.files.internal("assets/gui/volumeBar_Full.png"));
	}
	

	// called when the screen should render itself.
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		spriteClass.setSheetVals(0, 0);
		selectorTex = spriteClass.setAnimation();
		// draw objects into the screen
		batch.begin();
		batch.draw(backgroundTex, 0, 0, 800, 600, 0, 0, backgroundTex.getWidth(), backgroundTex.getHeight(), false, false);
		batch.draw(new TextureRegion(selectorTex, spriteClass.getFrameIndex() * (selectorTex.getWidth() / 4), spriteClass.getFrameIndex() * (selectorTex.getHeight() / 3), selectorTex.getWidth() / 4, selectorTex.getHeight() / 3), selectorXPos, selectorYPos);
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
	public void keyDown(int keycode)
	{
		switch(keycode)
		{
		case Keys.UP:
			selectorYPos += offSet;
			soundFiles.playSound("menuTraverse", game.sfxVolume);
			return;
		case Keys.DOWN:
			selectorYPos -= offSet;
			soundFiles.playSound("menuTraverse", game.sfxVolume);
			return;
		case Keys.LEFT:
			soundFiles.playSound("menuTraverse", game.sfxVolume);
			return;
		case Keys.RIGHT:
			soundFiles.playSound("menuTraverse", game.sfxVolume);
			return;
		}
	}
}
