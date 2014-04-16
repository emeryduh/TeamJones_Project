package mainpackage;

import mainpackage.Screens.GameScreen;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Collision {

	/**
	 * This method is to determine whether the players are close enough to each
	 * other to inflict damage.
	 * 
	 * @param p1TextureRegion
	 * @param p2TextureRegion
	 * @param p1XPos
	 * @param p1YPos
	 * @param p2XPos
	 * @param p2YPos
	 * @param p1State
	 * @param p2State
	 */
	public static void checkCollision(TextureRegion p1TexReg,
			TextureRegion p2TexReg, int p1XPos, int p1YPos, int p2XPos,
			int p2YPos, int p1State, int p2State) {

		Rectangle player1Rec = new Rectangle(p1XPos, p1YPos,
				p1TexReg.getRegionWidth(), p1TexReg.getRegionHeight());

		Rectangle player2Rec = new Rectangle(p2XPos, p2YPos,
				p2TexReg.getRegionWidth(), p2TexReg.getRegionHeight());

		// If player1 is attacking
		if (p1State == 1) {
			// The rectangles overlap each other
			if (player1Rec.overlaps(player2Rec) && !Player.p1AttackHit) {
				Player.player2HP -= 5;
				
				// The attack from p1 has hit
				Player.p1AttackHit = true;
				
				// P2 receives hurting animation
				//AI.curActionP2 = 6;
			}
		}

		// If Player2 is attacking
		if (p2State >= 4) {
			// The rectangles overlap each other
			if (player2Rec.overlaps(player1Rec) && !Player.p2AttackHit) {
				if (p1State == 2) {
					Player.player1HP -= 1;
				} else {
					Player.player1HP -= 5;
				}
				// The attack from p1 has hit
				Player.p2AttackHit = true;
				
				// P1 receives hurting animation which is 4 by default
				//GameScreen.player01State = 4;
				//GameScreen.curActionP1 = 18;
			}
		}
	}

}
