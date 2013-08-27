package zuijin.jsty.screen;

import javax.microedition.khronos.opengles.GL10;

import zuijin.jsty.MainActivity;
import zuijin.jsty.utils.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class PagerScreen implements GestureListener, Screen,InputProcessor{
	TextureAtlas pager;
	SpriteBatch batch;
	//只有在第一次启动的时候需要将资源初始化
	boolean hasini;
	TextureRegion[] pages;
    MainActivity activity;
	//用来存储长和宽的值
	int min;
	int max;
	//用来标记第一张图片的位置，可以为负，无法显示的部分就不会被画出
	float position;
	float targetposition;
	//循环遍历用
	int i;
	float prex;
	float currentx;
	
	
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void hide() {
		// TODO Auto-generated method stub
		//该Screen隐藏时position置0
		position=0;
	}

	public void pause() {
		// TODO Auto-generated method stub

	}

	public void render(float arg0) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0f,0f,0f,0f);
		batch.begin();
		for(i=0;i<pages.length;i++)
			batch.draw(pages[i],position+i*min,0,min,max);
		batch.end();
		//用户放手时才进行回弹
		if(!Gdx.input.isTouched()){
			//巧妙的用一个取整数的函数来表示一张图片翻过一半的时候就应该跳转到下一张图片的效果
			targetposition=(int)((position-0.5f*min)/min)*min;
			if(targetposition<position){
				//注意不能越界，min/480f是一个因子，用于分辨率适配，让回弹速度和分辨率搭配得协调一些
				if(position-20*min/480f<targetposition)
					position=targetposition;
				else
					position-=20*min/480f;
			}
			if(targetposition>position)
				//注意不能越界
				if(position+20*min/480f>targetposition)
					position=targetposition;
				else
					position+=20*min/480f;
		}
	}

	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	public void resume() {
		// TODO Auto-generated method stub

	}
	public void show() {
		// TODO Auto-generated method stub
		max=Gdx.graphics.getHeight()>Gdx.graphics.getWidth()?Gdx.graphics.getHeight():Gdx.graphics.getWidth();
		min=Gdx.graphics.getHeight()<Gdx.graphics.getWidth()?Gdx.graphics.getHeight():Gdx.graphics.getWidth();
		if(!hasini){
			batch=new SpriteBatch();
	
			pages=new TextureRegion[8];
			pages[0]=Assets.page1;
			pages[1]=Assets.page2;
			pages[2]=Assets.page3;
			pages[3]=Assets.page4;
			pages[4]=Assets.page5;
			pages[5]=Assets.page6;
			pages[6]=Assets.page7;
			pages[7]=Assets.page8;
			hasini=true;		
		}
		//将当前Screen加入按键和手势监听队列
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(this);
		multiplexer.addProcessor(new GestureDetector(this));
		Gdx.input.setInputProcessor(multiplexer);
	}
	public boolean pinch(Vector2 arg0, Vector2 arg1, Vector2 arg2, Vector2 arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean zoom(float arg0, float arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		//返回键按下的时候返回上一界面
		if(arg0==Input.Keys.BACK)
			MainActivity.game.setScreen(new MainMenuScreen());
		return false;
	}

	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean fling(float arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean longPress(float arg0, float arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean pan(float arg0, float arg1, float arg2, float arg3) {
		// TODO Auto-generated method stub
		System.out.println(position);
		if(position<=0&&position>=-(pages.length-1)*min){
			prex=currentx;
			currentx=arg0;
			if(position+currentx-prex<=0&&position+currentx-prex>=-(pages.length-1)*min)
				position+=currentx-prex;
			else{
				if(position+currentx-prex>0)
					position=0;
				if(position+currentx-prex<-(pages.length-1)*min)
					position=-(pages.length-1)*min;
					
			}
		}
		return false;
	}

	public boolean tap(float arg0, float arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchDown(float arg0, float arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		currentx=arg0;
		return false;
	}

}
