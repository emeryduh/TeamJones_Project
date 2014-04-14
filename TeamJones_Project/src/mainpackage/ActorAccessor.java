package mainpackage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import aurelienribon.tweenengine.TweenAccessor;

public class ActorAccessor implements TweenAccessor<Sprite> {
	// constants
	public static final int POS_XY = 1;
    public static final int CPOS_XY = 2;
    public static final int SCALE_XY = 3;
    public static final int OPACITY = 4;
    public static final int ROTATE = 5;
    public static final int POS_Y = 6;
    public static final int WIDTH = 7;
    public static final int POS_X = 8;
    public static final int HEIGHT = 9;
	@Override
	public int getValues(Sprite target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case POS_XY:
		    returnValues[0] = target.getX();
		    returnValues[1] = target.getY();
		    return 2;
		case CPOS_XY:
		    returnValues[0] = target.getX() + target.getWidth() / 2;
		    returnValues[1] = target.getY() + target.getHeight() / 2;
		    return 2;

		case SCALE_XY:
		    returnValues[0] = target.getScaleX();
		    returnValues[1] = target.getScaleY();
		    return 2;
		case OPACITY:
		    returnValues[0] = target.getColor().a;
		    return 1;
		case ROTATE:
		    returnValues[0] = target.getRotation();
		    return 1;
		case POS_Y:
			 returnValues[0] = target.getY();
		    return 1;
		case WIDTH:
			 returnValues[0] = target.getWidth();
		    return 1;
		case POS_X:
			 returnValues[0] = target.getX();
		    return 1;
		case HEIGHT:
			 returnValues[0] = target.getHeight();
		    return 1;
		default:
		    assert false;
		    return -1;
		}
	    }

	@Override
	 public void setValues(Sprite target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case POS_XY:
		    target.setPosition(newValues[0], newValues[1]);
		    break;
		case CPOS_XY:
		    target.setPosition(newValues[0] - target.getWidth() / 2,
			    newValues[1] - target.getHeight() / 2);
		    break;
		case SCALE_XY:
		    target.setScale(newValues[0], newValues[1]);
		    break;
		case ROTATE:
		    target.setRotation(newValues[0]);
		    break;
		case OPACITY:
		    Color c = target.getColor();
		    c.set(c.r, c.g, c.b, newValues[0]);
		    target.setColor(c);
		    break;
		case POS_Y:
			target.setY(newValues[0]);
		    break;		
		case POS_X:
			target.setX(newValues[0]);
		    break; 
		}
	    }

}
