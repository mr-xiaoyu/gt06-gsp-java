package club.mrxiao.gps.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * GPS服务端线程
 * @author  xiaoyu
 *
 */
@Slf4j
public class GpsService extends Thread{  
    private ServerSocket serverSocket = null;  
    private Boolean sign = true;
    private Integer max;
    public static final List<GpsDataDispose> GPS_DATA_DISPOSE_LIST = new ArrayList<>();

    /**
     * 启动socket监听
     * @param port 端口
     * @param max 最大连接数
     */
    public GpsService(Integer port,Integer max){
        try {
            this.max = max;
            if(null == serverSocket){  
                this.serverSocket = new ServerSocket(port);
                log.info("serverSocket线程创建成功,端口：{}",port);
            }  
        } catch (Exception e) {  
        	log.error("GpsService创建socket服务出错,端口"+port+"被其他程序使用",e);
        	this.interrupt();
        }
    }  
      
    @Override
    public void run(){
        while(sign){  
            try {  
                Socket socket = serverSocket.accept();
                if(max == -1 || max > GPS_DATA_DISPOSE_LIST.size()){
                	if(null != socket && !socket.isClosed()){  
                    	GpsDataDispose gpsDataDispose = new GpsDataDispose(socket);
                        GPS_DATA_DISPOSE_LIST.add(gpsDataDispose);
                    	log.info("socket连接数：{}",GPS_DATA_DISPOSE_LIST.size());
                        //处理接受的数据  
                    	new Thread(gpsDataDispose).start();
                    }
                    assert socket != null;
                    socket.setSoTimeout(600000);
                }else{
                	log.error("socket连接数超出最大连接数");
                	socket.close();
                }
            }catch (Exception e) {  
            	sign = false;
            	log.error("socket监听出错",e);
            	this.interrupt();
            }  
        }  
    }  
      
      
    public void closeSocketServer(){  
       try {  
            if(null!=serverSocket && !serverSocket.isClosed()){ 
            	log.info("serverSocket关闭");
            	serverSocket.close();  
            }
       } catch (IOException e) {  
        e.printStackTrace();  
       }  
     } 
}