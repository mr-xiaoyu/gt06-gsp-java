package cn.hehej.serversocket.util;

import org.apache.commons.lang.StringUtils;

import cn.hehej.serversocket.domain.Gt06InputDTO;

/**
 * 协议处理
 * @author  xiaoyu
 *
 */
public class ProtocolUtil {
	
	/**
	 * 服务器响应数据构造
	 * @param protocolNumber 协议号
	 * @param seriesNumber 信息序列号
	 * @return
	 */
	public static String serverResponseData(String protocolNumber,String seriesNumber){
		if(StringUtils.isNotBlank(seriesNumber) && StringUtils.isNotBlank(protocolNumber) ){
			String start = Gt06InputDTO.START_BIT;
			String length = String.format("%02x",5).toUpperCase();
			String hexstr = length+protocolNumber+seriesNumber;
			Crc16Util crc16 = new Crc16Util();
			crc16.reset();
    		crc16.update(Byte2Hex.hexString2Bytes(hexstr));
    		String crc = Integer.toHexString(crc16.getCrcValue()).toUpperCase();
            return start+length+protocolNumber+seriesNumber+crc+Gt06InputDTO.END_BIT;
		}
		return null;
	}
}