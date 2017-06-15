package cn.mmdata.test.competite;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

public class CompetiteController extends Controller {

	
	public void show_(){
		render("/WEB-INF/index.html");
	}
	
	public void url_add() {
		String url = getPara("url");
		Competite competite = getModel(Competite.class);
		Competite com = new Competite();
		com.set("ad_name","test");
		com.set("long_connect",url);
		competite.add_ad(com);
		show_();
	}
	
	
	
}
