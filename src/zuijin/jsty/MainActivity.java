package zuijin.jsty;

import zuijin.jsty.dao.GameData;
import zuijin.jsty.screen.Game;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class MainActivity extends AndroidApplication{
	public static Game game;
	public static Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//��ȥ��������Ӧ�ó�������֣�  
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //��ȥ״̬������(��ص�ͼ���һ�����β���)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		context=this;
		GameData.initDataBaseHelper(this);
		game = new Game();
		initialize(game, false);
	}
}
