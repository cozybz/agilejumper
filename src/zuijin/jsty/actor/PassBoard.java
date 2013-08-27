package zuijin.jsty.actor;

import zuijin.jsty.box2d.Box2dManager;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PassBoard extends StaticBoard {

	public PassBoard(float x, float y, float width, float height, float angle,
			float friction, TextureRegion region) {
		super(x, y, width, height, angle, friction, region);
	}
	public void pass(){
		body.setActive(false);
		Box2dManager.world.destroyBody(body);
	}
}
