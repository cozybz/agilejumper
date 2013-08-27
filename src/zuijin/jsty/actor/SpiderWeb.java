package zuijin.jsty.actor;

import zuijin.jsty.box2d.Box2dManager;
import zuijin.jsty.box2d.UserData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SpiderWeb extends Actor {
	private Body body;
	private TextureRegion region;

	public SpiderWeb(float x1, float y1, float x2, float y2,
			TextureRegion region) {

		float x = x1 < x2 ? x1 : x2;
		float y = y1 < y2 ? y1 : y2;
		float width = Math.abs(x1 - x2);
		float height = Math.abs(y1 - y2);

		setBounds(x, y, width, height);
		this.region = region;
		PolygonShape ps = new PolygonShape();
		Vector2[] vertices = new Vector2[2];
		
		vertices[0] = new Vector2((x1-x) * Box2dManager.PIXEL_TO_METER, (y1-y)
				* Box2dManager.PIXEL_TO_METER);
		vertices[1] = new Vector2((x2-x) * Box2dManager.PIXEL_TO_METER, (y2-y)
				* Box2dManager.PIXEL_TO_METER);
		
		ps.set(vertices);

		FixtureDef fd = new FixtureDef();
		fd.shape = ps;

		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;
		bd.position.set(x * Box2dManager.PIXEL_TO_METER, y
				* Box2dManager.PIXEL_TO_METER);
		body = Box2dManager.world.createBody(bd);
		body.createFixture(fd);
		body.setUserData(new UserData(5));
		ps.dispose();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(region, getX(), getY(), 0, 0, getWidth(), getHeight(), 1, 1 ,0);
	}
}
