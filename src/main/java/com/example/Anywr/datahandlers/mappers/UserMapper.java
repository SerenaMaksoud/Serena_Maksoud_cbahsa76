package com.example.Anywr.datahandlers.mappers;

import com.example.Anywr.datahandlers.models.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user where username=#{username} and password=#{password} order by id desc limit 1")
    User getUser(String username, String password);
//
//    @Select("SELECT * FROM user where username=#{username} order by id desc limit 1")
//    User getUserByUsername(String username);

    @Insert("INSERT INTO user (`username`, `password`) VALUES (#{username} ,#{password} )")
    void saveUser(User user);
}
