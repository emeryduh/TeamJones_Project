package mainpackage;

/**
 * This class is used to hold all information about a user's game configuration.
 * This class is responsible to load in from a *.config file user settings and be able
 * to provide information to the various game components when necessary. 
 */
public class UserConfig {
	// These values will hold the user's sound settings
	private static float masterVolume, bgmVolume, sfxVolume;
	
	
	public static void saveSettings() {
		// Save to a file the user settings
	}
	
	public static void loadSettings() {
		// Load from a file the user settings
		// For now default values will be provided
		masterVolume = 1.0f;
		bgmVolume = 0.85f;
		sfxVolume = 0.6f;
	}
	
	/*
	 * Getters and Setters
	 */
	
	/**
	 * Sets the master volume.
	 * @param volume
	 */
	public static void setMasterVolume(float volume) {
		masterVolume = volume;
	}
	
	/**
	 * Returns the master volume.
	 * @return
	 */
	public static float getMasterVolume() {
		return masterVolume;
	}
	
	/**
	 * Sets the Background music volume.
	 * @param volume
	 */
	public static void setBGMVolume(float volume) {
		bgmVolume = volume;
	}
	
	/**
	 * Returns the Background music volume.
	 * @param trueVolume
	 * @return
	 */
	public static float getBGMVolume(boolean trueVolume) {
		if(trueVolume == true) {
			return bgmVolume;
		} else {
			return bgmVolume * masterVolume;
		}
	}
	
	/**
	 * Sets the Sound effects volume.
	 * @param volume
	 */
	public static void setSFXVolume(float volume) {
		sfxVolume = volume;
	}
	
	/**
	 * Returns the Sound effects volume.
	 * @param trueVolume
	 * @return
	 */
	public static float getSFXVolume(boolean trueVolume) {
		if(trueVolume == true) {
			return sfxVolume;
		} else {
			return sfxVolume * masterVolume;
		}
	}
}
