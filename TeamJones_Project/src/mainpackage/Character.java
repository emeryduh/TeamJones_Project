package mainpackage;

public enum Character {
	ICHIGO(0), BYAKUYA(1),IKKAKU(2), ISHIDA(3), KENPACHI(4), URAHARA(5),KOMAMURA(6), MAYURI(7), RENJI(8);
	// to get the index
	private int chIndex;
	
	private Character(int code)
	{
		chIndex = code;
	}
	// get the index for character
	public int getChracterIndex()
	{
		return chIndex;
	}
}
