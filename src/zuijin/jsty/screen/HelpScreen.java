package zuijin.jsty.screen;

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
import zuijin.jsty.utils.Assets;

/**
 * @author xiaotian
 *
 */
public class HelpScreen implements Screen,InputProcessor {
	OrthographicCamera guiCam;
	SpriteBatch batcher;
	TextureRegion titleRegion;
	Rectangle soundBounds;
	Rectangle playBounds;
	Rectangle highscoresBounds;
	Rectangle helpBounds;
	Vector3 touchPoint;
	
	public HelpScreen () {
		guiCam = new OrthographicCamera(480, 800);
		guiCam.position.set(480 / 2, 800 / 2, 0);
		batcher = new SpriteBatch();
	}
	public void draw(){
		GLCommon gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);

		batcher.disableBlending();
		batcher.begin();
		batcher.draw(Assets.helpScrrenRegion, 0, 0, 480, 800);
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
			MainActivity.game.setScreen(new MainMenuScreen());
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
