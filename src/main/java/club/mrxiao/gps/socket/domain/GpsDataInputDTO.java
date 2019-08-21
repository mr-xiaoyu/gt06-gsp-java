package club.mrxiao.gps.socket.domain;

import club.mrxiao.gps.socket.util.GpsDataUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * GPS数据解析
 * @author  xiaoyu
 *
 */
@Data
public class GpsDataInputDTO implements Serializable{

	private static final long serialVersionUID = -5658760523370415966L;
	
	/**日期*/
	private String date;
	/**卫星星数*/
	private String satelliteNumber;
	/**纬度*/
	private Double latitude;
	/**经度*/
	private Double longitude;
	/**速度*/
	private Double speed;
	/**GPS状态*/
	private String gpsState;
	/**航向*/
	private Integer course;
	/**国家代号*/
	private String mcc;
	/**移动网号码*/
	private String mnc;
	/**位置区码*/
	private String lac;
	/**移动基站*/
	private String cellID;
	/**原始数据*/
	private String originalData;
	/**路线名*/
	private String name;
	/**路线ID*/
	private Integer roadId;
	/**服务人员ID*/
	private Integer workId;
	/**设备IMEI码*/
	private String imei;
	/**
	 * 构造函数
	 * @param input  GPS信息数据
	 * @param name 路线名
	 * @param roadId 路线ID
	 * @param workId 服务人员ID
	 * @param imei 设置imei码
	 */
	public GpsDataInputDTO(String input,String name,Integer roadId,Integer workId,String imei) {
		parseGpsDataInfo(input,name,roadId,workId,imei);
	}
	
	/**
	 * GPS信息数据解析
	 * @param input  GPS信息数据
	 */
	private void parseGpsDataInfo(String input,String thisName,Integer thisRoadId,Integer thisWorkId,String thisImei){
        this.originalData = input;
		//路线名
		this.name = thisName;
		//路线ID
		this.roadId = thisRoadId;
		//服务人员ID
		this.workId = thisWorkId;
		//设备imei
		this.imei = thisImei;
		// 日期(6字节)
		this.date = GpsDataUtil.parseGpsHexDate2Unix(input.substring(0, 2 * 6));
		input = input.substring(2 * 6);
		// 卫星星数(1字节)
		this.satelliteNumber = input.substring(0, 2);
		input = input.substring(2);
		// 纬度(4字节)
		this.latitude = GpsDataUtil.parseHexStr2Location(input.substring(0, 2 * 4));
		input = input.substring(2 * 4);
		// 经度(4字节)
		this.longitude = GpsDataUtil.parseHexStr2Location(input.substring(0, 2 * 4));
		input = input.substring(2 * 4);
		// 速度(1字节)
		this.speed = (double) Integer.parseInt(input.substring(0, 2), 16);
		input = input.substring(2);
		
		// GPS状态(1字节)
		String state = GpsDataUtil.bytes2BinaryStr(GpsDataUtil.parseHexStr2Byte(input.substring(0, 2)));
		this.gpsState = state;
		input = input.substring(2);
		
		//航向(1字节)   航向会挪用状态里面2个bit
		String cover = state.substring(state.length()-2);
		String courseString = cover+GpsDataUtil.bytes2BinaryStr((GpsDataUtil.parseHexStr2Byte(input.substring(0, 2))));
		course = Integer.parseInt(courseString,2);
		input = input.substring(2);
	
		// 国家代号(2字节)
		this.mcc = input.substring(0, 2 * 2);
		input = input.substring(2 * 2);
		
		// 移动网号码(1字节)
		this.mnc = input.substring(0, 2);
		input = input.substring(2);
		
		// 位置区码号码(2字节)
		this.lac = input.substring(0, 2 * 2);
		input = input.substring(2 * 2);
		
		// 移动基站(3字节)
		this.cellID = input.substring(0, 2 * 3);
		input = input.substring(2 * 3);
	}
	
}
