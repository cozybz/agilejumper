package zuijin.jsty.actor;

import zuijin.jsty.box2d.Box2dManager;
import zuijin.jsty.box2d.UserData;
import zuijin.jsty.dao.GameData;
import zuijin.jsty.utils.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Ball extends Actor {
	private Body body;
	private TextureRegion region;

	public Ball(float x, float y, float radius, float density, float friction,
			float restitution, TextureRegion region) {
		setBounds(x, y, radius * 2, radius * 2);
		setOrigin(radius, radius);
		this.region = region;
		
		CircleShape shape = new CircleShape();
		shape.setRadius(radius*Box2dManager.PIXEL_TO_METER);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = density;
		fixtureDef.restitution = restitution;
		fixtureDef.friction = friction;
		fixtureDef.shape = shape;
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(Box2dManager.PIXEL_TO_METER * (radius +x), Box2dManager.PIXEL_TO_METER * (radius+y));
		bodyDef.type=BodyType.DynamicBody;
		
		body = Box2dManager.world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		body.setUserData(new UserData(0));
		shape.dispose();
	}

	public void jump() {
		if (((UserData)(body.getUserData())).jumpable) {
			body.applyLinearImpulse(0, 0.7f, body.getPosition().x,
					body.getPosition().y);
			if(GameData.getVolume()==1){
				Assets.jumpSound.play();
			}
		}
	
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		update();
		batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
	}

	private void update() {
		setBounds(Box2dManager.METER_TO_PIXEL * body.getPosition().x
				- getWidth() / 2,
				Box2dManager.METER_TO_PIXEL * body.getPosition().y
						- getHeight() / 2, getWidth(), getHeight());
		setRotation(body.getAngle() * MathUtils.radiansToDegrees);
	}
	
	public void applyAccForce() {
		if(body.getLinearVelocity().y == 0)
			body.applyTorque(Gdx.input.getAccelerometerX() * 0.02f);
		else{
			body.applyForceToCenter(new Vector2(-Gdx.input.getAccelerometerX() * 0.08f, 0));
		}
	}
}
