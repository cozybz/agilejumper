package zuijin.jsty.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;

public class Box2dManager {
	static public final float PIXEL_TO_METER=1/80f;
	static public final float METER_TO_PIXEL=80f;
	private static  Vector2 GRAVITY = new Vector2(0, -9.8f);
	private static  float BOX_STEP = 1 / 60f;
	private static  int VELOCITY_ITERATIONS = 10;
	private static  int POSITION_ITERATIONS = 10;
	public static World world; 
	public static void step(){
		world.step(BOX_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
	}
	public static void createWorld(){
		world = new World(GRAVITY, true);
	}
	public static void dispose(){
		if(world!=null)
		    world.dispose();
	}
	public static void destroyBody(Body body){
		world.destroyBody(body);
	}
	public static void destroyJoint(Joint joint){
		world.destroyJoint(joint);
	}
}
