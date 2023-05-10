package com.example.Anywr.datahandlers.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Class {
    private Long id;
    private String name;
    private Long teacher_id;
}
