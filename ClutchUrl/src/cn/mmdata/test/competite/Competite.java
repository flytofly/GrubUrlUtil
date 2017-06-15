package cn.mmdata.test.competite;

import java.util.List;


import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

public class Competite extends Model<Competite>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<Record> searchAllurl(){
		String sql = "select * from stat_competitor_url";
		List<Record> find = Db.find(sql);
		return find;
	}
	

	
	public int add_ad(Competite competite){
		String sql = "insert into stat_competitor_url(ad_name,long_connect) values('"+competite.getStr("ad_name")+"','"+competite.getStr("long_connect")+"')";
		int ad_add = Db.update(sql);
		return ad_add;
	}
	
	
	
	
	
	
}
