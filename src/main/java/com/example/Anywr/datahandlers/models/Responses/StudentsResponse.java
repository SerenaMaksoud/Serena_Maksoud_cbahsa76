package com.example.Anywr.datahandlers.models.Responses;

import com.example.Anywr.datahandlers.models.Students;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@ResponseBody
public class StudentsResponse {
    private List<Students> students;
    private int studentsNumber;;
    private Integer status;

    @Builder(builderMethodName = "GetStudentsBuilder")
    public StudentsResponse(Integer status, List<Students> students) {
        this.students = students;
        this.studentsNumber = students.size();
        this.status = status;
    }
}
