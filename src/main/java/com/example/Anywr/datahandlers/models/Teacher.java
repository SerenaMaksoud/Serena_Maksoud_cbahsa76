package com.example.Anywr.datahandlers.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Teacher {
    private Long id;
    private String first_name;
    private String last_name;
}
