package zuijin.jsty.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {
	private static final String DATABASE_NAME = "data.db";
	private static final int DATABASE_VERSION = 128;

	public SQLiteOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("create table off(off int primary key,"
				+ "width float not null," + "height float not null,"
				+ "bgregion int not null," + "passx float not null,"
				+ "passy float not null," + "passwidth float not null,"
				+ "passheight float not null," + "region int not null)");

		db.execSQL("create table rank(id int not null,name char(50) not null, time char(50) not null)");

		db.execSQL("create table spiderweb(x1 float not null,"
				+ "y1 float not null," + "x2 float not null,"
				+ "y2 float not null," + "region int not null,"
				+ "off int references off(off))");

		db.execSQL("create table static_board(" + "x float not null,"
				+ "y float not null," + "width float not null,"
				+ "height float not null," + "angle float not null,"
				+ "friction float not null," + "region int not null,"
				+ "off int references off(off))");

		db.execSQL("create table move_board(sx float not null,"
				+ "sy float not null," + "dx float not null,"
				+ "dy float not null," + "t float not null,"
				+ "width float not null," + "height float not null,"
				+ "angle float not null," + "friction float not null,"
				+ "region int not null," + "off int references off(off))");

		db.execSQL("create table move_bee(sx float not null,"
				+ "sy float not null," + "dx float not null,"
				+ "dy float not null," + "t float not null,"
				+ "width float not null," + "height float not null,"
				+ "angle float not null," + "off int references off(off))");

		db.execSQL("create table rotate_board(x  float not null,"
				+ "y float not null," + "width float not null,"
				+ "height float not null," + "angularvelocity float not null,"
				+ "friction float not null," + "region int not null,"
				+ "off int references off(off))");

		db.execSQL("create table food(x float not null," + "y float not null,"
				+ "radius float not null," + "region int not null,"
				+ "off int references off(off))");

		db.execSQL("create table ball(x  float not null," + "y float not null,"
				+ "radius float not null," + "density float not null,"
				+ "friction float not null," + "restitution float not null,"
				+ "region int not null," + "off int references off(off))");

		db.execSQL("create table config(item int primary key," + // 1 volume
				"value float not null)"); // 2 maxOff

		ContentValues cv = new ContentValues();
		cv.put("item", 1);
		cv.put("value", 1);
		db.insert("config", null, cv);
		cv.clear();
		cv.put("item", 2);
		cv.put("value", 3);
		db.insert("config", null, cv);
		cv.clear();

		cv.put("off", 1);
		cv.put("width", 700);
		cv.put("height", 1000f);
		cv.put("bgregion", 1);
		cv.put("passx", 190f);
		cv.put("passy", 960f);
		cv.put("passwidth", 100f);
		cv.put("passheight", 57f);
		cv.put("region", 0);
		db.insert("off", null, cv);
		cv.clear();

		cv.put("x", 30f);
		cv.put("y", 30f);
		cv.put("radius", 20f);
		cv.put("density", 0.5f);
		cv.put("friction", 0.5f);
		cv.put("restitution", 0.6f);
		cv.put("region", 0);
		cv.put("off", 1);
		db.insert("ball", null, cv);
		cv.clear();

		cv.put("x", 0f);
		cv.put("y", 0f);
		cv.put("width", 700f);
		cv.put("height", 25f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 5);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 0f);
		cv.put("y", 0f);
		cv.put("width", 25f);
		cv.put("height", 1000f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 6);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 0f);
		cv.put("y", 975f);
		cv.put("width", 200f);
		cv.put("height", 25f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 5);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 280f);
		cv.put("y", 975f);
		cv.put("width", 420f);
		cv.put("height", 25f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 5);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 675f);
		cv.put("y", 0f);
		cv.put("width", 25f);
		cv.put("height", 1000f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 6);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		// 铁片
		cv.put("x", 0f);
		cv.put("y", 0f);
		cv.put("width", 22f);
		cv.put("height", 64f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 16);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 0f);
		cv.put("y", 0f);
		cv.put("width", 64f);
		cv.put("height", 22f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 15);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 676f);
		cv.put("y", 0f);
		cv.put("width", 22f);
		cv.put("height", 64f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 18);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 636f);
		cv.put("y", 0f);
		cv.put("width", 64f);
		cv.put("height", 22f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 17);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 0f);
		cv.put("y", 936f);
		cv.put("width", 22f);
		cv.put("height", 64f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 12);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 0f);
		cv.put("y", 976f);
		cv.put("width", 64f);
		cv.put("height", 22f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 11);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 676f);
		cv.put("y", 936f);
		cv.put("width", 22f);
		cv.put("height", 64f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 14);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 636f);
		cv.put("y", 976f);
		cv.put("width", 64f);
		cv.put("height", 22f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 13);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 24f);
		cv.put("y", 150f);
		cv.put("width", 200f);
		cv.put("height", 25f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 1);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 16f);
		cv.put("y", 153f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 200f);
		cv.put("y", 280f);
		cv.put("width", 450f);
		cv.put("height", 25f);
		cv.put("angularvelocity", 0f); // 转速
		cv.put("friction", 0.5f);
		cv.put("region", 0);
		cv.put("off", 1);
		db.insert("rotate_board", null, cv);
		cv.clear();

		cv.put("sx", 440f);
		cv.put("sy", 350f);
		cv.put("dx", 250f);
		cv.put("dy", 680f);
		cv.put("t", 600f);
		cv.put("width", 236f);
		cv.put("height", 25f);
		cv.put("angle", 0f);
		cv.put("friction", 1f);
		cv.put("region", 2);
		cv.put("off", 1);
		db.insert("move_board", null, cv);
		cv.clear();
		cv.put("x", 666f);
		cv.put("y", 403f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.clear();
		cv.put("x", 24f);
		cv.put("y", 580f);
		cv.put("width", 200f);
		cv.put("height", 25f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 1);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 16f);
		cv.put("y", 583f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.clear();
		cv.put("x", 500f);
		cv.put("y", 680f);
		cv.put("width", 175f);
		cv.put("height", 25f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 1);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 666f);
		cv.put("y", 683f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.clear();
		cv.put("x", 24f);
		cv.put("y", 820f);
		cv.put("width", 350f);
		cv.put("height", 25f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 1);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 16f);
		cv.put("y", 823f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 1);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 50f);
		cv.put("y", 250f);
		cv.put("radius", 20f);
		cv.put("region", 0);
		cv.put("off", 1);
		db.insert("food", null, cv);

		cv.clear();
		cv.put("x", 100f);
		cv.put("y", 680f);
		cv.put("radius", 20f);
		cv.put("region", 0);
		cv.put("off", 1);
		db.insert("food", null, cv);

		cv.clear();
		cv.put("x", 600f);
		cv.put("y", 800f);
		cv.put("radius", 20f);
		cv.put("region", 0);
		cv.put("off", 1);
		db.insert("food", null, cv);

		cv.clear();
		cv.put("sx", 200f);
		cv.put("sy", 900f);
		cv.put("dx", 600f);
		cv.put("dy", 900f);
		cv.put("t", 600f);
		cv.put("width", 35f);
		cv.put("height", 30f);
		cv.put("angle", 0f);
		cv.put("off", 1);
		db.insert("move_bee", null, cv);

		cv.clear();
		cv.put("x1", 25f);
		cv.put("y1", 720f);
		cv.put("x2", 125f);
		cv.put("y2", 820f);
		cv.put("region", 1);
		cv.put("off", 1);
		db.insert("spiderweb", null, cv);
		cv.clear();

// 第 三关

		cv.put("off", 3);
		cv.put("width", 900f);
		cv.put("height", 1100f);
		cv.put("bgregion", 3);
		cv.put("passx", 240f);
		cv.put("passy", 1060f);
		cv.put("passwidth", 100f);
		cv.put("passheight", 57f);
		cv.put("region", 0);
		db.insert("off", null, cv);
		cv.clear();

		cv.put("x", 200f);
		cv.put("y", 250f);
		cv.put("radius", 20f);
		cv.put("region", 0);
		cv.put("off", 3);
		db.insert("food", null, cv);
		cv.clear();
		// 1.四周墙壁

		cv.put("x", 0f);
		cv.put("y", 0f);
		cv.put("width", 900f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 5);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 0f);
		cv.put("y", 0f);
		cv.put("width", 24f);
		cv.put("height", 1100f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 7);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 0f);
		cv.put("y", 1076f);
		cv.put("width", 250f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 2);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 330f);
		cv.put("y", 1076f);
		cv.put("width", 570f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 5);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 876f);
		cv.put("y", 0f);
		cv.put("width", 24f);
		cv.put("height", 1100f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 7);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		// 左侧木板
		cv.put("x", 24f);
		cv.put("y", 174f);
		cv.put("width", 300f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 3);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 15f);
		cv.put("y", 174f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 24f);
		cv.put("y", 374f);
		cv.put("width", 200f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 2);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 15f);
		cv.put("y", 377f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 24f);
		cv.put("y", 600f);
		cv.put("width", 300f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 3);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 15f);
		cv.put("y", 600f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 24f);
		cv.put("y", 950f);
		cv.put("width", 300f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 2);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 15f);
		cv.put("y", 950f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		// 右侧木板
		cv.put("x", 726f);
		cv.put("y", 174f);
		cv.put("width", 150f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 2);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 867f);
		cv.put("y", 174f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 726f);
		cv.put("y", 324f);
		cv.put("width", 150f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 3);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 867f);
		cv.put("y", 324f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 676f);
		cv.put("y", 650f);
		cv.put("width", 200f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 2);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 867f);
		cv.put("y", 650f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 576f);
		cv.put("y", 950f);
		cv.put("width", 300f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 3);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 867f);
		cv.put("y", 950f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		// 中间竖板
		cv.put("x", 500f);
		cv.put("y", 24f);
		cv.put("width", 24f);
		cv.put("height", 500f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 6);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 503f);
		cv.put("y", 15f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 524f);
		cv.put("y", 450f);
		cv.put("width", 150f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 2);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 515f);
		cv.put("y", 450f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		// 滑翔板
		cv.put("sx", 350f);
		cv.put("sy", 174f);
		cv.put("dx", 350f);
		cv.put("dy", 500f);
		cv.put("t", 600f);
		cv.put("width", 150f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 2);
		cv.put("off", 3);
		db.insert("move_board", null, cv);
		cv.clear();
		// 中间跷跷板
		cv.put("x", 300f);
		cv.put("y", 800f);
		cv.put("width", 400f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 8);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();
		// 中间转板
		cv.put("x", 524f);
		cv.put("y", 174f);
		cv.put("width", 150f);
		cv.put("height", 24f);
		cv.put("angularvelocity", 1f); // 转速
		cv.put("friction", 0.5f);
		cv.put("region", 0);
		cv.put("off", 3);
		db.insert("rotate_board", null, cv);
		cv.clear();
		// 小球
		cv.put("x", 30f);
		cv.put("y", 30f);
		cv.put("radius", 20f);
		cv.put("density", 0.5f);
		cv.put("friction", 0.5f);
		cv.put("restitution", 0.6f);
		cv.put("region", 0);
		cv.put("off", 3);
		db.insert("ball", null, cv);
		cv.clear();
		// 装饰
		cv.put("x", 0f);
		cv.put("y", 1076f);
		cv.put("width", 64f);
		cv.put("height", 22f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 11);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 0f);
		cv.put("y", 1036f);
		cv.put("width", 22f);
		cv.put("height", 64f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 12);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 836f);
		cv.put("y", 1076f);
		cv.put("width", 64f);
		cv.put("height", 22f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 13);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 876f);
		cv.put("y", 1036f);
		cv.put("width", 22f);
		cv.put("height", 64f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 14);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 0f);
		cv.put("y", 0f);
		cv.put("width", 64f);
		cv.put("height", 22f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 15);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 0f);
		cv.put("y", 0f);
		cv.put("width", 22f);
		cv.put("height", 64f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 16);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 836f);
		cv.put("y", 0f);
		cv.put("width", 64f);
		cv.put("height", 22f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 17);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 876f);
		cv.put("y", 0f);
		cv.put("width", 22f);
		cv.put("height", 64f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 18);
		cv.put("off", 3);
		db.insert("static_board", null, cv);
		cv.clear();

		// 蜘蛛网
		cv.clear();
		cv.put("x1", 24f);
		cv.put("y1", 500f);
		cv.put("x2", 124f);
		cv.put("y2", 600f);
		cv.put("region", 1);
		cv.put("off", 3);
		db.insert("spiderweb", null, cv);
		cv.clear();

		cv.clear();
		cv.put("x1", 776f);
		cv.put("y1", 324f);
		cv.put("x2", 876f);
		cv.put("y2", 224f);
		cv.put("region", 2);
		cv.put("off", 3);
		db.insert("spiderweb", null, cv);
		cv.clear();

		cv.clear();
		cv.put("x1", 776f);
		cv.put("y1", 950f);
		cv.put("x2", 876f);
		cv.put("y2", 850f);
		cv.put("region", 2);
		cv.put("off", 3);
		db.insert("spiderweb", null, cv);
		cv.clear();
		// 蜜蜂
		cv.clear();
		cv.put("sx", 400f);
		cv.put("sy", 550f);
		cv.put("dx", 800f);
		cv.put("dy", 550f);
		cv.put("t", 600f);
		cv.put("width", 35f);
		cv.put("height", 30f);
		cv.put("angle", 0f);
		cv.put("off", 3);
		db.insert("move_bee", null, cv);
		// 食物
		cv.put("x", 100f);
		cv.put("y", 450f);
		cv.put("radius", 20f);
		cv.put("region", 0);
		cv.put("off", 3);
		db.insert("food", null, cv);
		cv.clear();
		cv.put("x", 800f);
		cv.put("y", 1000f);
		cv.put("radius", 20f);
		cv.put("region", 0);
		cv.put("off", 3);
		db.insert("food", null, cv);
		cv.clear();
		cv.put("x", 800f);
		cv.put("y", 50f);
		cv.put("radius", 20f);
		cv.put("region", 0);
		cv.put("off", 3);
		db.insert("food", null, cv);
		cv.clear();

// 第二关
		cv.put("off", 2);
		cv.put("width", 800);
		cv.put("height", 1100f);
		cv.put("bgregion", 2);
		cv.put("passx", 250f);
		cv.put("passy", 1060f);
		cv.put("passwidth", 100f);
		cv.put("passheight", 57f);
		cv.put("region", 0);
		db.insert("off", null, cv);
		cv.clear();

		// 小球
		cv.put("x", 30f);
		cv.put("y", 30f);
		cv.put("radius", 20f);
		cv.put("density", 0.5f);
		cv.put("friction", 0.5f);
		cv.put("restitution", 0.6f);
		cv.put("region", 0);
		cv.put("off", 2);
		db.insert("ball", null, cv);
		cv.clear();

		// 四周
		cv.put("x", 0f);
		cv.put("y", 0f);
		cv.put("width", 800f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 5);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 0f);
		cv.put("y", 0f);
		cv.put("width", 24f);
		cv.put("height", 1100f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 7);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 0f);
		cv.put("y", 1076f);
		cv.put("width", 260f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 5);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 340f);
		cv.put("y", 1076f);
		cv.put("width", 460f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 5);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 776f);
		cv.put("y", 0f);
		cv.put("width", 24f);
		cv.put("height", 1100f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 7);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		// 装饰
		cv.put("x", 0f);
		cv.put("y", 1076f);
		cv.put("width", 64f);
		cv.put("height", 22f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 11);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 0f);
		cv.put("y", 1036f);
		cv.put("width", 22f);
		cv.put("height", 64f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 12);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 736f);
		cv.put("y", 1076f);
		cv.put("width", 64f);
		cv.put("height", 22f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 13);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 776f);
		cv.put("y", 1036f);
		cv.put("width", 22f);
		cv.put("height", 64f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 14);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 0f);
		cv.put("y", 0f);
		cv.put("width", 64f);
		cv.put("height", 22f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 15);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 0f);
		cv.put("y", 0f);
		cv.put("height", 64f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 16);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 736f);
		cv.put("y", 0f);
		cv.put("width", 64f);
		cv.put("height", 22f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 17);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 776f);
		cv.put("y", 0f);
		cv.put("width", 22f);
		cv.put("height", 64f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 18);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		// 左侧木板
		cv.put("x", 24f);
		cv.put("y", 750f);
		cv.put("width", 100f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 1);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 15f);
		cv.put("y", 750f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		// 右侧木板
		cv.put("x", 526f);
		cv.put("y", 324f);
		cv.put("width", 250f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 3);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 767f);
		cv.put("y", 324f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 576f);
		cv.put("y", 900f);
		cv.put("width", 200f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 2);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 767f);
		cv.put("y", 900f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 526f);
		cv.put("y", 600f);
		cv.put("width", 250f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 4);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 767f);
		cv.put("y", 600f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		// 竖板
		cv.put("x", 400f);
		cv.put("y", 600f);
		cv.put("width", 24f);
		cv.put("height", 476f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 7);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 403f);
		cv.put("y", 1067f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 250f);
		cv.put("y", 925f);
		cv.put("width", 150f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 2);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 391f);
		cv.put("y", 925f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		

		cv.put("x", 424f);
		cv.put("y", 750f);
		cv.put("width", 150f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 2);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 415f);
		cv.put("y", 750f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		cv.put("x", 150f);
		cv.put("y", 600f);
		cv.put("width", 250f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 4);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();
		cv.put("x", 391f);
		cv.put("y", 600f);
		cv.put("width", 18f);
		cv.put("height", 18f);
		cv.put("angle", 0f);
		cv.put("friction", 0.5f);
		cv.put("region", 19);
		cv.put("off", 2);
		db.insert("static_board", null, cv);
		cv.clear();

		// 滑翔板
		cv.put("sx", 25f);
		cv.put("sy", 174f);
		cv.put("dx", 24f);
		cv.put("dy", 600f);
		cv.put("t", 600f);
		cv.put("width", 100f);
		cv.put("height", 24f);
		cv.put("angle", 0f);
		cv.put("friction", 1f);
		cv.put("region", 2);
		cv.put("off", 2);
		db.insert("move_board", null, cv);
		cv.clear();

		// 转动板
		cv.put("x", 200f);
		cv.put("y", 174f);
		cv.put("width", 400f);
		cv.put("height", 24f);
		cv.put("angularvelocity", 0f); // 转速
		cv.put("friction", 0.5f);
		cv.put("region", 0);
		cv.put("off", 2);
		db.insert("rotate_board", null, cv);
		cv.clear();

		cv.put("x", 250f);
		cv.put("y", 450f);
		cv.put("width", 300f);
		cv.put("height", 24f);
		cv.put("angularvelocity", 0f); // 转速
		cv.put("friction", 0.5f);
		cv.put("region", 0);
		cv.put("off", 2);
		db.insert("rotate_board", null, cv);
		cv.clear();

		// 食物
		cv.put("x", 200f);
		cv.put("y", 1000f);
		cv.put("radius", 20f);
		cv.put("region", 0);
		cv.put("off", 2);
		db.insert("food", null, cv);
		cv.clear();

		cv.put("x", 500f);
		cv.put("y", 1000f);
		cv.put("radius", 20f);
		cv.put("region", 0);
		cv.put("off", 2);
		db.insert("food", null, cv);
		cv.clear();

		cv.put("x", 730f);
		cv.put("y", 550f);
		cv.put("radius", 20f);
		cv.put("region", 0);
		cv.put("off", 2);
		db.insert("food", null, cv);

		// 蜘蛛网
		cv.clear();
		cv.put("x1", 24f);
		cv.put("y1", 950f);
		cv.put("x2", 200f);
		cv.put("y2", 1076f);
		cv.put("region", 1);
		cv.put("off", 2);
		db.insert("spiderweb", null, cv);
		cv.clear();
		cv.put("x1", 424f);
		cv.put("y1", 950f);
		cv.put("x2", 600f);
		cv.put("y2", 1076f);
		cv.put("region", 1);
		cv.put("off", 2);
		db.insert("spiderweb", null, cv);

		// 蜜蜂
		cv.clear();
		cv.put("sx", 250f);
		cv.put("sy", 300f);
		cv.put("dx", 480f);
		cv.put("dy", 300f);
		cv.put("t", 600f);
		cv.put("width", 35f);
		cv.put("height", 30f);
		cv.put("angle", 0f);
		cv.put("off", 2);
		db.insert("move_bee", null, cv);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS config");
		db.execSQL("DROP TABLE IF EXISTS ball");
		db.execSQL("DROP TABLE IF EXISTS food");
		db.execSQL("DROP TABLE IF EXISTS rotate_board");
		db.execSQL("DROP TABLE IF EXISTS move_board");
		db.execSQL("DROP TABLE IF EXISTS move_bee");
		db.execSQL("DROP TABLE IF EXISTS static_board");
		db.execSQL("DROP TABLE IF EXISTS spiderweb");
		db.execSQL("DROP TABLE IF EXISTS off");
		db.execSQL("DROP TABLE IF EXISTS rank");
		onCreate(db);
	}

}
