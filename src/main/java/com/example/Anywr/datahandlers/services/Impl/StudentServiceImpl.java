package com.example.Anywr.datahandlers.services.Impl;

import com.example.Anywr.datahandlers.mappers.StudentMapper;
import com.example.Anywr.datahandlers.models.Student;
import com.example.Anywr.datahandlers.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<Student> getStudents(String className, String teacherFirstName, String teacherLastName, Integer pageSize, Integer pageNumber) throws Exception {
        return studentMapper.getStudents(className, teacherFirstName, teacherLastName, pageNumber, pageSize);
    }
}
