package zuijin.jsty.actor;

import zuijin.jsty.box2d.Box2dManager;
import zuijin.jsty.box2d.UserData;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class MoveBoard extends BaseBoard {
	private float dx,dy,sx,sy;
	private Vector2 v;
	public MoveBoard(float sx, float sy, float dx, float dy, float t,
			float width, float height, float angle, float friction,
			TextureRegion region) {
		super(sx, sy, width, height, angle, friction, region);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Box2dManager.PIXEL_TO_METER * width / 2,
				Box2dManager.PIXEL_TO_METER * height / 2, new Vector2(0, 0),
				angle * MathUtils.degreesToRadians);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.friction = friction;

		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(Box2dManager.PIXEL_TO_METER * (width / 2+sx), Box2dManager.PIXEL_TO_METER * (height / 2+sy));
		bodyDef.type = BodyType.KinematicBody;
		body = Box2dManager.world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		body.setUserData(new UserData(4));
		shape.dispose();
		this.dx=dx;
		this.dy=dy;
		this.sx=sx;
		this.sy=sy;
		v =new Vector2((dx-sx)/t, (dy-sy)/t);
		body.setLinearVelocity(v);
	}

	@Override
	protected void update() {
		if(Math.abs(getX()-dx)<=Math.abs(v.x) && Math.abs(getY()-dy)<=Math.abs(v.y)){
			float temp;
			temp=dx;dx=sx;sx=temp;
			temp=dy;dy=sy;sy=temp;
			v.set(-v.x,-v.y);
			body.setLinearVelocity(v);
		}
		setBounds(Box2dManager.METER_TO_PIXEL * body.getPosition().x
				- getWidth() / 2,
				Box2dManager.METER_TO_PIXEL * body.getPosition().y
						- getHeight() / 2, getWidth(), getHeight());
	}
}
