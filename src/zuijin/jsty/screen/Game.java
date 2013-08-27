package zuijin.jsty.screen;

import zuijin.jsty.utils.Assets;

public class Game extends com.badlogic.gdx.Game {
	public static AssetsScreen assetsScreen;
	public static GameScreen gameScreen;
	public static HelpScreen helpScreen;
	public static MainMenuScreen mainMenuScreen;
	public static OptionScreen optionScreen;
	public static SelectScreen selectScreen;

	@Override
	public void create() {
		assetsScreen = new AssetsScreen();
		gameScreen = new GameScreen();
		helpScreen = new HelpScreen();
		mainMenuScreen = new MainMenuScreen();
		optionScreen = new OptionScreen();
		selectScreen = new SelectScreen();
		Assets.preLoad();
		setScreen(assetsScreen);
	}
	@Override
	public void dispose() {
		super.dispose();
		gameScreen.dispose();
		Assets.dispose();
	}
	
	
}
