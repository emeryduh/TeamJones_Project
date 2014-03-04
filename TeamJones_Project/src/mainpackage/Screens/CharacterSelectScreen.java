package mainpackage.Screens;

import mainpackage.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

public class CharacterSelectScreen implements Screen {

	// variables
	private Texture splashTexture;
	private Sprite splashSprite;
	private SpriteBatch batch;
	private Game game;
	private SpriteBatch batch1;
	private Sprite splashSprite1;
	private Texture splashTexture1;
	private SpriteBatch batch2;
	private Sprite splashSprite2;
	private Texture splashTexture2;
	private TextButton btnStartGame;
	private TextButton btnOptions;
	private BitmapFont whiteFont, blackFont;
	private TextureAtlas atlas;
	private Skin skin;
	private Stage stage;
	private SpriteBatch Stagebatch;

	
	
	
	


	
	// constructor to keep a reference to the main Game class
	public CharacterSelectScreen(Game game) {
		this.game = game;
	}

	// called when the screen should render itself.
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);		
		batch.begin();
		splashSprite.draw(batch);
		batch.end();
		//Character 1
		batch1.begin();
		splashSprite1.draw(batch1);
		batch1.end();
		// Character 2
		batch2.begin();
		splashSprite2.draw(batch2);
		batch2.end();
		//Stage
		stage.act(delta);
		// draw objects into the screen
		Stagebatch.begin();
		stage.draw();
		Stagebatch.end();
		
	}

	// called when the screen resized
	public void resize(int width, int height) {
		if (stage == null) {
			stage = new Stage(width, height, true);
		}

		stage.clear();
		Gdx.input.setInputProcessor(stage);

		// creates start game button
		TextButtonStyle txtStartgameStyle = new TextButtonStyle();
		txtStartgameStyle.up = skin.getDrawable("button");
		txtStartgameStyle.down = skin.getDrawable("buttonpressed");
		txtStartgameStyle.font = whiteFont;
		btnStartGame = new TextButton("Start Game", txtStartgameStyle);
		btnStartGame.setWidth(140f);
		btnStartGame.setHeight(40f);
		btnStartGame.setX(300);
		btnStartGame.setY(80);

		// start game events
		btnStartGame.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// this is where functionality for next screen should be called
				game.setScreen(new CharacterSelectScreen(game));
			}
		});

				
		// adds an start game and option actors to the root of the stage.
		stage.addActor(btnStartGame);
			}

	// called when this screen becomes the current screen for a Game.
	public void show() {
		splashTexture = new Texture(Gdx.files.internal("assets/gui/cbg.jpg"));
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		splashSprite = new Sprite(splashTexture);		
		splashSprite.setX(Gdx.graphics.getWidth() / 2
				- (splashSprite.getWidth() / 2));
		splashSprite.setY(Gdx.graphics.getHeight() / 2
				- (splashSprite.getHeight() / 2));
		batch = new SpriteBatch();
		splashTexture1 = new Texture(Gdx.files.internal("assets/gui/Character1.PNG"));
		splashTexture1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		splashSprite1 = new Sprite(splashTexture1);		
		splashSprite1.setX(100);
		splashSprite1.setY(180);
		
		
		
		batch1 = new SpriteBatch();
		splashTexture2 = new Texture(Gdx.files.internal("assets/gui/Character2.PNG"));
		splashTexture2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		splashSprite2 = new Sprite(splashTexture2);		
		splashSprite2.setX(450);
		splashSprite2.setY(180);
		
		
		batch2 = new SpriteBatch();
		
		// Button Assets
		Stagebatch = new SpriteBatch();
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
		dispose();
	}

	// called when game paused.
	public void pause() {

	}

	// called when game resume
	public void resume() {

	}

	// called when this screen should release all resources.
	public void dispose() {
		splashTexture.dispose();
		batch.dispose();
		splashTexture1.dispose();
		batch1.dispose();
		
}}
