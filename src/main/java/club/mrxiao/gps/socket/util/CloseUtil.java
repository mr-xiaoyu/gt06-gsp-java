package club.mrxiao.gps.socket.util;

import java.io.Closeable;

/**
 *  关闭连接
 * @author xiaoyu
 */
public class CloseUtil {
public static void closeAll(Closeable...io)
{
	for(Closeable temp :io)
	{
		try{
			if(null!=temp)
			{
				temp.close();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		
}
}
