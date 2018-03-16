package cn.bqmart.consumer.config;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * DubboConsumerConfiguration
 *
 * @author Lee
 * @date 2018/3/14
 * description:
 * Created by Lee on 2018/3/14.
 */
@Configuration
@EnableDubboConfig
@PropertySource("classpath:application.properties")
@DubboComponentScan(basePackages = "cn.bqmart.consumer")
public class DubboConsumerConfiguration {
}
