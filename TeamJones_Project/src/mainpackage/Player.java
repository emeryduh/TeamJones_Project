package mainpackage;

import mainpackage.Screens.Art;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {
	Sprite sprite;
	
	
	public Player(String select) {
		int width, height;
		
		
		sprite = new Sprite();
		TextureRegion tex;
		if(select=="bya"){
			tex = Art.texture_bya_game;
			width = 70;
			height = 100;
		} else {
			tex = Art.texture_ich_game;
			width = 90;
			height = 120;
		}
		sprite = new Sprite(tex);
		sprite.setSize(width, height);
		sprite.setPosition(300, 100);
	}
	
	public void render(Batch batch) {
		// must be enclosed by batch.begin and batch.end
		sprite.draw(batch);
	}

}
