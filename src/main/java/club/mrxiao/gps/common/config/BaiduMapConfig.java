package club.mrxiao.gps.common.config;

import club.mrxiao.baidu.api.BaiduTraceService;
import club.mrxiao.baidu.api.impl.BaiduTraceServiceImpl;
import club.mrxiao.baidu.config.BaiduTraceConfig;
import club.mrxiao.gps.common.properties.BaiduMapProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 百度鹰眼配置
 * @author xiaoyu
 */

@Component
public class BaiduMapConfig {

    private final BaiduMapProperties baiduTraceConfig;

    @Autowired
    public BaiduMapConfig(BaiduMapProperties baiduTraceConfig) {
        this.baiduTraceConfig = baiduTraceConfig;
    }

    @Bean
    public BaiduTraceService baiduTraceService(){
        BaiduTraceService baiduTraceService = new BaiduTraceServiceImpl();
        BaiduTraceConfig config = new BaiduTraceConfig(baiduTraceConfig.getAk(),baiduTraceConfig.getTraceServiceId());
        baiduTraceService.setBaiduTraceConfig(config);
        return baiduTraceService;
    }
}
