package com.gizaSystem.service;


import com.gizaSystem.entity.Employee;
import com.gizaSystem.repo.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
public class ValidationServiceTest {

    @Mock
    EmployeeRepo employeeRepoMock;

   @Spy
    @InjectMocks
    ValidationService validationService;

    @Test
    void email_is_in_email_format() {
//        Arrange
        String email = "name@example.com";

//        Act
        boolean actual = validationService.isEmail(email);

//        Assert
        assertTrue(actual);
    }

    @Test
    void email_is_not_in_email_format() {
//        Arrange
        String email = "name.example.com";

//        Act
        boolean actual = validationService.isEmail(email);

//        Assert
        assertFalse(actual);
    }

    @Test
    void age_is_between_20_and_50() {
//        Arrange
        int age = 35;

//        Act
        boolean actual = validationService.isAgeValid(age);

//        Assert
        assertTrue(actual);
    }

    @Test
    void age_is_not_between_20_and_50() {

//        Act and Assert

        assertAll(
                () -> assertFalse(validationService.isAgeValid(15)),
                () -> assertFalse(validationService.isAgeValid(60))
        );
    }

    @Test
    void name_is_unique_employee_null() {

        doReturn(5).when(validationService).sum(1,2);
//        when(validationService.sum(1,2)).thenReturn(5);
        doReturn(null).when(employeeRepoMock).findByNameIgnoreCase(anyString());

        assertTrue(validationService.isNameUnique("marwa",-1));

    }

    @Test
    void name_is_unique_employee_not_null() {

        Employee employee = new Employee(10,"ali","ali@gg",25,20000);

        doReturn(employee).when(employeeRepoMock).findByNameIgnoreCase(anyString());

        assertTrue(validationService.isNameUnique("ali",10));

//        assertEquals(true,validationService.isNameUnique("marwa",10));

    }

    @Test
    void name_is_duplicated() {
        Employee employee = new Employee(10,"ali","ali@gg",25,20000);

        doReturn(employee).when(employeeRepoMock).findByNameIgnoreCase(anyString());

        assertFalse(validationService.isNameUnique("ali",11));
    }

    @Test
    void email_is_unique_employee_null() {

        doReturn(null).when(employeeRepoMock).findByEmail(anyString());

        assertTrue(validationService.isEmailUnique("marwa@gmail.com",-1));

    }

    @Test
    void email_is_unique_employee_not_null() {

        Employee employee = new Employee(10,"ali","ali@gg",25,20000);

        doReturn(employee).when(employeeRepoMock).findByEmail(anyString());

        assertTrue(validationService.isEmailUnique("ali@gg",10));

    }

    @Test
    void email_is_duplicated() {
        Employee employee = new Employee(10,"ali","ali@gg",25,20000);

        doReturn(employee).when(employeeRepoMock).findByEmail(anyString());

        assertFalse(validationService.isEmailUnique("ali@gg",11));
    }

    @Test
    void sum_test() {

        assertDoesNotThrow(()->validationService.sum(1,2));
    }

}
