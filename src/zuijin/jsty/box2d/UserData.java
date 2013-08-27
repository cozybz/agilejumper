package zuijin.jsty.box2d;

public class UserData {
	public int type;	//ball 0  food 1 sb 2 rb 3 mb 4 sw 5
	public boolean jumpable;
	public int eatNum;
	public UserData(int type){
		this.type=type;
	}
}
