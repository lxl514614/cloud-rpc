package cn.bqmart.provider.dao;

import cn.bqmart.domain.TbStudent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * TestDao
 *
 * @author Lee
 * @date 2018/3/15
 * description:
 * Created by Lee on 2018/3/15.
 */
@Mapper
public interface TestDao {

    @Select("select * from tb_student where id = #{id}")
    TbStudent findStudent(@Param("id") Integer id);

    @Insert("insert into tb_student (name,age) values (#{name}, #{age})")
    int add(@Param("name") String name, @Param("age") Integer age);
}
