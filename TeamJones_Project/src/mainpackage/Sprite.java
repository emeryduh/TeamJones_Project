package mainpackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Sprite 
{
	private int frameWidth;
	private int frameHeight;
	private Rectangle[] frames;
	private int frameIndex = 0;
	private float frameLength;
	private float elapsedTime;
	
	public Sprite()
	{}
	
	public int Animate(int sheetWidth, int sheetHeight, int numOfFrames, int numOfRows)
	{
		frameWidth = sheetWidth / numOfFrames;
		frameHeight = sheetHeight / numOfRows;
		frames = new Rectangle[numOfFrames];
		for(int rows = 0; rows < numOfRows; rows++)
		{
			for(int cols = 0; cols < numOfFrames; cols++)
			{
				frames[rows * numOfFrames + cols] = new Rectangle(cols * frameWidth, rows * frameHeight, frameWidth, frameHeight);
			}
		}
		
		elapsedTime += Gdx.app.getGraphics().getDeltaTime();
		frameLength = numOfFrames * 0.1f;
		if(elapsedTime > frameLength)
		{
			frameIndex++;
			if(frameIndex > numOfFrames)
			{
				frameIndex = 0;
			}
		}
		return frameIndex;
	}
}