package cn.mmdata.test.config;


import cn.mmdata.test.competite.Competite;
import cn.mmdata.test.competite.CompetiteController;
import cn.mmdata.test.websocket.WebSocketHandler;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;

public class CommonConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		loadPropertyFile("jdbc.properties");
		me.setDevMode(getPropertyToBoolean("devMode", true));
	}

	
	
	@Override
	public void configRoute(Routes me) {
		me.add("/competite", CompetiteController.class);
	}

	@Override
	public void configPlugin(Plugins me) {
		MmsDBPlugin.addPlugin(me);
	}

	@Override
	public void configInterceptor(Interceptors me) {
	}

	@Override
	public void afterJFinalStart() {
		// 开启任务
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new WebSocketHandler("^/websocket"));
	}

}
