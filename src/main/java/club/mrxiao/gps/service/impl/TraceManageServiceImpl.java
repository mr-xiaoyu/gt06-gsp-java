package club.mrxiao.gps.service.impl;

import club.mrxiao.baidu.api.BaiduTraceService;
import club.mrxiao.baidu.request.BaiduTraceEntityRequest;
import club.mrxiao.gps.service.TraceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 轨迹管理服务相关接口实现
 * @author xiaoyu
 */
@Service
public class TraceManageServiceImpl implements TraceManageService {

    private final BaiduTraceService baiduTraceService;

    @Autowired
    public TraceManageServiceImpl(BaiduTraceService baiduTraceService) {
        this.baiduTraceService = baiduTraceService;
    }

    @Override
    public void creationBaiduEntity(BaiduTraceEntityRequest baiduTraceEntityRequest) {
        baiduTraceService.getEntityService().entityAdd(baiduTraceEntityRequest);
    }
}
