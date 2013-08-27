package zuijin.jsty.actor;

import zuijin.jsty.box2d.Box2dManager;
import zuijin.jsty.box2d.UserData;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class RotateBoard extends BaseBoard {
	public RotateBoard(float x, float y, float width, float height,
			float angularVelocity, float friction, TextureRegion region) {
		super(x, y, width, height, 0, friction, region);

		PolygonShape polygon = new PolygonShape();
		polygon.setAsBox(Box2dManager.PIXEL_TO_METER * width / 2,
				Box2dManager.PIXEL_TO_METER * height / 2, new Vector2(0, 0), 0);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = polygon;
		fixtureDef.friction = friction;
		fixtureDef.density = 6f;

		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(Box2dManager.PIXEL_TO_METER * (width / 2 + x),
				Box2dManager.PIXEL_TO_METER * (height / 2 + y));
		bodyDef.type = BodyType.DynamicBody;

		body = Box2dManager.world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		polygon.dispose();
		body.setAngularVelocity(angularVelocity);
		body.setFixedRotation(angularVelocity!=0);
		body.setUserData(new UserData(3));

		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.add(0, Box2dManager.PIXEL_TO_METER * 5);
		Body center = Box2dManager.world.createBody(bodyDef);

		CircleShape circle = new CircleShape();
		fixtureDef.shape = circle;
		fixtureDef.density = 0;
		center.createFixture(fixtureDef);
		circle.dispose();

		RevoluteJointDef jointDef = new RevoluteJointDef();
		if (angularVelocity==0) {
			jointDef.enableLimit = true;
			jointDef.upperAngle = MathUtils.PI / 12;
			jointDef.lowerAngle = -MathUtils.PI / 12;
		}
		jointDef.initialize(body, center, center.getWorldCenter());
		Box2dManager.world.createJoint(jointDef);
	}

	@Override
	protected void update() {
		setRotation(body.getAngle() * MathUtils.radiansToDegrees);
	}
}
