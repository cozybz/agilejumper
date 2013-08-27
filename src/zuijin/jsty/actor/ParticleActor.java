package zuijin.jsty.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ParticleActor extends Actor {
	private ParticleEffect effect;

	public ParticleActor(float x, float y) {
		effect = new ParticleEffect();
		effect.load(Gdx.files.internal("particles/a.p"),
				Gdx.files.internal("particles/"));
		setPosition(x, y);
		effect.setPosition(getX(), getY());
		effect.start();
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		effect.draw(batch,1/60f);
	}
}
