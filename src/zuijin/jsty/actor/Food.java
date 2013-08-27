package zuijin.jsty.actor;

import zuijin.jsty.box2d.Box2dManager;
import zuijin.jsty.box2d.UserData;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Food extends Actor {
	private Body body;
	private TextureRegion region;
	public Food(float x, float y, float radius, TextureRegion region) {
		setBounds(x, y, radius * 2, radius * 2);
		setOrigin(radius, radius);
		this.region = region;
		CircleShape shape = new CircleShape();
		shape.setRadius(radius*Box2dManager.PIXEL_TO_METER);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(Box2dManager.PIXEL_TO_METER * (radius +x), Box2dManager.PIXEL_TO_METER * (radius+y));
		bodyDef.type=BodyType.StaticBody;
		
		body = Box2dManager.world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		body.setUserData(new UserData(1));
		shape.dispose();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
		
		if(!body.isActive()){
			this.remove();
			Box2dManager.destroyBody(body);
		}
	}
}
