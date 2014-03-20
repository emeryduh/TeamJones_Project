package mainpackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Sprite 
{
	private int frameWidth;
	private int frameHeight;
	private Rectangle[] frames;
	private int frameIndex = 0, rows, cols;
	private float frameLength;
	private float elapsedTime;
	private int numOfFrames, numOfRows;
	private TextureRegion curFrame;
	private Texture curAnimation;
	//loads character textures
	private Texture ichigoIdleRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Standing_Right.png"));
	private Texture ichigoIdleLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Standing_Left.png"));
	private Texture ichigoRunRTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Running_Right.png"));
	private Texture ichigoRunLTex = new Texture(Gdx.files.internal("assets/sprites/spritesheets/Ichigo/Ichigo_Running_Left.png"));
	
	public Sprite()
	{}
	
	public void setSheetVals(int character, int action)
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
				//loads texture corresponding to current action
				curAnimation = ichigoIdleRTex;
				//sets the width and height of a single frame
				frameWidth = curAnimation.getWidth() / numOfFrames;
				frameHeight = curAnimation.getHeight() / numOfRows;
				break;
			case 1:
				curAnimation = ichigoIdleLTex;
				frameWidth = curAnimation.getWidth() / numOfFrames;
				frameHeight = curAnimation.getHeight() / numOfRows;
				break;
			case 2:
				curAnimation = ichigoRunRTex;
				frameWidth = curAnimation.getWidth() / numOfFrames;
				frameHeight = curAnimation.getHeight() / numOfRows;
				break;
			case 3:
				curAnimation = ichigoRunLTex;
				frameWidth = curAnimation.getWidth() / numOfFrames;
				frameHeight = curAnimation.getHeight() / numOfRows;
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
	
	public Texture setAnimation()
	{
		return curAnimation;
	}
	
	public TextureRegion Animate()
	{
		//creates rectangle array with a size matching the number of frames in sprite-sheet of current action
		frames = new Rectangle[numOfFrames * numOfRows];
		//cycles through rows
		for(rows = 0; rows < numOfRows; rows++)
		{
			//cycles through columns
			for(cols = 0; cols < numOfFrames; cols++)
			{
				//creates a rectangle with a width and height matching current action frame and stores it into rectangle array with element index matching the current frame index
				frames[rows * numOfFrames + cols] = new Rectangle(cols * frameWidth, rows * frameHeight, frameWidth, frameHeight);
			}
		}
		
		//creates and stores a texture region into curFrame with parameters coinciding to current; frame index, frame width, frame height 
		curFrame = new TextureRegion(curAnimation, cols * frameWidth, rows * frameHeight, frameWidth, frameHeight);
		//sends the texture region to be used by GameScreen class
		return curFrame;
	}
	
	public int getFrameIndex()
	{
		//sets frame length based on total number of frames in current action sprite-sheet
		frameLength = 1.0f / (numOfFrames * numOfRows);
		
		//updates how much time has passed
		elapsedTime += Gdx.graphics.getDeltaTime();
		//checks if the amount of time passed has gone over the time length of a single frame
		if(elapsedTime > frameLength)
		{
			//goes to next frame
			frameIndex++;
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