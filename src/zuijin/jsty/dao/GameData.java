package zuijin.jsty.dao;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import zuijin.jsty.actor.Ball;
import zuijin.jsty.actor.Food;
import zuijin.jsty.actor.MoveBee;
import zuijin.jsty.actor.MoveBoard;
import zuijin.jsty.actor.PassBoard;
import zuijin.jsty.actor.RotateBoard;
import zuijin.jsty.actor.SpiderWeb;
import zuijin.jsty.actor.StaticBoard;
import zuijin.jsty.utils.Assets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GameData {
	private static SQLiteOpenHelper dbhelper;

	public static void initDataBaseHelper(Context context) {
		dbhelper = new SQLiteOpenHelper(context);
	}

	public static List<Ball> getBall(int off) {
		List<Ball> list = new ArrayList<Ball>();
		Ball ball;

		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = db.query("ball", new String[] { "x", "y", "radius",
				"density", "friction", "restitution", "region" }, "off=" + off,
				null, null, null, null);
		TextureRegion region = null;
		while (cursor.moveToNext()) {
			switch (cursor.getInt(6)) {
			case 0:
				region = Assets.ball;
				break;
			default:
				region = null;
				break;
			}
			ball = new Ball(cursor.getFloat(0), cursor.getFloat(1),
					cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4),
					cursor.getFloat(5), region);
			list.add(ball);
		}

		cursor.close();
		db.close();

		return list;
	}

	public static List<Food> getFood(int off) {
		List<Food> list = new ArrayList<Food>();
		Food food;

		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = db.query("food", new String[] { "x", "y", "radius",
				"region" }, "off=" + off, null, null, null, null);
		TextureRegion region = null;
		while (cursor.moveToNext()) {
			switch (cursor.getInt(3)) {
			case 0:
				region = Assets.food;
				break;
			default:
				region = null;
				break;
			}
			food = new Food(cursor.getFloat(0), cursor.getFloat(1),
					cursor.getFloat(2), region);
			list.add(food);
		}
		cursor.close();
		db.close();

		return list;
	}

	public static List<MoveBoard> getMoveBoard(int off) {
		List<MoveBoard> list = new ArrayList<MoveBoard>();
		MoveBoard board;

		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = db.query("move_board", new String[] { "sx", "sy", "dx",
				"dy", "t", "width", "height", "angle", "friction", "region" },
				"off=" + off, null, null, null, null);
		TextureRegion region = null;
		while (cursor.moveToNext()) {
			switch (cursor.getInt(9)) {
			case 0:
				region = Assets.commonBoard;
				break;
			case 1:
				region = Assets.board1;
				break;
			case 2:
				region = Assets.board2;
				break;
			case 3:
				region = Assets.board3;
				break;
			default:
				region = null;
				break;
			}
			board = new MoveBoard(cursor.getFloat(0), cursor.getFloat(1),
					cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4),
					cursor.getFloat(5), cursor.getFloat(6), cursor.getFloat(7),
					cursor.getFloat(8), region);
			list.add(board);
		}

		cursor.close();
		db.close();
		return list;
	}

	public static List<RotateBoard> getRotateBoard(int off) {
		List<RotateBoard> list = new ArrayList<RotateBoard>();
		RotateBoard board;

		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = db.query("rotate_board", new String[] { "x", "y",
				"width", "height", "angularvelocity", "friction", "region" },
				"off=" + off, null, null, null, null);
		TextureRegion region = null;
		while (cursor.moveToNext()) {
			switch (cursor.getInt(6)) {
			case 0:
				region = Assets.moveBoard;
				break;
			default:
				region = null;
				break;
			}
			board = new RotateBoard(cursor.getFloat(0), cursor.getFloat(1),
					cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4),
					cursor.getFloat(5), region);
			list.add(board);
		}

		cursor.close();
		db.close();
		return list;
	}

	public static List<StaticBoard> getStaticBoard(int off) {
		List<StaticBoard> list = new ArrayList<StaticBoard>();
		StaticBoard board;
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = db.query("static_board", new String[] { "x", "y",
				"width", "height", "angle", "friction", "region" }, "off="
				+ off, null, null, null, null);
		TextureRegion region = null;
		while (cursor.moveToNext()) {
			switch (cursor.getInt(6)) {
			case 0:
				region = Assets.commonBoard;
				break;
			case 1:
				region = Assets.board1;
				break;
			case 2:
				region = Assets.board2;
				break;
			case 3:
				region = Assets.board3;
				break;
			case 4:
				region = Assets.board4;
				break;
			case 5:
				region = Assets.board5;
				break;
			case 6:
				region = Assets.board6;
				break;
			case 7:
				region = Assets.board7;
				break;
			case 8:
				region = Assets.moveBoard;
				break;
			case 11:
				region = Assets.iron1_1;
				break;
			case 12:
				region = Assets.iron1_2;
				break;
			case 13:
				region = Assets.iron2_1;
				break;
			case 14:
				region = Assets.iron2_2;
				break;
			case 15:
				region = Assets.iron3_1;
				break;
			case 16:
				region = Assets.iron3_2;
				break;
			case 17:
				region = Assets.iron4_1;
				break;
			case 18:
				region = Assets.iron4_2;
				break;
			case 19:
				region = Assets.nail;
				break;
			default:
				region = null;
				break;
			}
			board = new StaticBoard(cursor.getFloat(0), cursor.getFloat(1),
					cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4),
					cursor.getFloat(5), region);
			list.add(board);
		}

		cursor.close();
		db.close();
		return list;
	}

	public static int getOff() {
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = db.query("config", new String[] { "value" }, "item=2",
				null, null, null, null);
		int maxoff = 1;
		if (cursor.moveToNext())
			maxoff = cursor.getInt(0);
		cursor.close();
		db.close();
		return maxoff;
	}

	public static void saveOff(int off) {
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("value", off);
		db.update("config", cv, "item=2", null);
		db.close();
		cv.clear();
	}

	public static void saveVolume(int volume) {
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("value", volume);
		db.update("config", cv, "item=1", null);
		db.close();
		cv.clear();
	}

	public static int getVolume() {
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = db.query("config", new String[] { "value" }, "item=1",
				null, null, null, null);
		int volume = 0;
		if (cursor.moveToNext())
			volume = cursor.getInt(0);
		cursor.close();
		db.close();
		return volume;
	}

	public static float getMapWidth(int off) {
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = db.query("off", new String[] { "width" }, "off=" + off,
				null, null, null, null);
		int width = 0;
		if (cursor.moveToNext())
			width = cursor.getInt(0);
		cursor.close();
		db.close();
		return width;
	}

	public static float getMapHeight(int off) {
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = db.query("off", new String[] { "height" },
				"off=" + off, null, null, null, null);
		int height = 0;
		if (cursor.moveToNext())
			height = cursor.getInt(0);
		cursor.close();
		db.close();
		return height;
	}

	public static PassBoard getPassBoard(int off) {
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = db.query("off", new String[] { "passx", "passy",
				"passwidth", "passheight", "region" }, "off=" + off, null,
				null, null, null);
		PassBoard passboard = null;
		TextureRegion region = null;
		if (cursor.moveToNext()) {
			switch (cursor.getInt(4)) {
			case 0:
				region = Assets.ufo;
				break;
			default:
				region = null;
				break;
			}
			passboard = new PassBoard(cursor.getFloat(0), cursor.getFloat(1),
					cursor.getFloat(2), cursor.getFloat(3), 0f, 0f, region);
		}
		cursor.close();
		db.close();
		return passboard;
	}

	public static TextureRegion getBackgroundRegion(int off) {
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = db.query("off", new String[] { "bgregion" }, "off="
				+ off, null, null, null, null);
		if (cursor.moveToNext()) {
			switch (cursor.getInt(0)) {
			case 1:
				return Assets.background1;
			case 2:
				return Assets.background2;
			case 3:
				return Assets.background3;
			}
		}
		cursor.close();
		db.close();
		return null;
	}

	public static List<SpiderWeb> getSpiderWeb(int off) {
		List<SpiderWeb> list = new ArrayList<SpiderWeb>();
		SpiderWeb spiderWeb;
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = db.query("spiderweb", new String[] { "x1", "y1", "x2",
				"y2", "region", "off" }, "off=" + off, null, null, null, null);
		TextureRegion region = null;
		while (cursor.moveToNext()) {
			switch (cursor.getInt(4)) {
			case 1:
				region = Assets.spiderWeb1;
				break;
			case 2:
				region = Assets.spiderWeb2;
				break;
			}
			spiderWeb = new SpiderWeb(cursor.getFloat(0), cursor.getFloat(1),
					cursor.getFloat(2), cursor.getFloat(3), region);
			list.add(spiderWeb);
		}

		cursor.close();
		db.close();
		return list;
	}

	public static List<MoveBee> getMoveBee(int off) {
		List<MoveBee> list = new ArrayList<MoveBee>();
		MoveBee moveBee;
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = db.query("move_bee", new String[] { "sx", "sy", "dx",
				"dy", "t", "width", "height", "angle" }, "off=" + off, null,
				null, null, null);
		while (cursor.moveToNext()) {
			moveBee = new MoveBee(cursor.getFloat(0), cursor.getFloat(1),
					cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4),
					cursor.getFloat(5), cursor.getFloat(6), cursor.getFloat(7));
			list.add(moveBee);
		}

		cursor.close();
		db.close();
		return list;
	}

	public static void rankInsert(int off, String name, String time) {
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("id", off);
		cv.put("name", name);
		cv.put("time", time);
		db.insert("rank", null, cv);
		db.close();
	}

	public static List<Rank> getRank() {
		List<Rank> list = new ArrayList<Rank>();
		Rank rank;
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = db.query("rank", new String[] { "id", "name", "time" },
				null, null, null, null, null);
		while (cursor.moveToNext()) {
			rank = new Rank();
			rank.setId(cursor.getInt(0));
			rank.setName(cursor.getString(1));
			rank.setTime(cursor.getString(2));
			list.add(rank);
		}
		cursor.close();
		db.close();
		return list;
	}

	public static String getTime(int off) {
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		String time = null;
		Cursor cursor = db.query("rank", new String[] { "time" }, "id=" + off,
				null, null, null, null);
		if (cursor.moveToNext())
			time = cursor.getString(0);

		cursor.close();
		db.close();
		return time;

	}
}
