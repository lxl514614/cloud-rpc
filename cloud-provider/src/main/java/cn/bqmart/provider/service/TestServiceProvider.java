package cn.bqmart.provider.service;

import cn.bqmart.api.test.TestService;
import cn.bqmart.domain.TbStudent;
import cn.bqmart.provider.dao.TestDao;
import cn.bqmart.util.cache.RedisUtil;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * TestServiceProvider
 *
 * @author Lee
 * @date 2018/3/14
 * description:
 * Created by Lee on 2018/3/14.
 */
@Service
public class TestServiceProvider implements TestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceProvider.class);

    @Autowired
    private TestDao testDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String sayHell(String name) {
        return "hello " + name;
    }

    @Override
    @Transactional
    public int addStudent(String name, Integer age) {

        LOGGER.info("add student is start! name:{}, age:{}", name, age);

        boolean hasKey = redisTemplate.hasKey("test");
        if (!hasKey) {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set("test", name+"_"+age, 10, TimeUnit.SECONDS);
            LOGGER.info("TestServiceProvider.addStudent() : 学生信息插入缓存 >> name:{}, age:{}" ,name, age );
        }
        return testDao.add(name, age);
    }

    @Override
    public TbStudent findStudent(Integer id) {

        LOGGER.info("find student is start! id:{}", id);
        ValueOperations<String, TbStudent> operations = redisTemplate.opsForValue();

        TbStudent student = new TbStudent();

//        RedisUtil.set("redisTest", "this is test");

//        LOGGER.info("set redis==>");

        boolean hasKey = redisTemplate.hasKey("test"+id);
        if (!hasKey) {
            student = testDao.findStudent(id);
            operations.set("test"+id, student, 10, TimeUnit.SECONDS);
            LOGGER.info("TestServiceProvider.addStudent() : 学生信息插入缓存 >> student:{}", student.toString() );
        }
        else {
            student = operations.get("test"+id);
            LOGGER.info("TestServiceProvider.addStudent() : 获取学生信息缓存 >> student:{}", student.toString() );
        }
//        String redisRes = (String) RedisUtil.get("redisTest");
//        LOGGER.info("get redis==>{}", redisRes);
        return student;
    }
}
