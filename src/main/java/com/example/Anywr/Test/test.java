package com.example.Anywr.Test;

import com.example.Anywr.datahandlers.mappers.StudentMapper;
import com.example.Anywr.datahandlers.mappers.UserMapper;
import com.example.Anywr.datahandlers.models.Class;
import com.example.Anywr.datahandlers.models.Student;
import com.example.Anywr.datahandlers.models.Teacher;
import com.example.Anywr.datahandlers.models.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UserMapper userMapper;

    // this is for creating testing data
    @Test
    public void Test() {
        // create teachers, classes and students for testing the getStudents API
        Teacher teacher1 = new Teacher(10L, "Jane", "Doe");
        Teacher teacher2 = new Teacher(20L, "John", "Doe");

        Class class1 = new Class(10L, "Maths", 10L);
        Class class2 = new Class(20L, "Science", 20L);

        Student student1 = new Student(10L, "James", "St", 10L);
        Student student2 = new Student(20L, "Jina", "St", 10L);
        Student student3 = new Student(30L, "Maria", "St", 20L);

        studentMapper.saveTeacher(teacher1);
        studentMapper.saveTeacher(teacher2);
        studentMapper.saveClass(class1);
        studentMapper.saveClass(class2);
        studentMapper.saveStudent(student1);
        studentMapper.saveStudent(student2);
        studentMapper.saveStudent(student3);

        // saved user to test the auth API
        User user1 = new User("Jane", "Doe");
        userMapper.saveUser(user1);

        // this username can be tested for successful authentication and authorization
    }
}


