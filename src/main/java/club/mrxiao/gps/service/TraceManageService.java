package club.mrxiao.gps.service;

import club.mrxiao.baidu.request.BaiduTraceEntityRequest;

/**
 * 轨迹管理服务相关接口
 * @author xiaoyu
 */
public interface TraceManageService {

    /**
     * 创建百度鹰眼终端对象
     * @param baiduTraceEntityRequest
     */
    void creationBaiduEntity(BaiduTraceEntityRequest baiduTraceEntityRequest);
}
