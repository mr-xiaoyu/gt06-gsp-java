package club.mrxiao.gps.socket.util;

/**
 * 字节数组与16进制字符串相互转换的工具类
 * 
 * @author zhu@goome
 *
 */
public class Byte2Hex {
	 /**
	 * 16进制char转换成整型
	 * 
	 * @param c
	 * @return
	 */
	public static int parse(char c) {
		if (c >= 'a') {
			return (c - 'a' + 10) & 0x0f;
		}
		if (c >= 'A') {
			return (c - 'A' + 10) & 0x0f;
		}
		return (c - '0') & 0x0f;
	}
	 /**
	 * 十六进制字符串转换成字节数组
	 * 
	 * @param hexstr
	 * @return
	 */
	public static byte[] hexString2Bytes(String hexstr) {
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}
	
	/**
     * 将 byte 数组转化为十六进制字符串
     *
     * @param bytes byte[] 数组
     * @param begin 起始位置
     * @param end 结束位置
     * @return byte 数组的十六进制字符串表示
     */
	public static String bytesToHex(byte[] bytes, int begin, int end) {
		if(end == -1){
			return null;
		}
        StringBuilder hexBuilder = new StringBuilder(2 * (end - begin));
        for (int i = begin; i < end; i++) {
			// 转化高四位
            hexBuilder.append(Character.forDigit((bytes[i] & 0xF0) >> 4, 16));
			// 转化低四位
            hexBuilder.append(Character.forDigit((bytes[i] & 0x0F), 16));
        }
        return hexBuilder.toString().toUpperCase();
    }
}
