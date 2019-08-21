package club.mrxiao.gps.socket.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * GT06数据流解析实体
 * @author  xiaoyu
 *
 */
@SuppressWarnings("ALL")
@Data
public class Gt06InputDTO implements Serializable {

	private static final long serialVersionUID = 5273619433954288442L;
	
	/**
	 * 开始位
	 */
	public static final String START_BIT  = "7878";
	
	/**
	 * 停止位
	 */
	public static final String END_BIT  = "0D0A";
	
	
	/**
	 * 协议号 01 登录信息
	 */
	public static final String PROTOCOL_NUMBER_LOGIN_INFORMATION = "01";
	/**
	 * 协议号 12 GPS信息
	 */
	public static final String PROTOCOL_NUMBER_GPS_INFORMATION = "12";
	/**
	 * 协议号 13 状态信息
	 */
	public static final String PROTOCOL_NUMBER_STATE_INFORMATION = "13";
	/**
	 * 协议号 15 字符串信息
	 */
	public static final String PROTOCOL_NUMBER_STRING_INFORMATION = "21";
	/**
	 * 协议号 16 报警信息
	 */
	public static final String PROTOCOL_NUMBER_GPSANDSTATE_INFORMATION = "16";
	
	/**
	 * 协议号 02 ICCID
	 */
	public static final String PROTOCOL_NUMBER_ICCID = "02";
	/**
	 * 协议号 80 服务器指令
	 */
	public static final String PROTOCOL_NUMBER_INSTRUCT = "80";
	

	/** 起始位 */
	private String start;

	/** 长度 */
	private Integer length;

	/** 协议号 */
	private String protocolNumber;

	/** 信息内容 */
	private String content;

	/** 信息序列号 */
	private String seriesNumber;

	/** 错误校验 */
	private String crc;

	/** 结束位 */
	private String end;

	/** 原始数据 */
	private String originalData;

	
	/**
	 * 获取起始位
	 * @return
	 */
	public String getStart() {
		return start;
	}

	/**
	 * 设置起始位
	 * @param start
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * 获取长度
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * 设置长度
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * 获取协议号
	 * @return
	 */
	public String getProtocolNumber() {
		return protocolNumber;
	}

	/**
	 * 设置协议号
	 * @param protocolNumber
	 */
	public void setProtocolNumber(String protocolNumber) {
		this.protocolNumber = protocolNumber;
	}

	/**
	 * 获取内容
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取信息序列号
	 * @return
	 */
	public String getSeriesNumber() {
		return seriesNumber;
	}

	/**
	 * 设置信息序列号
	 * @param seriesNumber
	 */
	public void setSeriesNumber(String seriesNumber) {
		this.seriesNumber = seriesNumber;
	}

	/**
	 * 获取错误校验
	 * @return
	 */
	public String getCrc() {
		return crc;
	}

	/**
	 * 设置错误校验
	 * @param crc
	 */
	public void setCrc(String crc) {
		this.crc = crc;
	}

	/**
	 * 获取结束位
	 * @return
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * 设置结束位
	 * @param end
	 */
	public void setEnd(String end) {
		this.end = end;
	}

	/**
	 * 获取原始数据
	 * @return
	 */
	public String getOriginalData() {
		return originalData;
	}

	/**
	 * 设置原始数据
	 * @param originalData
	 */
	public void setOriginalData(String originalData) {
		this.originalData = originalData;
	}
	
	/**
	 * Gt06InputDTO 构造函数
	 * @param input GI06协议16进制字符串
	 */
	public Gt06InputDTO(String input) {
		parseGt06Info(input);
	}
	
	/**
	 * 解析gt06协议的字符串
	 * 
	 * @param input GI06协议16进制字符串
	 */
	private void parseGt06Info(String input) {

		// 保存原始数据
		originalData = input;

		// 信息头(2字节)
		start = input.substring(0, 2 * 2);
		input = input.substring(2 * 2);

		// 内容长度(1字节)
		String neiRongChangDu = input.substring(0,2);
		length = Integer.parseInt(neiRongChangDu, 16);
		input = input.substring(2);

		// 协议号(1字节)
		protocolNumber = input.substring(0, 2);
		input = input.substring(2);

		// 信息内容(N字节)
		content = input.substring(0, length * 2 - 5 * 2);
		input = input.substring(length * 2 - 5 * 2);

		// 序列号(2字节)
		seriesNumber = input.substring(0, 2 * 2);
		input = input.substring(2 * 2);

		// 错误校验码(2字节)
		crc = input.substring(0, 2 * 2);
		input = input.substring(2 * 2);

		// 结束位(2字节)
		end = input.substring(0, 2 * 2);
	}
}
