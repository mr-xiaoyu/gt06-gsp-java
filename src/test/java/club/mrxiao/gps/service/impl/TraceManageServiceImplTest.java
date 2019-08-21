package club.mrxiao.gps.service.impl;

import club.mrxiao.baidu.request.BaiduTraceEntityRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TraceManageServiceImplTest {

    @Autowired
    private TraceManageServiceImpl traceManageService;

    @Test
    public void creationBaiduEntity() {
        BaiduTraceEntityRequest request = new BaiduTraceEntityRequest();
        request.entityName("test").entityDesc("test001");
        traceManageService.creationBaiduEntity(request);
    }
}