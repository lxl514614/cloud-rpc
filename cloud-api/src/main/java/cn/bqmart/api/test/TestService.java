package cn.bqmart.api.test;

import cn.bqmart.domain.TbStudent;

/**
 * TestService
 *
 * @author Lee
 * @date 2018/3/14
 * description:
 * Created by Lee on 2018/3/14.
 */
public interface TestService {

    public String sayHell(String name);

    public int addStudent (String name, Integer age);

    public TbStudent findStudent(Integer id);


}
