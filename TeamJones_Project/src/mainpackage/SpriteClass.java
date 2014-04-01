package mainpackage;
                               
import com.badlogic.gdx.Gdx;
import mainpackage.Screens.GameScreen;
import com.badlogic.gdx.graphics.Texture;

public class SpriteClass 
{
	Game game;
	GameScreen gameScreen;
	private int frameIndex = 0;
	private float frameLength, f;
	private float elapsedTime;
	private int numOfFrames, numOfRows;
	private Texture curAnimation;
	//loads the selector texture
	private Texture selectorTex = new Texture(Gdx.files.internal("assets/gui/selectorTex.png"));
	//loads idle textures
	private Texture ichigoIdleRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Standing_Right.png"));
	private Texture ichigoIdleLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Standing_Left.png"));
	//loads the running textures
	private Texture ichigoRunRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Running_Right.png"));
	private Texture ichigoRunLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Running_Left.png"));
	//loads the first attack textures
	private Texture ichigoAttackRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Attack_01_Right.png"));
	private Texture ichigoAttackLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Attack_01_Left.png"));
	//loads the ducking textures
	private Texture ichigoDuckRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Ducking_Right.png"));
	private Texture ichigoDuckLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Ducking_Left.png"));
	//loads the blocking textures
	private Texture ichigoBlockRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Blocking_Right.png"));
	private Texture ichigoBlockLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Blocking_Left.png"));
	//loads the ducking block textures
	private Texture ichigoDuckingBlockRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_DuckingBlock_Right.png"));
	private Texture ichigoDuckingBlockLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_DuckingBlock_Left.png"));
	//loads the ducking attack textures
	private Texture ichigoDuckingAttackRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_DuckingAttack_Right.png"));
	private Texture ichigoDuckingAttackLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_DuckingAttack_Left.png"));
	
	public SpriteClass()
	{
	}
	
	public void setSheetVals(int character, int action)
	{
		//checks which character is selected
		switch(character)
		{
		//if its the selector Icon
		case 0:
			numOfFrames = 6;
			numOfRows = 1;
			switch(action)
			{
			case 0:
				f = 1.0f;
				//sets the current sprite-sheet to the selector sprite-sheet
				curAnimation = selectorTex;
				break;
			}
			break;
		
		//if the character is Ichigo, sets the # of frames and rows accordingly
		case 1:
			numOfFrames = 6;
			numOfRows = 1;
			//checks what is Ichigo's current action
			switch(action)
			{
			case 0:
				//f represents the total time for all frames to run through once in a given animation
				f = 0.80f;
				//loads texture corresponding to current action
				curAnimation = ichigoIdleRTex;
				break;
			case 1:
				f = 0.80f;
				curAnimation = ichigoIdleLTex;
				break;
			case 2:
				f = 0.80f;
				curAnimation = ichigoRunRTex;
				break;
			case 3:
				f = 0.80f;
				curAnimation = ichigoRunLTex;
				break;
			case 4:
				f = 0.60f;
				curAnimation = ichigoAttackRTex;
				break;
			case 5:
				f = 0.60f;
				curAnimation = ichigoAttackLTex;
				break;
			case 6:
				f = 0.80f;
				curAnimation = ichigoDuckRTex;
				break;
			case 7:
				f = 0.80f;
				curAnimation = ichigoDuckLTex;
				break;
			case 8:
				f = 0.80f;
				curAnimation = ichigoBlockRTex;
				break;
			case 9:
				f = 0.80f;
				curAnimation = ichigoBlockLTex;
				break;
			case 10:
				f = 0.80f;
				curAnimation = ichigoDuckingBlockRTex;
				break;
			case 11:
				f = 0.80f;
				curAnimation = ichigoDuckingBlockLTex;
				break;
			case 12:
				f = 0.60f;
				curAnimation = ichigoDuckingAttackRTex;
				break;
			case 13:
				f = 0.60f;
				curAnimation = ichigoDuckingAttackLTex;
				break;
			}
			break;
		case 2:
			//if the character is Byakuya, sets the # of frames and rows accordingly
			numOfFrames = 4;
			numOfRows = 1;
			break;
		}
	}
	
	//called in GameScreen class to tell it which texture to draw
	public Texture setAnimation()
	{
		return curAnimation;
	}
	
	//called to reset the frameIndex to 0
	public void resetFrameIndex()
	{
		frameIndex = 0;
	}
	
	//called the get the current frame #
	public int getFrameIndex()
	{
		//sets frame length based on total number of frames in current action sprite-sheet
		frameLength = f / (numOfFrames * numOfRows);
		
		//updates how much time has passed
		elapsedTime += Gdx.graphics.getDeltaTime();
		//checks if the amount of time passed has gone over the time length of a single frame
		if(elapsedTime > frameLength)
		{
			//if(gameScreen.pausedState() == false)
			//{
				//goes to next frame
				frameIndex++;
			//}
			//resets elapsedTime so that it is once again less than the time length of a single frame
			elapsedTime = 0;
			//checks if the frame index has reached last frame
			if(frameIndex > numOfFrames - 1)
			{
				//resets to first frame
				frameIndex = 0;
			}
		}	
		return frameIndex;
	}
}