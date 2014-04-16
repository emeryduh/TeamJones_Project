package mainpackage;

public class AI {

	private static int aiAttackRange = 100;
	private static int moveSpeedP2 = 4;

	// Player 2 state
	public static int curActionP2 = 0;

	// Holds the milliseconds of time of when the last attack occurred
	private static long lastAttack = 0;

	// Boolean value to hold that the AI is taking damage
	public static boolean isHurt = false;

	// Hurt time & duration
	private static long lastHurt = 0;
	private static long hurtDuration = 700;

	private final static int IDLE_RIGHT = 0;
	private final static int IDLE_LEFT = 1;
	private final static int RUN_RIGHT = 2;
	private final static int RUN_LEFT = 3;
	private final static int ATTACK_RIGHT = 4;
	private final static int ATTACK_LEFT = 5;
	private final static int HURT_RIGHT = 6;
	private final static int HURT_LEFT = 7;

	public static int runLogic(int playerXPos, int player2XPos) {
		// If Player2 AI is currently being hurt
		if (isHurt) {
			// If the AI hasn't been hurt for hurtDuration then continue
			// Else stop hurting animation
			if (lastHurt <= (System.currentTimeMillis() - hurtDuration)) {
				if (playerXPos < player2XPos) {
					curActionP2 = HURT_LEFT;
				} else {
					curActionP2 = HURT_RIGHT;
				}
			} else {
				isHurt = false;
				curActionP2 = IDLE_LEFT;
			}
			return player2XPos;
		} 
		// If Player2 AI is NOT being hurt
		else {
			if (playerXPos < player2XPos - aiAttackRange) {

				player2XPos -= moveSpeedP2;
				curActionP2 = RUN_LEFT;

			} else if (playerXPos > player2XPos + aiAttackRange) {

				player2XPos += moveSpeedP2;
				curActionP2 = RUN_RIGHT;

			} else if (Math.abs(playerXPos - player2XPos) < aiAttackRange * 2) {
				// Allows damage to occur (Player.p2Attackhit = false) every 500
				// milliseconds
				if (lastAttack <= (System.currentTimeMillis() - 500)) {
					// Set the attack hit to false
					Player.p2AttackHit = false;
					lastAttack = System.currentTimeMillis();
				}

				if (playerXPos <= player2XPos) {
					curActionP2 = ATTACK_LEFT;
				} else if (playerXPos > player2XPos) {
					curActionP2 = ATTACK_RIGHT;
				}

			} else {

				if (playerXPos <= player2XPos) {
					curActionP2 = IDLE_LEFT;
				} else if (playerXPos > player2XPos) {
					curActionP2 = IDLE_RIGHT;
				}

			}

			return player2XPos;
		}
	}
}
