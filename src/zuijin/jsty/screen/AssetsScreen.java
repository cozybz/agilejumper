package zuijin.jsty.screen;

import zuijin.jsty.MainActivity;
import zuijin.jsty.utils.Assets;

import com.badlogic.gdx.Screen;

public class AssetsScreen implements Screen {

	@Override
	public void render(float delta) {
		if(Assets.update()){
			Assets.load();
			MainActivity.game.setScreen(Game.mainMenuScreen);
		}	
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

}
