package com.example.Anywr.datahandlers.models.Responses;

import com.example.Anywr.datahandlers.models.Student;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@ResponseBody
public class StudentsResponse {
    private List<Student> students;
    private int studentsNumber;;
    private Integer status;

    @Builder(builderMethodName = "GetStudentsBuilder")
    public StudentsResponse(Integer status, List<Student> students) {
        this.students = students;
        this.studentsNumber = students.size();
        this.status = status;
    }
}
