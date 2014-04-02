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
	private Texture curAnimationP1, curAnimationP2;
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
	
	public void setSheetValsP1(int character, int action)
	{
		//checks which character is selected
		switch(character)
		{
		//if the character is Ichigo, sets the # of frames and rows accordingly
		case 0:
			numOfFrames = 6;
			numOfRows = 1;
			//checks what is Ichigo's current action
			switch(action)
			{
			case 0:
				//f represents the total time for all frames to run through once in a given animation
				f = 1f;
				//loads texture corresponding to current action
				curAnimationP1 = ichigoIdleRTex;
				break;
			case 1:
				f = 1f;
				curAnimationP1 = ichigoIdleLTex;
				break;
			case 2:
				f = 1f;
				curAnimationP1 = ichigoRunRTex;
				break;
			case 3:
				f = 1f;
				curAnimationP1 = ichigoRunLTex;
				break;
			case 4:
				f = 0.80f;
				curAnimationP1 = ichigoAttackRTex;
				break;
			case 5:
				f = 0.80f;
				curAnimationP1 = ichigoAttackLTex;
				break;
			case 6:
				f = 1f;
				curAnimationP1 = ichigoDuckRTex;
				break;
			case 7:
				f = 1f;
				curAnimationP1 = ichigoDuckLTex;
				break;
			case 8:
				f = 1f;
				curAnimationP1 = ichigoBlockRTex;
				break;
			case 9:
				f = 1f;
				curAnimationP1 = ichigoBlockLTex;
				break;
			case 10:
				f = 1f;
				curAnimationP1 = ichigoDuckingBlockRTex;
				break;
			case 11:
				f = 1f;
				curAnimationP1 = ichigoDuckingBlockLTex;
				break;
			case 12:
				f = 0.80f;
				curAnimationP1 = ichigoDuckingAttackRTex;
				break;
			case 13:
				f = 0.80f;
				curAnimationP1 = ichigoDuckingAttackLTex;
				break;
			}
			break;
		case 1:
			//if the character is Byakuya, sets the # of frames and rows accordingly
			numOfFrames = 4;
			numOfRows = 1;
			break;
		}
	}
	
	public void setSheetValsP2(int character, int action)
	{
		//checks which character is selected
		switch(character)
		{
		//if the character is Ichigo, sets the # of frames and rows accordingly
		case 0:
			numOfFrames = 6;
			numOfRows = 1;
			//checks what is Ichigo's current action
			switch(action)
			{
			case 0:
				//f represents the total time for all frames to run through once in a given animation
				f = 1f;
				//loads texture corresponding to current action
				curAnimationP2 = ichigoIdleRTex;
				break;
			case 1:
				f = 1f;
				curAnimationP2 = ichigoIdleLTex;
				break;
			case 2:
				f = 1f;
				curAnimationP2 = ichigoRunRTex;
				break;
			case 3:
				f = 1f;
				curAnimationP2 = ichigoRunLTex;
				break;
			case 4:
				f = 0.80f;
				curAnimationP2 = ichigoAttackRTex;
				break;
			case 5:
				f = 0.80f;
				curAnimationP2 = ichigoAttackLTex;
				break;
			case 6:
				f = 1f;
				curAnimationP2 = ichigoDuckRTex;
				break;
			case 7:
				f = 1f;
				curAnimationP2 = ichigoDuckLTex;
				break;
			case 8:
				f = 1f;
				curAnimationP2 = ichigoBlockRTex;
				break;
			case 9:
				f = 1f;
				curAnimationP2 = ichigoBlockLTex;
				break;
			case 10:
				f = 1f;
				curAnimationP2 = ichigoDuckingBlockRTex;
				break;
			case 11:
				f = 1f;
				curAnimationP2 = ichigoDuckingBlockLTex;
				break;
			case 12:
				f = 0.80f;
				curAnimationP2 = ichigoDuckingAttackRTex;
				break;
			case 13:
				f = 0.80f;
				curAnimationP2 = ichigoDuckingAttackLTex;
				break;
			}
			break;
		case 1:
			//if the character is Byakuya, sets the # of frames and rows accordingly
			numOfFrames = 4;
			numOfRows = 1;
			break;
		}
	}
	
	//called in GameScreen class to tell it which texture to draw for P1
	public Texture setAnimationP1()
	{
		return curAnimationP1;
	}
	
	//called in GameScreen class to tell it which texture to draw for P2
	public Texture setAnimationP2()
	{
		return curAnimationP2;
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