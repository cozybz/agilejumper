package zuijin.jsty.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class BaseBoard extends Actor {
	protected Body body;
	protected TextureRegion region;
	/**
	 * @param x
	 *            (Pixels)
	 * @param y
	 *            (Pixels)
	 * @param width
	 *            (Pixels)
	 * @param height
	 *            (Pixels)
	 * @param angle
	 *            (degrees)
	 * @param friction
	 * 
	 * @param region
	 */
	public BaseBoard(float x, float y, float width, float height, float angle,
			float friction, TextureRegion region) {

		setBounds(x, y, width, height);
		setOrigin(width / 2, height / 2);
		setRotation(angle);
		this.region = region;
	}

	protected abstract void update();

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		update();
		batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
	}
}
