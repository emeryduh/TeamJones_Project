package mainpackage;

import mainpackage.Screens.OptionScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundFiles {

	private Sound sound;
	
	
	
	// play in game fight sounds
	public void playSound(Entity e, String soundName) {
		switch (soundName) {
		// play punch sfx
		case "punch":
			soundName = "Attack01";
			sound = Gdx.audio.newSound(Gdx.files.internal("assets/audioFiles/"
					+ e.getName() + "combat/" + soundName + ".wav"));
			sound.play();
			break;
		// play kick sfx
		case "kick":
			soundName = "Attack02";
			sound = Gdx.audio.newSound(Gdx.files.internal("assets/audioFiles/"
					+ e.getName() + "combat/" + soundName + ".wav"));
			sound.play();
			break;
		// play attack3 sfx
		case "Attack3":
			soundName = "Attack03";
			sound = Gdx.audio.newSound(Gdx.files.internal("assets/audioFiles/"
					+ e.getName() + "combat/" + soundName + ".wav"));
			sound.play();
			break;
		// play hurt sfx
		case "Hurt":
			soundName = "Hurt";
			sound = Gdx.audio.newSound(Gdx.files.internal("assets/audioFiles/"
					+ e.getName() + "combat/" + soundName + ".wav"));
			sound.play();
			break;

		}
	}

	// play menu sounds
	public void playSound(String soundName) {
		
		
		switch (soundName) {
		// hitting back on a menu sound
		case "menuBack":
			sound = Gdx.audio.newSound(Gdx.files
					.internal("assets/audioFiles/menuSounds/" + soundName
							+ ".wav"));
			sound.play();
			
			//sound.setVolume(0, this.game.);
			break;
		// selecting an item sound
		case "menuSelect":
			sound = Gdx.audio.newSound(Gdx.files
					.internal("assets/audioFiles/menuSounds/" + soundName
							+ ".wav"));
			sound.play();
			break;
		// traversing the menu sound
		case "menuTraverse":
			sound = Gdx.audio.newSound(Gdx.files
					.internal("assets/audioFiles/menuSounds/" + soundName
							+ ".wav"));
			sound.play();
			break;
		}
	}

}
