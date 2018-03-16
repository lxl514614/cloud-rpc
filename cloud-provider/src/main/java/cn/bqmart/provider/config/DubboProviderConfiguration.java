package cn.bqmart.provider.config;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * DubboProviderConfiguration
 *
 * @author Lee
 * @date 2018/3/14
 * description:
 * <p>@EnableDubboConfig</p>
 *      基于规约自动装配相应的Dubbo配置类
 *      当规约的外部配置存在时, 相应的dubbo配置类才会提升为spring bean. 按需装配
 *  注意: 如果@configuration注解下的class中配置了相应的bean, 会与配置中的相应配置冲突,运行会出现异常
 * Created by Lee on 2018/3/14.
 */
@Configuration
@EnableDubboConfig
@PropertySource("classpath:application.properties")
@DubboComponentScan(basePackages = "cn.bqmart.provider.service")
public class DubboProviderConfiguration {

//    @Bean("dubbo-annotation-provider")
//    public ApplicationConfig applicationConfig() {
//
//        ApplicationConfig applicationConfig = new ApplicationConfig();
//        applicationConfig.setName("provider-test");
//
//        return applicationConfig;
//    }
//
//    @Bean("my-registry")
//    public RegistryConfig registryConfig() {
//
//        RegistryConfig registryConfig = new RegistryConfig();
//        registryConfig.setAddress("zookeeper://127.0.0.1:2181");
////        registryConfig.setClient("curator");
//        registryConfig.setClient("zkclient");
//
//        return registryConfig;
//
//    }
//
//    @Bean
//    public ProviderConfig providerConfig() {
//        ProviderConfig providerConfig = new ProviderConfig();
//        providerConfig.setTimeout(5000);
//        return providerConfig;
//    }


}
