package com.example.Anywr.datahandlers.mappers;

import com.example.Anywr.datahandlers.models.Students;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("<script>"+
            "SELECT * FROM student " +
            " where 1=1 " +
            "<if test='teacherFirstName!=null'>"+
            " and class_id = (select id from class where teacher_id=(select id from teacher where first_name=#{teacherFirstName}))" +
            "</if>"+
            "<if test='teacherLastName!=null'>"+
            " and class_id = (select id from class where teacher_id=(select id from teacher where last_name=#{teacherLastName}))" +
            "</if>"+
            "<if test='className!=null'>"+
            " and class_id = (select id from class where name=#{className})" +
            "</if>"+
            " order by id desc " +
            " limit #{pageNumber},#{pageSize} "+
            "</script>")
    List<Students> getStudents(String className, String teacherFirstName, String teacherLastName, Integer pageSize, Integer pageNumber);

    }
