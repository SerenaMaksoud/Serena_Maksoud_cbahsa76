//package com.example.Anywr.Test;
//
//import com.example.Anywr.datahandlers.models.Students;
//import com.example.Anywr.datahandlers.models.Teacher;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Test
//public class test {
//
////        List<Students> students = new ArrayList<>();
////        students.add(new Student(1, "John", "Doe", new Class(1, "Math", new Teacher(1, "Jane", "Smith"))));
////        students.add(new Student(2, "Jane", "Doe", new Class(1, "Math", new Teacher(1, "Jane", "Smith"))));
////
////        Page<Student> page = new PageImpl<>(students);
////
////        Mockito.when(studentRepository.findByClazzNameAndClazzTeacherLastName("Math", "Smith",
////                        PageRequest.of(0, 10)))
////                .thenReturn(page);
////
////        ResponseEntity<Page<Student>> response = userController.getStudents("Math", "Smith", 0, 10);
////
////        assertEquals(HttpStatus.OK, response.getStatusCode());
////        assertEquals(2, response.getBody().getSize());
////        assertEquals("John", response.getBody().getContent().get(0).getFirstName());
////        assertEquals("Jane", response.getBody().getContent().get(1).getFirstName());
////    }
//
//
