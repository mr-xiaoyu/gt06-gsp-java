package club.mrxiao.gps.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 百度地图配置
 * @author xiaoyu
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "gps.baidu-map")
public class BaiduMapProperties {

    private String ak;

    private Long traceServiceId;
}
