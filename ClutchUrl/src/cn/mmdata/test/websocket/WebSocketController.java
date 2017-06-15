package cn.mmdata.test.websocket;

import java.io.IOException;
import java.io.InputStream;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/log")
public class WebSocketController {
	private Process process;
    private InputStream inputStream;
	
	/**
     * 新的WebSocket请求开启
     */
    @OnOpen
    public void onOpen(Session session) {
        try {
            // 执行tail -f命令
//            process = Runtime.getRuntime().exec("more f:\\topweb.csv");
            process = Runtime.getRuntime().exec("tail -f /data2/cloud/squid/log/access.log");
            inputStream = process.getInputStream();
            System.out.println("tail -f /data2/zlh/log.txt");
            // 一定要启动新的线程，防止InputStream阻塞处理WebSocket的线程
            TailLogThread thread = new TailLogThread(inputStream, session);
            thread.start();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * WebSocket请求关闭
     */
    @OnClose
    public void onClose() {
        try {
            if(inputStream != null)
                inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(process != null)
            process.destroy();
    }

    @OnError
    public void onError(Throwable thr) {
        thr.printStackTrace();
    }

	@OnMessage
	public void onMessage(String requestJson, Session session) {
		try {
			if(requestJson!=null && requestJson.trim().length()>0) {
				session.getBasicRemote().sendText(requestJson);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
