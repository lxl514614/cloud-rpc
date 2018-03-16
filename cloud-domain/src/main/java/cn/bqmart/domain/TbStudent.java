package cn.bqmart.domain;

import java.io.Serializable;

/**
 * TbStudent
 *
 * @author Lee
 * @date 2018/3/15
 * description:
 * Created by Lee on 2018/3/15.
 */
public class TbStudent implements Serializable {

    private Integer id;

    private String name;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
