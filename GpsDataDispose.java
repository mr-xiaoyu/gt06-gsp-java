package cn.hehej.serversocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.apache.commons.lang.StringUtils;

import cn.hehej.common.util.CodeUtil;
import cn.hehej.common.util.DateUtil;
import cn.hehej.serversocket.domain.GpsDataInputDTO;
import cn.hehej.serversocket.domain.Gt06InputDTO;
import cn.hehej.serversocket.util.Byte2Hex;
import cn.hehej.serversocket.util.Crc16Util;
import cn.hehej.serversocket.util.CloseUtil;
import cn.hehej.serversocket.util.ProtocolUtil;
import cn.hehej.system.domain.Road;
import lombok.extern.slf4j.Slf4j;

/**
 * GSP数据处理
 * @author  xiaoyu
 *
 */
@Slf4j
public class GpsDataDispose extends Thread {
	
	
	/**
	 * 输入流
	 */
	private DataInputStream dis;
	/**
	 * 输出流
	 */
	private DataOutputStream dos;
	private Boolean flag = true;
	/**
	 * 路线名
	 */
	private String name = null;
	/**
	 * 路线ID
	 */
	private Integer roadId = null;
	/**
	 * 服务人员ID
	 */
	private Integer workId = null;
	/**
	 * 设备imei码
	 */
	private String imei = null;

	public GpsDataDispose(Socket socket){
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			flag = false;
			CloseUtil.closeAll(dis,dos);
			GpsService.GPS_DATA_DISPOSE_LIST.remove(this);
		}
		
	}

	@Override
	public void run() {
		while (flag) {
			String hex = readFromClient();
			if(StringUtils.isBlank(hex)){
				flag = false;
				CloseUtil.closeAll(dis,dos);
				GpsService.GPS_DATA_DISPOSE_LIST.remove(this);
				break;
			}

            String start = hex.substring(0, 2 * 2);
			if(!"7878".equals(start)){
				flag = false;
				CloseUtil.closeAll(dis,dos);
				GpsService.GPS_DATA_DISPOSE_LIST.remove(this);
				break;
			}

			Gt06InputDTO dto = new Gt06InputDTO(hex);
			// 协议号
			String protocolNumber = dto.getProtocolNumber();
			// 消息内容
			String content = dto.getContent();
			// 消息序列号
			String seriesNumber = dto.getSeriesNumber();

			String hexstr = String.format("%02x", dto.getLength()).toUpperCase()+ protocolNumber+ content+ seriesNumber;
			Crc16Util crc16 = new Crc16Util();
			crc16.reset();
			crc16.update(Byte2Hex.hexString2Bytes(hexstr));
			String crc = Integer.toHexString(crc16.getCrcValue()).toUpperCase();
			if (dto.getCrc().equals(crc)) {
				if (Gt06InputDTO.PROTOCOL_NUMBER_LOGIN_INFORMATION.equals(protocolNumber)) {
					Road road = CodeUtil.findRoadByTerminalId(content);
					if(road != null){
						this.name = road.getName();
						this.roadId = road.getRoadId();
						this.workId = road.getWorkId();
						this.imei = content;
						String resultString = ProtocolUtil.serverResponseData(protocolNumber, seriesNumber);
						sendToClient(resultString);
					}
				}else if (Gt06InputDTO.PROTOCOL_NUMBER_GPSANDSTATE_INFORMATION.equals(protocolNumber)) {
					log.info("报警信息：{},name：{}", content,this.name);
					String resultString = ProtocolUtil.serverResponseData(protocolNumber, seriesNumber);
					sendToClient(resultString);
				}else if (Gt06InputDTO.PROTOCOL_NUMBER_STATE_INFORMATION.equals(protocolNumber)) {
					String resultString = ProtocolUtil.serverResponseData(protocolNumber, seriesNumber);
					sendToClient(resultString);
					GpsDataInputDTO gpsDataInputDTO = CodeUtil.getGpsDataFormCache(imei);
					if(gpsDataInputDTO != null && gpsDataInputDTO.getLatitude() != null && gpsDataInputDTO.getLongitude() != null){
						gpsDataInputDTO.setDate(DateUtil.getCurrentTimeMillis());
						try {
							CodeUtil.gpsEquipmentAddPoint(gpsDataInputDTO);
						} catch (Exception e) {
							log.error("GPS轨迹点上传失败",e);
						}
					}
					
				}else if (Gt06InputDTO.PROTOCOL_NUMBER_ICCID.equals(protocolNumber)) {
					log.info("ICCID信息：{},name：{}", content,this.name);
				}else if (Gt06InputDTO.PROTOCOL_NUMBER_GPS_INFORMATION.equals(protocolNumber)) {
					GpsDataInputDTO gpsDataInputDTO = new GpsDataInputDTO(content,name,roadId,workId,imei);
					if(gpsDataInputDTO.getLatitude() != null && gpsDataInputDTO.getLongitude() != null){
						try {
							CodeUtil.gpsEquipmentAddPoint(gpsDataInputDTO);
						} catch (Exception e) {
							log.error("GPS轨迹点上传失败",e);
						}
					}
				}else{
					log.info("未识别信息：{},name：{}", content,this.name);
				}
			}else{
				log.error("服务器效验CRC不通过，停止此socket服务");
				flag = false;
				CloseUtil.closeAll(dis,dos);
				GpsService.GPS_DATA_DISPOSE_LIST.remove(this);
				break;
			}
		}
	}

	/**
	 * 定义读取客户端数据的方法
	 * @return
	 */
	private String readFromClient() {
		String hex = "";
		try {
			byte[] bytes = new byte[1024];
			int size;
			size = dis.read(bytes);
			hex = Byte2Hex.bytesToHex(bytes, 0, size);
			return hex;
		} catch (IOException e) {
			log.error("服务器接收客户端数据失败",e);
			flag = false;
			CloseUtil.closeAll(dis,dos);
			GpsService.GPS_DATA_DISPOSE_LIST.remove(this);
		}
		return hex;
	}


	/**
	 * 定义发送数据给客户端的方法
	 * @param resultString
	 */
	private void sendToClient(String resultString) {
		if(StringUtils.isNotBlank(resultString)){
			try {
				dos.write(Byte2Hex.hexString2Bytes(resultString));
				dos.flush();
			} catch (IOException e) {
				log.error("服务器发送数据给客户端失败",e);
				flag = false;
				CloseUtil.closeAll(dis,dos);
				GpsService.GPS_DATA_DISPOSE_LIST.remove(this);
			}
		}
	}
}
