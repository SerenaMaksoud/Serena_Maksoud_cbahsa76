package com.example.Anywr.datahandlers.mappers;

import com.example.Anywr.datahandlers.models.Student;
import com.example.Anywr.datahandlers.models.Teacher;
import com.example.Anywr.datahandlers.models.Class;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("<script>" +
            "SELECT * FROM student " +
            " where 1=1 " +
            "<if test='teacherFirstName!=null'>" +
            " and class_id = (select id from class where teacher_id=(select id from teacher where first_name=#{teacherFirstName}))" +
            "</if>" +
            "<if test='teacherLastName!=null'>" +
            " and class_id = (select id from class where teacher_id=(select id from teacher where last_name=#{teacherLastName}))" +
            "</if>" +
            "<if test='className!=null'>" +
            " and class_id = (select id from class where name=#{className})" +
            "</if>" +
            " order by id desc " +
            " limit #{pageNumber},#{pageSize} " +
            "</script>")
    List<Student> getStudents(String className, String teacherFirstName, String teacherLastName, Integer pageSize, Integer pageNumber);

    @Insert("INSERT INTO student (`id`, `first_name`, `last_name`, `class_id`) VALUES (#{id}, #{first_name} ,#{last_name}, #{class_id} )")
    void saveStudent(Student s);

    @Insert("INSERT INTO teacher (`id`, `first_name`, `last_name`) VALUES (#{id}, #{first_name} ,#{last_name})")
    void saveTeacher(Teacher t);

    @Insert("INSERT INTO class (`id`, `name`, `teacher_id`) VALUES (#{id}, #{name} ,#{teacher_id})")
    void saveClass(Class c);
}
