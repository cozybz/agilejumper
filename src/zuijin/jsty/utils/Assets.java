package zuijin.jsty.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	private static AssetManager assetManager;
	public static TextureRegion commonBoard;
	public static TextureRegion ball;
	public static TextureRegion food;
	public static TextureRegion background1;
	public static TextureRegion background2;
	public static TextureRegion background3;

	public static TextureRegion page1;
	public static TextureRegion page2;
	public static TextureRegion page3;
	public static TextureRegion page4;
	public static TextureRegion page5;
	public static TextureRegion page6;
	public static TextureRegion page7;
	public static TextureRegion page8;
	
	public static Music backgroundMusic;
	public static Sound collideSound;
	public static Sound passSound;
	public static Sound jumpSound;
	public static Sound eatSound;

	private static String objectPos = "texture/object.atlas";
	private static String texturesPos = "texture/textures.pack";
	private static String pagePos = "page/page.atlas";
	private static String backgroundMusicPos = "sound/background.mp3";
	private static String passSoundPos = "sound/passsound.mp3";
	private static String jumpSoundPos = "sound/jumpsound.mp3";
	private static String eatSoundPos = "sound/eatsound.wav";
	
	//ƒæ∞Â
	public static TextureRegion board1;
	public static TextureRegion board2;
	public static TextureRegion board3;
	public static TextureRegion board4;
	public static TextureRegion board5;
	public static TextureRegion board6;
	public static TextureRegion board7;
	public static TextureRegion moveBoard;
	public static TextureRegion iron1_1;
	public static TextureRegion iron1_2;
	public static TextureRegion iron2_1;
	public static TextureRegion iron2_2;
	public static TextureRegion iron3_1;
	public static TextureRegion iron3_2;
	public static TextureRegion iron4_1;
	public static TextureRegion iron4_2;
	public static TextureRegion nail;
	public static TextureRegion ufo;
	
	public static TextureRegion glad;
	public static TextureRegion dialog1;
	public static TextureRegion dialog2;
	
	public static TextureRegion bee1;
	public static TextureRegion bee2;
	public static TextureRegion spiderWeb1;
	public static TextureRegion spiderWeb2;
	
	
	public static TextureRegion backgroundRegion;
	public static TextureRegion titleRegion;
	public static TextureRegion helpScrrenRegion;
	public static TextureRegion selectScrren_background;
	public static TextureRegion selectScrren_redBall;
	public static TextureRegion selectScrren_lock;
	public static BitmapFont numberFont;
	public static BitmapFont timeFont;
	public static TextureRegion acountScreen_background;
	public static Texture sound_on;
	public static Texture sound_off;
	public static TextureRegion optionScreen;

	public static void preLoad() {
		assetManager = new AssetManager();
		assetManager.load(texturesPos, TextureAtlas.class);
		assetManager.load(objectPos,TextureAtlas.class);
		assetManager.load(pagePos,TextureAtlas.class);
		assetManager.load(backgroundMusicPos, Music.class);
		assetManager.load(passSoundPos, Sound.class);
		assetManager.load(jumpSoundPos, Sound.class);
		assetManager.load(eatSoundPos, Sound.class);
	}
	public static boolean update() {
		return assetManager.update();
	}
	public static void load(){
		TextureAtlas atlas = assetManager.get(texturesPos,TextureAtlas.class);
		TextureAtlas ObjectAtlas = assetManager.get(objectPos,TextureAtlas.class);
		TextureAtlas pageAtlas = assetManager.get(pagePos,TextureAtlas.class);
		commonBoard = atlas.findRegion("commonBoard");
		ball = atlas.findRegion("ball1");
		food = atlas.findRegion("food");
		background1 = atlas.findRegion("background1");
		background2 = atlas.findRegion("background2");
		background3 = atlas.findRegion("background3");
		
		
		
		backgroundMusic = assetManager.get(backgroundMusicPos, Music.class);
		jumpSound = assetManager.get(jumpSoundPos, Sound.class);
		passSound = assetManager.get(passSoundPos, Sound.class);
		eatSound = assetManager.get(eatSoundPos, Sound.class);
		
		
		
		
		TextureAtlas mainAtlas = new TextureAtlas(Gdx.files.internal("mainScreen/screen1.atlas"),Gdx.files.internal("mainScreen"));
		backgroundRegion = mainAtlas.findRegion("background");
        titleRegion=mainAtlas.findRegion("title");
 
        sound_on=new Texture(Gdx.files.internal("mainScreen/sound1.png"));
        sound_off=new Texture(Gdx.files.internal("mainScreen/sound2.png"));
        
        helpScrrenRegion=new TextureRegion(new Texture(Gdx.files.internal("helpScreen/screen2.png")),0,0,480,800);
        TextureAtlas selectScreen_atlas = new TextureAtlas(Gdx.files.internal("selectScreen/selectScreen.atlas"),Gdx.files.internal("selectScreen"));
        selectScrren_background=selectScreen_atlas.findRegion("selectBackground");
        selectScrren_lock=selectScreen_atlas.findRegion("lock");
        selectScrren_redBall=selectScreen_atlas.findRegion("redBall");       
        numberFont = new BitmapFont(Gdx.files.internal("numberFont/number.fnt"), Gdx.files.internal("numberFont/number.png"), false);
        timeFont = new BitmapFont(Gdx.files.internal("numberFont/time.fnt"), Gdx.files.internal("numberFont/time.png"), false);
        optionScreen=new TextureRegion(new Texture("optionScreen/optionScreen.png"),0,0,480,800);
        acountScreen_background=new TextureRegion(new Texture(Gdx.files.internal("rankScreen/rank.png")),0,0,480,800);
	
        //objectÕº∆¨º”‘ÿ
        board1=ObjectAtlas.findRegion("board1");
        board2=ObjectAtlas.findRegion("board2");
        board3=ObjectAtlas.findRegion("board3");
        board4=ObjectAtlas.findRegion("board4");
        board5=ObjectAtlas.findRegion("board5");
        board6=ObjectAtlas.findRegion("board6");
        board7=ObjectAtlas.findRegion("board7");
        moveBoard=ObjectAtlas.findRegion("moveBoard");
        ufo=ObjectAtlas.findRegion("ufo");
        glad=ObjectAtlas.findRegion("glad");
        
        iron1_1=ObjectAtlas.findRegion("iron11");
        iron1_2=ObjectAtlas.findRegion("iron12");
        iron2_1=ObjectAtlas.findRegion("iron21");
        iron2_2=ObjectAtlas.findRegion("iron22");
        iron3_1=ObjectAtlas.findRegion("iron31");
        iron3_2=ObjectAtlas.findRegion("iron32");
        iron4_1=ObjectAtlas.findRegion("iron41");
        iron4_2=ObjectAtlas.findRegion("iron42"); 
        nail=ObjectAtlas.findRegion("nail");
        //dialog
        TextureAtlas dialogAtlas = new TextureAtlas(Gdx.files.internal("dialog/dialog.atlas"),Gdx.files.internal("dialog"));
        dialog1 = dialogAtlas.findRegion("dialog1");
        dialog2=dialogAtlas.findRegion("dialog2");

        bee1=ObjectAtlas.findRegion("bee1");
        bee2=ObjectAtlas.findRegion("bee2");
        spiderWeb1=ObjectAtlas.findRegion("sprideWeb1");
        spiderWeb2=ObjectAtlas.findRegion("sprideWeb2");
        //page
        page1=pageAtlas.findRegion("page1");
        page2=pageAtlas.findRegion("page2");
        page3=pageAtlas.findRegion("page3");
        page4=pageAtlas.findRegion("page4");
        page5=pageAtlas.findRegion("page5");
        page6=pageAtlas.findRegion("page6");
        page7=pageAtlas.findRegion("page7");
        page8=pageAtlas.findRegion("page8");
        
        if(page1==null)
        	System.out.println("page1 “Ï≥£");
        System.out.println("“ª«–OK");
	}
	public static void dispose() {
		assetManager.clear();
		assetManager.dispose();
	}
}
