package club.mrxiao.gps.socket.util;

/**
 * GPS数据处理工具类
 * @author  xiaoyu
 *
 */
public class GpsDataUtil {
	private static final String[] BINARY_ARRAY ={
		"0000","0001","0010","0011",  
		"0100","0101","0110","0111",  
        "1000","1001","1010","1011",  
        "1100","1101","1110","1111"};  

	/**将16进制转换为二进制
     * @param hexStr
     * @return
     */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1) {
            return new byte[1024];
        }
		byte[] result = new byte[hexStr.length()/2];
		for (int i = 0;i< hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	
	 /** 
     *  
     * @param bArray
     * @return 转换为二进制字符串 
     */  
    public static String bytes2BinaryStr(byte[] bArray){  
        StringBuilder outStr = new StringBuilder();
        int pos;
        for(byte b:bArray){  
            //高四位  
            pos = (b&0xF0)>>4;  
            outStr.append(BINARY_ARRAY[pos]);
            //低四位  
            pos=b&0x0F;  
            outStr.append(BINARY_ARRAY[pos]);
        }  
        return outStr.toString();
          
    } 
	
	/**
	 * 将GPS16进制时间字符串转换为Unix时间戳
	 * @param hexStr
	 * @return
	 */
	public static String parseGpsHexDate2Unix(String hexStr){
		
		// 年(1字节)
		String year = hexStr.substring(0, 2);
		year = String.format("%02d",Integer.parseInt(year, 16));
		hexStr = hexStr.substring(2);
		
		// 月(1字节)
		String month = hexStr.substring(0, 2);
		month = String.format("%02d",Integer.parseInt(month, 16));
		hexStr = hexStr.substring(2);
		
		// 日(1字节)
		String day = hexStr.substring(0, 2);
		day = String.format("%02d",Integer.parseInt(day, 16));
		hexStr = hexStr.substring(2);
		
		// 时(1字节)
		String time = hexStr.substring(0, 2);
		time = String.format("%02d",Integer.parseInt(time, 16));
		hexStr = hexStr.substring(2);
		
		// 分(1字节)
		String minute = hexStr.substring(0, 2);
		minute = String.format("%02d",Integer.parseInt(minute, 16));
		hexStr = hexStr.substring(2);
		
		// 秒(1字节)
		String second = hexStr.substring(0, 2);
		second = String.format("%02d",Integer.parseInt(second, 16));

		String date = "20"+year+"-"+month+"-"+day+" "+time+":"+minute+":"+second;
		
		return DateUtil.Date2TimeStamp(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 16进制经纬度转换
	 * @param hexStr 16进制纬度或经度
	 * @return
	 */
	public static Double parseHexStr2Location(String hexStr) {
		if(StringUtil.isNullStr(hexStr)){
			return null;
		}
		return ArithUtil.div(Integer.parseInt(hexStr, 16),1800000,7);
	}
}
