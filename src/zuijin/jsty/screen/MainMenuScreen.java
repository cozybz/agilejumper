package zuijin.jsty.screen;

import java.util.ArrayList;

import zuijin.jsty.MainActivity;
import zuijin.jsty.RankActivity;
import zuijin.jsty.dao.GameData;
import zuijin.jsty.utils.Assets;
import zuijin.jsty.utils.OverlapTester;


import android.content.Intent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * @author xiaotian
 * 
 */
public class MainMenuScreen implements Screen, InputProcessor {

	OrthographicCamera guiCam;
	SpriteBatch batcher;
	TextureRegion titleRegion;
	int s = 0;
	Rectangle rankBounds;
	Rectangle playBounds;
	Rectangle selectBounds;
	Rectangle shareBounds;
	Rectangle helpBounds;
	Rectangle optionBounds;
	Rectangle soundBounds;
	Vector3 touchPoint;
	int soundOn;

	ParticleEffect particle; 
	ParticleEffect tem;
	ParticleEffectPool particlepool;
	ArrayList<ParticleEffect> particlelist;
	public MainMenuScreen() {
		guiCam = new OrthographicCamera(480, 800);
		guiCam.position.set(480 / 2, 800 / 2, 0);
		batcher = new SpriteBatch();
		playBounds = new Rectangle(60, 495, 350, 73);
		selectBounds = new Rectangle(60, 421, 350, 73);
		rankBounds = new Rectangle(60, 347, 350, 73);
		shareBounds = new Rectangle(60, 273, 350, 73);
		helpBounds = new Rectangle(60, 190, 350, 73);
		optionBounds = new Rectangle(60, 135, 350, 73);
		soundBounds = new Rectangle(380, 20, 64, 64);
		touchPoint = new Vector3();
		soundOn = GameData.getVolume();
	}

	public void draw() {
		GLCommon gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);

		batcher.disableBlending();
		batcher.begin();
		batcher.draw(Assets.backgroundRegion, 0, 0, 480, 800);
		batcher.end();

		batcher.enableBlending();
		batcher.begin();
		titleRegion = new TextureRegion(Assets.titleRegion, 0, 30 - s, Assets.titleRegion.getRegionWidth(),
				105 + s);
		batcher.draw(titleRegion, 105, 625 - s);
		if (s < 40) {
			s++;
		}
		if (soundOn == 1) {
			batcher.draw(Assets.sound_on, 385, 25, 64, 64);
		} else {
			batcher.draw(Assets.sound_off, 385, 23, 64, 64);
		}
		batcher.end();
	}

	public void dispose() {

	}

	public void hide() {
		Gdx.input.setInputProcessor(null);
		Gdx.input.setCatchBackKey(false);

	}

	public void pause() {

	}

	public void render(float delta) {

		// update(delta);
		draw();
		if(true){
    		if(Gdx.input.isTouched()){
    			//当此触摸点与上一触摸点距离大于一定值的时候触发新的粒子系统，由此减小系统负担
    			tem=particlepool.obtain();
    			tem.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
    			particlelist.add(tem);
    		}
    		batcher.begin();
            for(int i=0;i<particlelist.size();i++){
    		particlelist.get(i).draw(batcher, Gdx.graphics.getDeltaTime());
            }
            batcher.end();
            //清除已经播放完成的粒子系统 
            ParticleEffect temparticle;
            for(int i=0;i<particlelist.size();i++){
            	temparticle=particlelist.get(i);
        		if(temparticle.isComplete()){
        			particlelist.remove(i);
        		}
            }
    	}
	}

	public void resize(int arg0, int arg1) {

	}

	public void resume() {

	}

	public void show() {
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setInputProcessor(this);
		//初始化粒子变量
    	particle = new ParticleEffect(); 
		particle.load(Gdx.files.internal("particle.p"), Gdx.files.internal(""));
		particlepool=new ParticleEffectPool(particle, 5, 10);
		particlelist=new ArrayList<ParticleEffect>();		
		InputMultiplexer multiplexer=new InputMultiplexer();
		multiplexer.addProcessor(this);
		Gdx.input.setInputProcessor(multiplexer);
	}

	public boolean keyDown(int keycode) {

		if (keycode == Keys.BACK) {
			Assets.dispose();
			Gdx.app.exit();
		}
		return false;
	}

	public boolean keyUp(int keycode) {

		return false;
	}

	public boolean keyTyped(char character) {

		return false;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		guiCam.unproject(touchPoint.set(screenX, screenY, 0));

		if (OverlapTester.pointInRectangle(playBounds, touchPoint.x,
				touchPoint.y)) {
			Game.gameScreen.setOff(1);
			MainActivity.game.setScreen(Game.gameScreen);
		}
		if (OverlapTester.pointInRectangle(selectBounds, touchPoint.x,
				touchPoint.y)) {
			MainActivity.game.setScreen(Game.selectScreen);
		}
		if (OverlapTester.pointInRectangle(shareBounds, touchPoint.x,
				touchPoint.y)) {
			Intent intent=new Intent(Intent.ACTION_SEND);  
	        intent.setType("image/*");  
	        intent.putExtra(Intent.EXTRA_SUBJECT, "Share");  
	        intent.putExtra(Intent.EXTRA_TEXT, "极速跳跃，高质体验，快来玩玩吧，游戏下载地址：http://apps.ztems.com/newdetails.html?appCode=402825aa3abf32c1013ae8c85a7c0cef&curnavistop=ITNAV2_P8a885ffc2b46d1f6012b46dbb3880007");  
	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
	        MainActivity.context.startActivity(Intent.createChooser(intent,"分享"));  
		}
		if (OverlapTester.pointInRectangle(rankBounds, touchPoint.x,
				touchPoint.y)) {
			 Intent intent=new Intent(MainActivity.context,RankActivity.class);
			 MainActivity.context.startActivity(intent);
		}
		if (OverlapTester.pointInRectangle(helpBounds, touchPoint.x,
				touchPoint.y)) {			
			
			MainActivity.game.setScreen(new PagerScreen());
			
		}
		if (OverlapTester.pointInRectangle(optionBounds, touchPoint.x,
				touchPoint.y)) {
			MainActivity.game.setScreen(Game.optionScreen);
		}
		if (OverlapTester.pointInRectangle(soundBounds, touchPoint.x,
				touchPoint.y)) {
			soundOn = soundOn == 1 ? 0 : 1;		
			GameData.saveVolume(soundOn);
		}
		return false;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer) {

		return false;
	}

	public boolean mouseMoved(int screenX, int screenY) {

		return false;
	}

	public boolean scrolled(int amount) {

		return false;
	}

}
