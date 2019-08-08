package cn.hehej.serversocket.domain;

import cn.hehej.serversocket.util.GpsDataUtil;

import java.io.Serializable;

/**
 * GPS数据解析
 * @author  xiaoyu
 *
 */
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
	private String MCC;
	/**移动网号码*/
	private String MNC;
	/**位置区码*/
	private String LAC;
	/**移动基站*/
	private String CellID;
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
	 * 获取日期
	 * @return
	 */
	public String getDate() {
		return date;
	}
	/**
	 * 设置日期
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * 获取卫星星数
	 * @return
	 */
	public String getSatelliteNumber() {
		return satelliteNumber;
	}
	/**
	 * 设置卫星星数
	 * @param satelliteNumber
	 */
	public void setSatelliteNumber(String satelliteNumber) {
		this.satelliteNumber = satelliteNumber;
	}
	/**
	 * 获取纬度
	 * @return
	 */
	public Double getLatitude() {
		return latitude;
	}
	/**
	 * 设置纬度
	 * @param latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	/**
	 * 获取经度
	 * @return
	 */
	public Double getLongitude() {
		return longitude;
	}
	/**
	 * 设置经度
	 * @param longitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	/**
	 * 获取速度
	 * @return
	 */
	public Double getSpeed() {
		return speed;
	}
	/**
	 * 设置速度
	 * @param speed
	 */
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	/**
	 * 获取GPS状态
	 * @return
	 */
	public String getGpsState() {
		return gpsState;
	}
	/**
	 * 设置GPS状态
	 * @param gpsState
	 */
	public void setGpsState(String gpsState) {
		this.gpsState = gpsState;
	}
	/**
	 * 获取航向
	 * @return
	 */
	public Integer getCourse() {
		return course;
	}
	/**
	 * 设置航向
	 * @param course
	 */
	public void setCourse(Integer course) {
		this.course = course;
	}
	/**
	 * 获取国家代号
	 * @return
	 */
	public String getMCC() {
		return MCC;
	}
	/**
	 * 设置国家代号
	 * @param mCC
	 */
	public void setMCC(String mCC) {
		MCC = mCC;
	}
	/**
	 * 获取移动网号码
	 * @return
	 */
	public String getMNC() {
		return MNC;
	}
	/**
	 * 设置移动网号码
	 * @param mNC
	 */
	public void setMNC(String mNC) {
		MNC = mNC;
	}
	/**
	 * 获取位置区码
	 * @return
	 */
	public String getLAC() {
		return LAC;
	}
	/**
	 * 设置位置区码
	 * @param lAC
	 */
	public void setLAC(String lAC) {
		LAC = lAC;
	}
	/**
	 * 获取移动基站
	 * @return
	 */
	public String getCellID() {
		return CellID;
	}
	/**
	 * 设置移动基站
	 * @param cellID
	 */
	public void setCellID(String cellID) {
		CellID = cellID;
	}
	/**
	 * 获取原始数据
	 * @return
	 */
	public String getOriginalData() {
		return originalData;
	}
	/**
	 * 获取路线名
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置路线名
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取路线ID
	 * @return
	 */
	public Integer getRoadId() {
		return roadId;
	}
	/**
	 * 设置路线ID
	 * @param roadId
	 */
	public void setRoadId(Integer roadId) {
		this.roadId = roadId;
	}
	/**
	 * 获取服务人员ID
	 * @return
	 */
	public Integer getWorkId() {
		return workId;
	}
	/**
	 * 设置服务人员ID
	 * @param workId
	 */
	public void setWorkId(Integer workId) {
		this.workId = workId;
	}
	/**
	 * 获取设备imei
	 * @return
	 */
	public String getImei() {
		return imei;
	}
	/**
	 * 设置设备imei
	 * @param imei
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}
	/**
	 * 设置原始数据
	 * @param originalData
	 */
	public void setOriginalData(String originalData) {
		this.originalData = originalData;
	}
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
        originalData = input;
		/**路线名*/
		name = thisName;
		/**路线ID*/
		roadId = thisRoadId;
		/**服务人员ID*/
		workId = thisWorkId;
		/**设备imei*/
		imei = thisImei;
		// 日期(6字节)
		date = GpsDataUtil.parseGpsHexDate2Unix(input.substring(0, 2 * 6));
		input = input.substring(2 * 6);
		// 卫星星数(1字节)
		satelliteNumber = input.substring(0, 2 * 1);
		input = input.substring(2 * 1);
		// 纬度(4字节)
		latitude = GpsDataUtil.parseHexStr2Location(input.substring(0, 2 * 4));
		input = input.substring(2 * 4);
		// 经度(4字节)
		longitude = GpsDataUtil.parseHexStr2Location(input.substring(0, 2 * 4));
		input = input.substring(2 * 4);
		// 速度(1字节)
		speed = Double.valueOf(Integer.parseInt(input.substring(0, 2 * 1),16));
		input = input.substring(2 * 1);
		
		// GPS状态(1字节)
		String state = GpsDataUtil.bytes2BinaryStr(GpsDataUtil.parseHexStr2Byte(input.substring(0, 2 * 1)));
		gpsState = state;
		input = input.substring(2 * 1);
		
		//航向(1字节)   航向会挪用状态里面2个bit
		String cover = state.substring(state.length()-2);
		String courseString = cover+GpsDataUtil.bytes2BinaryStr((GpsDataUtil.parseHexStr2Byte(input.substring(0, 2 * 1))));
		course = Integer.parseInt(courseString,2);
		input = input.substring(2 * 1);
	
		// 国家代号(2字节)
		MCC = input.substring(0, 2 * 2);
		input = input.substring(2 * 2);
		
		// 移动网号码(1字节)
		MNC = input.substring(0, 2 * 1);
		input = input.substring(2 * 1);
		
		// 位置区码号码(2字节)
		LAC = input.substring(0, 2 * 2);
		input = input.substring(2 * 2);
		
		// 移动基站(3字节)
		CellID = input.substring(0, 2 * 3);
		input = input.substring(2 * 3);
	}
	
}
