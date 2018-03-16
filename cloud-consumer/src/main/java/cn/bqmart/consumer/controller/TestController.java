package cn.bqmart.consumer.controller;

import cn.bqmart.api.test.TestService;
import cn.bqmart.consumer.service.TestConsumerService;
import cn.bqmart.domain.TbStudent;
import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * TestController
 *
 * @author Lee
 * @date 2018/3/14
 * description:
 * Created by Lee on 2018/3/14.
 */
@RestController
@RequestMapping (value = "/test")
public class TestController {


    @Reference
    private TestService testService;

    @Autowired
    private TestConsumerService testConsumerService;

    @RequestMapping (value = "/hello/{name}")
    public String sayHell(@PathVariable String name) {

//        String result = testService.sayHell(name);
        String result = testConsumerService.sayHell(name);

        return result;
    }

    @RequestMapping (value = "add/student/{name}/{age}")
    public String addStudent(@PathVariable String name, @PathVariable Integer age) {



        testService.addStudent(name,age);

        return "success";
    }

    @RequestMapping (value = "student/{id}")
    public TbStudent getStudent(@PathVariable Integer id) {

        TbStudent student = testService.findStudent(id);

        return student;
    }
}
