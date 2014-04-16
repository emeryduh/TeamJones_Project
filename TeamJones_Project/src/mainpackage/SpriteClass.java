package mainpackage;
                               
import com.badlogic.gdx.Gdx;
import mainpackage.Screens.GameScreen;
import com.badlogic.gdx.graphics.Texture;

public class SpriteClass 
{
	Game game;
	GameScreen gameScreen;
	private int frameIndexP1 = 0, frameIndexP2 = 0, frameIndexGUI = 0;
	private float frameLengthP1, frameLengthP2, frameLengthGUI, f;
	private float elapsedTime, elapsedTimeP2;
	private int numOfFramesP1, numOfRowsP1, numOfFramesP2, numOfRowsP2, numOfFramesGUI, numOfRowsGUI;
	private Texture curAnimationP1, curAnimationP2, curAnimationGUI;
	//loads the textures for any GUI element sprite-sheets
	private Texture selectorTex = new Texture(Gdx.files.internal("assets/gui/selectorTex.png"));
	//loads idle textures for Ichigo
	private Texture ichigoIdleRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Standing_Right.png"));
	private Texture ichigoIdleLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Standing_Left.png"));
	//loads the running textures for Ichigo
	private Texture ichigoRunRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Running_Right.png"));
	private Texture ichigoRunLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Running_Left.png"));
	//loads the first attack textures for Ichigo
	private Texture ichigoAttackRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Attack_01_Right.png"));
	private Texture ichigoAttackLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Attack_01_Left.png"));
	//loads the ducking textures for Ichigo
	private Texture ichigoDuckRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Ducking_Right.png"));
	private Texture ichigoDuckLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Ducking_Left.png"));
	//loads the blocking textures for Ichigo
	private Texture ichigoBlockRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Blocking_Right.png"));
	private Texture ichigoBlockLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Blocking_Left.png"));
	//loads the ducking block textures for Ichigo
	private Texture ichigoDuckingBlockRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_DuckingBlock_Right.png"));
	private Texture ichigoDuckingBlockLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_DuckingBlock_Left.png"));
	//loads the ducking attack textures for Ichigo
	private Texture ichigoDuckingAttackRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_DuckingAttack_Right.png"));
	private Texture ichigoDuckingAttackLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_DuckingAttack_Left.png"));
	//loads the jumping textures for Ichigo
	private Texture ichigoJumpingRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Jumping_Right.png"));
	private Texture ichigoJumpingLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Jumping_Left.png"));
	//loads the jumping attack textures for Ichigo
	private Texture ichigoJumpingAttackRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_JumpingAttack_Right.png"));
	private Texture ichigoJumpingAttackLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_JumpingAttack_Left.png"));
	//loads the hurting textures for Ichigo
	private Texture ichigoHurtingRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Hurting_Right.png"));
	private Texture ichigoHurtingLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Hurting_Left.png"));
	
	//loads idle textures for Byakuya
	private Texture byakuyaIdleRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Byakuya/Byakuya_Standing_Right.png"));
	private Texture byakuyaIdleLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Byakuya/Byakuya_Standing_Left.png"));
	//loads the running textures for Byakuya
	private Texture byakuyaRunRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Byakuya/Byakuya_Running_Right.png"));
	private Texture byakuyaRunLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Byakuya/Byakuya_Running_Left.png"));
	//loads the first attack textures for Byakuya
	private Texture byakuyaAttackRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Byakuya/Byakuya_Attack_01_Right.png"));
	private Texture byakuyaAttackLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Byakuya/Byakuya_Attack_01_Left.png"));
	//loads the hurting textures for Byakuya
	private Texture byakuyaHurtingRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Byakuya/Byakuya_Hurting_Right.png"));
	private Texture byakuyaHurtingLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Byakuya/Byakuya_Hurting_Left.png"));
	
	public SpriteClass()
	{
	}
	
	public void setSheetValsGUI(int guiElement, int action)
	{
		switch(guiElement)
		{
		case 0:
			numOfFramesGUI = 6;
			numOfRowsGUI = 1;
			switch(action)
			{
			case 0:
				f = 1.0f;
				curAnimationGUI = selectorTex;
				break;
			}
			break;
		}
	}
	
	public void setSheetValsP1(int character, int action)
	{
		//checks which character is selected
		switch(character)
		{
		//if the character is Ichigo, sets the # of frames and rows accordingly
		case 0:
			numOfFramesP1 = 6;
			numOfRowsP1 = 1;
			//checks what is Ichigo's current action
			switch(action)
			{
			case 0:
				//f represents the total time for all frames to run through once in a given animation
				f = 1.20f;
				//loads texture corresponding to current action
				curAnimationP1 = ichigoIdleRTex;
				break;
			case 1:
				f = 1.20f;
				curAnimationP1 = ichigoIdleLTex;
				break;
			case 2:
				f = 1.20f;
				curAnimationP1 = ichigoRunRTex;
				break;
			case 3:
				f = 1.20f;
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
				f = 1.20f;
				curAnimationP1 = ichigoDuckRTex;
				break;
			case 7:
				f = 1.20f;
				curAnimationP1 = ichigoDuckLTex;
				break;
			case 8:
				f = 1.20f;
				curAnimationP1 = ichigoBlockRTex;
				break;
			case 9:
				f = 1.20f;
				curAnimationP1 = ichigoBlockLTex;
				break;
			case 10:
				f = 1.20f;
				curAnimationP1 = ichigoDuckingBlockRTex;
				break;
			case 11:
				f = 1.20f;
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
			case 14:
				f = 1.20f;
				curAnimationP1 = ichigoJumpingRTex;
				break;
			case 15:
				f = 1.20f;
				curAnimationP1 = ichigoJumpingLTex;
				break;
			case 16:
				f = 0.80f;
				curAnimationP1 = ichigoJumpingAttackRTex;
				break;
			case 17:
				f = 0.80f;
				curAnimationP1 = ichigoJumpingAttackLTex;
				break;
			case 18:
				f = 0.80f;
				curAnimationP1 = ichigoHurtingRTex;
				break;
			case 19:
				f = 0.80f;
				curAnimationP1 = ichigoHurtingLTex;
				break;
			}
			break;
		case 1:
			//if the character is Byakuya, sets the # of frames and rows accordingly
			numOfFramesP1 = 4;
			numOfRowsP1 = 1;
			//checks what is Byakuya's current action
			switch(action)
			{
			case 0:
				//f represents the total time for all frames to run through once in a given animation
				f = 0.4f;
				//loads texture corresponding to current action
				curAnimationP1 = byakuyaIdleRTex;
				break;
			case 1:
				f = 0.4f;
				curAnimationP1 = byakuyaIdleLTex;
				break;
			case 2:
				f = 0.4f;
				curAnimationP1 = byakuyaRunRTex;
				break;
			case 3:
				f = 0.4f;
				curAnimationP1 = byakuyaRunLTex;
				break;
			case 4:
				f = 0.80f;
				curAnimationP1 = byakuyaAttackRTex;
				break;
			case 5:
				f = 0.80f;
				curAnimationP1 = byakuyaAttackLTex;
				break;
			case 6:
				f = 0.80f;
				curAnimationP1 = byakuyaHurtingRTex;
				break;
			case 7:
				f = 0.80f;
				curAnimationP1 = byakuyaHurtingLTex;
				break;
			}
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
			numOfFramesP2 = 6;
			numOfRowsP2 = 1;
			//checks what is Ichigo's current action
			switch(action)
			{
			case 0:
				//f represents the total time for all frames to run through once in a given animation
				f = 0.4f;
				//loads texture corresponding to current action
				curAnimationP2 = ichigoIdleRTex;
				break;
			case 1:
				f = 0.4f;
				curAnimationP2 = ichigoIdleLTex;
				break;
			case 2:
				f = 0.4f;
				curAnimationP2 = ichigoRunRTex;
				break;
			case 3:
				f = 0.4f;
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
				f = 0.4f;
				curAnimationP2 = ichigoDuckRTex;
				break;
			case 7:
				f = 0.4f;
				curAnimationP2 = ichigoDuckLTex;
				break;
			case 8:
				f = 0.4f;
				curAnimationP2 = ichigoBlockRTex;
				break;
			case 9:
				f = 0.4f;
				curAnimationP2 = ichigoBlockLTex;
				break;
			case 10:
				f = 0.4f;
				curAnimationP2 = ichigoDuckingBlockRTex;
				break;
			case 11:
				f = 0.4f;
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
			case 14:
				f = 0.80f;
				curAnimationP2 = ichigoJumpingRTex;
				break;
			case 15:
				f = 0.80f;
				curAnimationP2 = ichigoJumpingLTex;
				break;
			case 16:
				f = 0.40f;
				curAnimationP2 = ichigoJumpingAttackRTex;
				break;
			case 17:
				f = 0.40f;
				curAnimationP2 = ichigoJumpingAttackLTex;
				break;
			case 18:
				f = 0.80f;
				curAnimationP2 = ichigoHurtingRTex;
				break;
			case 19:
				f = 0.80f;
				curAnimationP2 = ichigoHurtingLTex;
				break;
			}
			break;
		case 1:
			//if the character is Byakuya, sets the # of frames and rows accordingly
			numOfFramesP2 = 4;
			numOfRowsP2 = 1;
			//checks what is Byakuya's current action
			switch(action)
			{
			case 0:
				//f represents the total time for all frames to run through once in a given animation
				f = 0.4f;
				//loads texture corresponding to current action
				curAnimationP2 = byakuyaIdleRTex;
				break;
			case 1:
				f = 0.4f;
				curAnimationP2 = byakuyaIdleLTex;
				break;
			case 2:
				f =0.4f;
				curAnimationP2 = byakuyaRunRTex;
				break;
			case 3:
				f = 0.4f;
				curAnimationP2 = byakuyaRunLTex;
				break;
			case 4:
				f = 0.4f;
				curAnimationP2 = byakuyaAttackRTex;
				break;
			case 5:
				f = 0.4f;
				curAnimationP2 = byakuyaAttackLTex;
				break;
			case 6:
				f = 0.80f;
				curAnimationP2 = byakuyaHurtingRTex;
				break;
			case 7:
				f = 0.80f;
				curAnimationP2 = byakuyaHurtingLTex;
				break;
			}
			break;
		}
	}
	
	//called to send which gui element texture to draw
	public Texture setAnimationGUI()
	{
		return curAnimationGUI;
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
	
	//called to reset the frameIndex to 0 for player 1
	public void resetFrameIndexP1()
	{
		frameIndexP1 = 0;
	}
	
	//called to reset the frameIndex to 0 for player 2
	public void resetFrameIndexP2()
	{
		frameIndexP2 = 0;
	}
	
	//called to acquire the # of frames in a single row for player 1
	public int getNumOfFramesP1()
	{
		return numOfFramesP1;
	}
	
	//called to acquire the # of frames in a single row for player 2
	public int getNumOfFramesP2()
	{
		return numOfFramesP2;
	}
	
	//called to acquire the # of frames in a single row for a gui element
	public int getNumOfFramesGUI()
	{
		return numOfFramesGUI;
	}
	
	//called the get the current frame # for the gui element
	public int getFrameIndexGUI()
	{
		//sets frame length based on total number of frames in current action sprite-sheet
		frameLengthGUI = f / (numOfFramesGUI * numOfRowsGUI);
		
		//updates how much time has passed
		elapsedTime += Gdx.graphics.getDeltaTime();
		//checks if the amount of time passed has gone over the time length of a single frame
		if(elapsedTime > frameLengthGUI)
		{
			//goes to next frame
			frameIndexGUI++;
			//resets elapsedTime so that it is once again less than the time length of a single frame
			elapsedTime = 0;
			//checks if the frame index has reached last frame
			if(frameIndexGUI > numOfFramesGUI - 1)
			{
				//resets to first frame
				frameIndexGUI = 0;
			}
		}	
		return frameIndexGUI;
	}
	
	//called the get the current frame # for player 1
	public int getFrameIndexP1()
	{
		//sets frame length based on total number of frames in current action sprite-sheet
		frameLengthP1 = f / (numOfFramesP1 * numOfRowsP1);
		
		//updates how much time has passed
		elapsedTime += Gdx.graphics.getDeltaTime();
		//checks if the amount of time passed has gone over the time length of a single frame
		if(elapsedTime > frameLengthP1)
		{
			if(!Player.getPausedState())
			{
				//goes to next frame
				frameIndexP1++;
			}
			//resets elapsedTime so that it is once again less than the time length of a single frame
			elapsedTime = 0;
			//checks if the frame index has reached last frame
			if(frameIndexP1 > numOfFramesP1 - 1)
			{
				//resets to first frame
				frameIndexP1 = 0;
			}
		}	
		return frameIndexP1;
	}
	
	//called the get the current frame # for player 2
	public int getFrameIndexP2()
	{
		//sets frame length based on total number of frames in current action sprite-sheet
		frameLengthP2 = f / (numOfFramesP2 * numOfRowsP2);
		
		//updates how much time has passed
		elapsedTimeP2 += Gdx.graphics.getDeltaTime();
		//checks if the amount of time passed has gone over the time length of a single frame
		if(elapsedTimeP2 > frameLengthP2)
		{
			if(!Player.getPausedState())
			{
				//goes to next frame
				frameIndexP2++;
			}
			//resets elapsedTime so that it is once again less than the time length of a single frame
				elapsedTimeP2 = 0;
			//checks if the frame index has reached last frame
			if(frameIndexP2 > numOfFramesP2 - 1)
			{
				//resets to first frame
				frameIndexP2 = 0;
			}
		}	
		return frameIndexP2;
	}
}