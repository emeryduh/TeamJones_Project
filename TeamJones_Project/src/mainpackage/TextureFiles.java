package mainpackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TextureFiles {

	public static Texture getCharacterTexture(String strCharacter) {
		Texture chTexture = null;
		switch (strCharacter) {
		case "ichigo": {
			chTexture = new Texture(
					Gdx.files.internal("assets/sprites/backgrounds/ichigo.png"));
			break;
		}

		case "byakuya": {
			chTexture = new Texture(
					Gdx.files
							.internal("assets/sprites/backgrounds/byakuya.png"));
			break;
		}

		case "ikkaku": {
			chTexture = new Texture(
					Gdx.files.internal("assets/sprites/backgrounds/ikkaku.png"));
			break;
		}

		case "ishida": {
			chTexture = new Texture(
					Gdx.files.internal("assets/sprites/backgrounds/ishida.png"));
			break;
		}

		case "kenpachi": {
			chTexture = new Texture(
					Gdx.files
							.internal("assets/sprites/backgrounds/kenpachi.png"));
			break;
		}

		case "urahara": {
			chTexture = new Texture(
					Gdx.files
							.internal("assets/sprites/backgrounds/urahara.png"));
			break;
		}

		case "komamura": {
			chTexture = new Texture(
					Gdx.files
							.internal("assets/sprites/backgrounds/komamura.png"));
			break;
		}

		case "mayuri": {
			chTexture = new Texture(
					Gdx.files.internal("assets/sprites/backgrounds/mayuri.png"));
			break;
		}

		case "renji": {
			chTexture = new Texture(
					Gdx.files.internal("assets/sprites/backgrounds/renji.png"));
			break;
		}
		}
		return chTexture;
	}

	public static Texture getCharacterSelectionTexture(String strCharacter) {
		Texture chTexture = null;
		switch (strCharacter) {
		case "ichigo": {
			chTexture = new Texture(
					Gdx.files
							.internal("assets/sprites/backgrounds/chIchigo.png"));
			break;
		}

		case "byakuya": {
			chTexture = new Texture(
					Gdx.files
							.internal("assets/sprites/backgrounds/chByakuya.png"));
			break;
		}

		case "ikkaku": {
			chTexture = new Texture(
					Gdx.files
							.internal("assets/sprites/backgrounds/chIkkaku.png"));
			break;
		}

		case "ishida": {
			chTexture = new Texture(
					Gdx.files
							.internal("assets/sprites/backgrounds/chIshida.png"));
			break;
		}

		case "kenpachi": {
			chTexture = new Texture(
					Gdx.files
							.internal("assets/sprites/backgrounds/chKenpachi.png"));
			break;
		}

		case "urahara": {
			chTexture = new Texture(
					Gdx.files
							.internal("assets/sprites/backgrounds/chUrahara.png"));
			break;
		}

		case "komamura": {
			chTexture = new Texture(
					Gdx.files
							.internal("assets/sprites/backgrounds/chKomamura.png"));
			break;
		}

		case "mayuri": {
			chTexture = new Texture(
					Gdx.files
							.internal("assets/sprites/backgrounds/chMayuri.png"));
			break;
		}

		case "renji": {
			chTexture = new Texture(
					Gdx.files
							.internal("assets/sprites/backgrounds/chRenji.png"));
			break;
		}
		}
		return chTexture;
	}

	public static Texture getBackgroundTexture(String strTexture) {
		Texture chTexture = null;
		switch (strTexture) {
		case "ichigo": {
			chTexture = new Texture(
					Gdx.files
							.internal("assets/sprites/backgrounds/chIchigo.png"));
			break;
		}

		case "byakuya": {
			chTexture = new Texture(
					Gdx.files
							.internal("assets/sprites/backgrounds/chByakuya.png"));
			break;
		}

		case "ikkaku": {
			chTexture = new Texture(
					Gdx.files
							.internal("assets/sprites/backgrounds/chIkkaku.png"));
			break;
		}
		}
		return chTexture;
	}
}
