package com.gizaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSetterDto {

    String name;
    String email;
    int age;
    double salary;
}
