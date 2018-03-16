package cn.bqmart.provider.service;

import cn.bqmart.api.test.TestService;
import cn.bqmart.domain.TbStudent;
import cn.bqmart.provider.dao.TestDao;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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

    private static final Logger logger = LoggerFactory.getLogger(TestServiceProvider.class);

    @Autowired
    private TestDao testDao;

    @Override
    public String sayHell(String name) {
        return "hello " + name;
    }

    @Override
    @Transactional
    public int addStudent(String name, Integer age) {

        logger.info("add student is start! name:{}, age:{}", name, age);

        return testDao.add(name, age);
    }

    @Override
    public TbStudent findStudent(Integer id) {

        logger.info("add student is start! id:{}", id);

        return testDao.findStudent(id);
    }
}
