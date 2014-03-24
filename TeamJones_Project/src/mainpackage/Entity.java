/**
 * 
 */
package mainpackage;

public class Entity {
	// store sprite for a player
	public SpriteClass[] spriteSheet;
	// store the selected character
	public Character character;
	// store the character name
	public String charName;
	
	public Entity()
	{
		charName = character.toString();
	}
	
	// get selected player name
	public String getName()
	{
		return charName;
	}	 
}
