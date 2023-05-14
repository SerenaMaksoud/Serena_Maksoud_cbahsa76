package com.example.Anywr.datahandlers.services;

import com.example.Anywr.datahandlers.models.Student;

import java.util.List;

public interface
StudentService {
    List<Student> getStudents(String className, String teacherFirstName, String teacherLastName, Integer pageSize, Integer pageNumber) throws Exception;
}
