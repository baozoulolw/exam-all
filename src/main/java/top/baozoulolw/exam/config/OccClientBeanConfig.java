package top.baozoulolw.exam.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OccClientBeanConfig {

    @Value("${aliyun.oss.end-point}")
    private String endpoint;

    @Value("${aliyun.oss.access-key}")
    private String accessKeyId;

    @Value("${aliyun.oss.secret-key}")
    private String accessKeySecret;


    @Bean
    public OSS oss() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
