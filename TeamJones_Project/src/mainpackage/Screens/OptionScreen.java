package mainpackage.Screens;

import mainpackage.Game;
import mainpackage.Battle;
import mainpackage.SoundFiles;
import mainpackage.PlayerInput;

import org.lwjgl.input.Keyboard;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class OptionScreen implements Screen {

	// variables
	private Game game;
	private Stage stage;
	private BitmapFont whiteFont, blackFont;
	private TextureAtlas atlas;
	private Skin skin;
	private SpriteBatch batch;
	private TextButton btnBack;
	private Texture splashTexture;
	private SoundFiles soundFiles;
	private Slider masterSlider, bgmSlider, sfxSlider;
	public float volume;
	private MenuScreen menuScreen;

	

	// constructor to keep a reference to the main Game class
	public OptionScreen(Game game) {
		this.game = game;
		
	}
	

	// called when the screen should render itself.
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		// draw objects into the screen
		batch.begin();
		stage.draw();
		
		batch.end();
		batch.begin();
		blackFont.setColor(0,0,0,1);
		whiteFont.draw(batch, "Options" , 75, 550);
		whiteFont.draw(batch, "Master Volume:", 75, 415);
		whiteFont.draw(batch, "SFX Volume:", 75, 315);
		whiteFont.draw(batch, "BGM Volume:", 75, 215);
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
		
		Gdx.input.setInputProcessor(stage);
		

		// set the background image to the menu screen
		splashTexture = new Texture(Gdx.files.internal("assets/gui/optionsMenuBG.png"));
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image img = new Image(new TextureRegion(splashTexture));
		// fit the full image to the screen
		img.setFillParent(true);
		// adds an actor to the root of the stage.
		stage.addActor(img);

		// creates back button
		TextButtonStyle txtBackStyle = new TextButtonStyle();
		txtBackStyle.up = skin.getDrawable("button");
		txtBackStyle.down = skin.getDrawable("buttonpressed");
		txtBackStyle.font = blackFont;
		btnBack = new TextButton("Back", txtBackStyle);
		btnBack.setWidth(140f);
		btnBack.setHeight(40f);
		btnBack.setX(600);
		btnBack.setY(60);

		// back button events
		btnBack.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// this is where functionality for next screen should be called
				//play menu select sound
				soundFiles = new SoundFiles();
				soundFiles.playSound("menuSelect");
				
				//go to menu screen
				game.setScreen(new MenuScreen(game));
			}
		});

		// adds back button actor to the root of the stage.
		stage.addActor(btnBack);
		
		
		//Add the Slider
		
		//Slider Styling
		SliderStyle sliderStyle = new SliderStyle();
		sliderStyle.knob = skin.getDrawable("slider");
		sliderStyle.background = skin.getDrawable("sliderbackground");
		
		
		//Add the master volume slider
		masterSlider = new Slider(0f,1f,0.1f, false,sliderStyle);
		masterSlider.setValue(game.masterVolume);
		masterSlider.setWidth(200f);
		masterSlider.setHeight(40f);
		masterSlider.setX(75);
		masterSlider.setY(350);
		
		//Add the Background Music volume slider
		bgmSlider = new Slider(0f,1f,0.1f, false, sliderStyle);
		bgmSlider.setValue(game.bgmVolume);
		bgmSlider.setWidth(200f);
		bgmSlider.setHeight(40f);
		bgmSlider.setX(75);
		bgmSlider.setY(250);
		
		//Add the Sound Effect volume slider
		sfxSlider = new Slider(0f,1f,0.1f, false, sliderStyle);
		sfxSlider.setValue(game.sfxVolume);
		sfxSlider.setWidth(200f);
		sfxSlider.setHeight(40f);
		sfxSlider.setX(75);
		sfxSlider.setY(150);
		
		
		stage.addActor(masterSlider);
		stage.addActor(bgmSlider);
		stage.addActor(sfxSlider);
		
		masterSlider.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				game.masterVolume = masterSlider.getValue();
			}
		});
		
		sfxSlider.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				game.sfxVolume = sfxSlider.getValue();
			}
		});
		
		
		
		
	}
	
	// called when this screen becomes the current screen for a Game.
	public void show() {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("assets/gui/button.pack");
		skin = new Skin();
		skin.addRegions(atlas);
		whiteFont = new BitmapFont(
				Gdx.files.internal("assets/gui/whitefont.fnt"), false);
		blackFont = new BitmapFont(
				Gdx.files.internal("assets/gui/blackfont.fnt"), false);
		
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
		stage.dispose();
	}
	
	// method to return the key selection
	public void keyDown(int keycode) {		
		if (keycode == Keys.ENTER) {
			game.setScreen(new MenuScreen(game));
			return;
		}
			
	}
}
