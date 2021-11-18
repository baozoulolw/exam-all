package top.baozoulolw.webchat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.baozoulolw.exam.utils.RedisUtils;

import javax.annotation.Resource;

@SpringBootTest
class WebchatApplicationTests {

    @Resource
    private RedisUtils redisUtils;

    @Test
    void contextLoads() {
        // redisUtils.set("test","12345");
        System.out.println(redisUtils.get("test"));
    }

}
