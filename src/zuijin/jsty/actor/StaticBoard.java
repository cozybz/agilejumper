package zuijin.jsty.actor;

import zuijin.jsty.box2d.Box2dManager;
import zuijin.jsty.box2d.UserData;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class StaticBoard extends BaseBoard {

	public StaticBoard(float x, float y, float width, float height,
			float angle, float friction, TextureRegion region) {
		super(x, y, width, height, angle, friction, region);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Box2dManager.PIXEL_TO_METER * width / 2,
				Box2dManager.PIXEL_TO_METER * height / 2, new Vector2(0, 0),
				angle * MathUtils.degreesToRadians);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.friction = friction;

		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(Box2dManager.PIXEL_TO_METER * (width / 2+x), Box2dManager.PIXEL_TO_METER * (height / 2+y));
		bodyDef.type = BodyType.StaticBody;
		body = Box2dManager.world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		body.setUserData(new UserData(2));
		shape.dispose();
	}
	
	@Override
	protected void update() {
	}
}
