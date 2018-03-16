package cn.bqmart.consumer.service;

import cn.bqmart.api.test.TestService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * TestConsumerService
 *
 * @author Lee
 * @date 2018/3/14
 * description:
 * Created by Lee on 2018/3/14.
 */
@Component
public class TestConsumerService {

    @Reference
    private TestService testService;


    public String sayHell(String name) {

        return testService.sayHell(name);
    }
}
