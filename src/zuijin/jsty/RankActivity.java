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
	private ArrayList<HashMap<String, Object>> listItems; // ������֡�ͼƬ��Ϣ
	private SimpleAdapter listItemAdapter; // ������
	private List<Rank> rs=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//��ȥ��������Ӧ�ó�������֣�  
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //��ȥ״̬������(��ص�ͼ���һ�����β���)
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
			map.put("Id", rs.get(i).getId()); // ����
			map.put("Name",rs.get(i).getName());// ͼƬ
			map.put("Time", rs.get(i).getTime()+"s");
			listItems.add(map);
		}
		// ������������Item�Ͷ�̬�����Ӧ��Ԫ��
		listItemAdapter = new SimpleAdapter(this, listItems,// ����Դ
			R.layout.rank_list_item,// ListItem��XML����ʵ��
			// ��̬������ImageItem��Ӧ������
			new String[] { "Id", "Name","Time" },
			// ImageItem��XML�ļ������һ��ImageView,����TextView ID
			new int[] { R.id.Id, R.id.Name,R.id.Time});
	}
	
}

