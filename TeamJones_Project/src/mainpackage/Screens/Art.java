package mainpackage.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Art {
	public static TextureRegion texture_bya;
	public static TextureRegion texture_ich;
	public static TextureRegion texture_bya_game;
	public static TextureRegion texture_ich_game;

	public static void load() {

		// System.out.println(Gdx.files.internal("assets/spritesheets/ichigo_main.png").readString());

		Texture texture = new Texture(
				Gdx.files.internal("assets/spritesheets/byakuya_main.png"));
		texture_bya = new TextureRegion(texture, 5, 40, 224, 180);
		texture_bya_game = new TextureRegion(texture, 0, 620, 70, 100);
		System.out.println("texture 1 loaded");

		texture = new Texture(
				Gdx.files.internal("assets/spritesheets/Ichigo2_main.png"));
		texture_ich = new TextureRegion(texture, 10, 35, 190, 150);
		texture_ich_game = new TextureRegion(texture, 10, 880, 90, 120);
		
		System.out.println("texture 2 loaded");

	}
}
