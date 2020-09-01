package com.chanchhaya.jasperreport.repository;

import com.chanchhaya.jasperreport.model.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    @Select("SELECT * FROM users")
    @Results(value = {
            @Result(column = "user_id", property = "userId"),
            @Result(column = "first_name", property = "firstName"),
            @Result(column = "last_name", property = "lastName")
    })
    List<User> findAll();

}
