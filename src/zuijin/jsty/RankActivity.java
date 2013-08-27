package zuijin.jsty;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import zuijin.jsty.dao.GameData;
import zuijin.jsty.dao.Rank;
import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class RankActivity extends Activity {
	private ArrayList<HashMap<String, Object>> listItems; // 存放文字、图片信息
	private SimpleAdapter listItemAdapter; // 适配器
	private List<Rank> rs=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//隐去标题栏（应用程序的名字）  
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐去状态栏部分(电池等图标和一切修饰部分)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.rank_list);
		initView();
	}	
	private void initView(){
		rs=GameData.getRank();	
		initListView(rs);
		ListView listView=(ListView) findViewById(R.id.RankList);
		listView.setAdapter(listItemAdapter);
	}	
	private void initListView(List<Rank> rs) {
		listItems = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < rs.size(); i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Id", rs.get(i).getId()); // 文字
			map.put("Name",rs.get(i).getName());// 图片
			map.put("Time", rs.get(i).getTime()+"s");
			listItems.add(map);
		}
		// 生成适配器的Item和动态数组对应的元素
		listItemAdapter = new SimpleAdapter(this, listItems,// 数据源
			R.layout.rank_list_item,// ListItem的XML布局实现
			// 动态数组与ImageItem对应的子项
			new String[] { "Id", "Name","Time" },
			// ImageItem的XML文件里面的一个ImageView,两个TextView ID
			new int[] { R.id.Id, R.id.Name,R.id.Time});
	}
	
}

