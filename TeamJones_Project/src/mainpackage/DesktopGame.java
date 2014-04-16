package mainpackage;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopGame {
	
	public DesktopGame() {
		
	}

	public static void main(String[] args) {
		LwjglApplicationConfiguration windowConfig = new LwjglApplicationConfiguration();
		windowConfig.width = 800;
		windowConfig.height = 600;
		windowConfig.title = "Bleach Fighter" + Game.VERSION;
		windowConfig.resizable = false;
		windowConfig.addIcon("assets/gui/windowicon.png/",
				Files.FileType.Internal);
		UserConfig.loadSettings();
		new LwjglApplication(new Game(), windowConfig);
	}
}
