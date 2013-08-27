package zuijin.jsty.screen;

import java.util.HashMap;
import java.util.Iterator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import zuijin.jsty.MainActivity;
import zuijin.jsty.dao.GameData;
import zuijin.jsty.utils.Assets;
import zuijin.jsty.utils.OverlapTester;

/**
 * @author xiaotian
 * 
 */
public class SelectScreen implements Screen, InputProcessor {

	OrthographicCamera guiCam;
	SpriteBatch batcher;
	TextureRegion titleRegion;
	HashMap<Rectangle, Integer> bounds;
	Rectangle soundBounds;
	Rectangle playBounds;
	Rectangle highscoresBounds;
	Rectangle helpBounds;
	Vector3 touchPoint;
	private int off;

	public SelectScreen() {
		off = 1;
		guiCam = new OrthographicCamera(480, 800);
		guiCam.position.set(480 / 2, 800 / 2, 0);
		batcher = new SpriteBatch();
		bounds = new HashMap<Rectangle, Integer>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				bounds.put(new Rectangle(70 + j * 90 + j * 30, 550 - i * 60 - i
						* 90, 60, 60), i * 3 + j + 1);
			}
		}
		touchPoint = new Vector3();
	}
	public void draw() {
		GLCommon gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);

		batcher.disableBlending();
		batcher.begin();
		batcher.draw(Assets.selectScrren_background, 0, 0, 480, 800);
		batcher.end();
		batcher.enableBlending();
		batcher.begin();
		// 12为通过最大关数，需修改
		
		off = GameData.getOff();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				int num = i * 3 + j + 1;
				if (num <= off) {
					batcher.draw(Assets.selectScrren_redBall, 70 + j * 90 + j
							* 30, 550 - i * 60 - i * 90);
					if (num < 10)
						Assets.numberFont.draw(batcher, num + "", 70 + j * 90
								+ j * 30 + 30, 550 - i * 60 - i * 90 + 68);
					else
						Assets.numberFont.draw(batcher, 3 * i + j + 1 + "", 70
								+ j * 90 + j * 30 + 16, 550 - i * 60 - i * 90
								+ 45);
				} else {
					batcher.draw(Assets.selectScrren_lock,
							70 + j * 90 + j * 30, 550 - i * 60 - i * 90);
				}
			}
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

	public void render(float arg0) {
		
		// update();
		draw();
	}

	public void resize(int arg0, int arg1) {
		

	}

	public void resume() {
		

	}

	public void show() {
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setInputProcessor(this);

	}

	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK) {
			MainActivity.game.setScreen(Game.mainMenuScreen);
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

		guiCam.unproject(touchPoint.set(screenX, screenY, 0));
		Iterator<Rectangle> tempBounds = bounds.keySet().iterator();
		while (tempBounds.hasNext()) {
			Rectangle rectangle = tempBounds.next();
			if (OverlapTester.pointInRectangle(rectangle, touchPoint.x,
					touchPoint.y)) {
				if (bounds.get(rectangle) <= off){
					Game.gameScreen.setOff(bounds.get(rectangle));
					MainActivity.game.setScreen(Game.gameScreen);
				}
					
			}
		}
		return false;
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
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
