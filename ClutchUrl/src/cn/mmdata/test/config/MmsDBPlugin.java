package cn.mmdata.test.config;

import cn.mmdata.test.competite.Competite;

import com.jfinal.config.Plugins;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.c3p0.C3p0Plugin;



public class MmsDBPlugin {
	public static void addPlugin(Plugins me) {
		
		/*PropKit.use("jdbc.properties");*/
        //C3p0数据源插件
		String string = PropKit.get("mms.jdbcUrl");
		System.out.println(string);
        C3p0Plugin c3p0 = new C3p0Plugin(PropKit.get("mms.jdbcUrl"),PropKit.get("mms.user"),PropKit.get("mms.password"));
		// 初始化连接池数量大小,默认10
        c3p0.setInitialPoolSize(PropKit.getInt("initialSize"));
		// 初始化最小空闲连接数,默认10
        c3p0.setMinPoolSize(PropKit.getInt("minIdle"));
		// 初始化最大活跃连接数,默认100
        c3p0.setMaxPoolSize(PropKit.getInt("maxIdle"));
		// 配置获取连接等待超时的时间
        c3p0.setMaxIdleTime(25000);
        me.add(c3p0);
		// 配置ActiveRecord插件
		ActiveRecordPlugin activeRecord = new ActiveRecordPlugin(DbKit.MAIN_CONFIG_NAME, c3p0);
		activeRecord.addMapping("stat_competitor_url",Competite.class);
		me.add(activeRecord);
		activeRecord.setShowSql(true);
	}

}
