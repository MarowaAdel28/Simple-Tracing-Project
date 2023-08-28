package com.gizaSystem.service;

import com.gizaSystem.dto.EmployeeGetterDto;
import com.gizaSystem.dto.EmployeeSetterDto;
import com.gizaSystem.entity.Employee;
import com.gizaSystem.repo.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepo employeeRepoMock;

    @Mock
    ValidationService validationServiceMock;

    @Spy
    @InjectMocks
    EmployeeService employeeService;

    @Test
    void get_employee_successfully() {
        // Arrange
        Employee employee = new Employee(1, "marwa", "marwa@gmail.com", 25, 20_000);

        doReturn(Optional.of(employee)).when(employeeRepoMock).findById(anyInt());

        // Act
        EmployeeGetterDto employeeDtoPredicted = employeeService.getEmployee(1);

        // Assert
        assertNotNull(employeeDtoPredicted);
        assertEquals(employee.getId(), employeeDtoPredicted.getId());
        assertEquals(employee.getName(), employeeDtoPredicted.getName());
        assertEquals(employee.getEmail(), employeeDtoPredicted.getEmail());
        assertEquals(employee.getAge(), employeeDtoPredicted.getAge());
        assertEquals(employee.getSalary(), employeeDtoPredicted.getSalary());
    }

    @Test
    void throw_exception_employee_not_found() {

        doReturn(Optional.empty()).when(employeeRepoMock).findById(anyInt());

        assertThrows(RuntimeException.class, () -> employeeService.getEmployee(1), "Employee with id: 1 NOT FOUND!!!");
    }

    @Test
    void create_new_employee_successfully() {
        // arrange

        Employee employee = new Employee(1);

        EmployeeSetterDto employeeSetterDto = new EmployeeSetterDto();

        // when(employeeRepoMock.save(any(Employee.class))).thenReturn(employee);
        doNothing().when(employeeService).validateEmployeeData(employeeSetterDto, -1);

        doReturn(employee).when(employeeRepoMock).save(any(Employee.class));

        assertEquals(employeeService.createEmployee(employeeSetterDto), 1);
    }

    @Test
    void update_employee_successfully() {

        Employee employee = new Employee(1);

        EmployeeSetterDto employeeSetterDto = new EmployeeSetterDto("marwa", "null", 0, 0);

        doReturn(Optional.of(employee)).when(employeeRepoMock).findById(anyInt());

        // when(employeeRepoMock.save(any(Employee.class))).thenReturn(employee);

        doReturn(employee).when(employeeRepoMock).save(any(Employee.class));

        doNothing().when(employeeService).validateEmployeeData(employeeSetterDto, 1);

        assertEquals(employeeService.updateEmployee(employeeSetterDto, 1), 1);
    }

    @Test
    void throw_exception_when_update_not_existing_employee() {

//        arrange
        EmployeeSetterDto employeeDto =
                new EmployeeSetterDto("marwa", "null", 0, 0);

        doNothing().when(employeeService).validateEmployeeData(
                any(EmployeeSetterDto.class), anyInt());

        doReturn(Optional.empty()).when(employeeRepoMock).findById(anyInt());

        assertThrows(RuntimeException.class, () ->
                        employeeService.updateEmployee(employeeDto, 1)
                 ,"Employee with id: 1 NOT FOUND!!!");

    }

    @Test
    void delete_employee_successfully() {

        // Arrange
        Employee employee = new Employee(1, "marwa", "marwa@gmail.com", 25, 20_000);

        doReturn(Optional.of(employee)).when(employeeRepoMock).findById(anyInt());

        // Act
        // employeeService.deleteEmployee(1);

        // Assert
        // verify(employeeRepoMock, times(1)).delete(employee);
        // does not throw
        assertDoesNotThrow(() -> employeeService.deleteEmployee(1));
    }

    @Test
    void throw_exception_when_delete_not_existing_employee() {

        doReturn(Optional.empty()).when(employeeRepoMock).findById(anyInt());

        assertThrows(RuntimeException.class, () -> employeeService.deleteEmployee(1),
                "Employee with id: 1 NOT FOUND!!!");
    }

    @Test
    void validateEmployeeData_throw_exception_name_not_unique() {

        doReturn(false).when(validationServiceMock).isNameUnique(anyString(), anyInt());

        assertThrows(RuntimeException.class, () -> 
        employeeService.validateEmployeeData(new EmployeeSetterDto("marwa", "null", 0, 0), 0));
    }

    @Test
    void validateEmployeeData_throw_exception_age_not_valid() {
        doReturn(true).when(validationServiceMock).isNameUnique(anyString(), anyInt());

        doReturn(false).when(validationServiceMock).isAgeValid(anyInt());

        assertThrows(RuntimeException.class, () -> employeeService.validateEmployeeData(
            new EmployeeSetterDto("marwa", "null", 0, 0), 0));
    }

    @Test
    void validateEmployeeData_throw_exception_email_not_valid() {
        doReturn(true).when(validationServiceMock).isNameUnique(anyString(), anyInt());

        doReturn(true).when(validationServiceMock).isAgeValid(anyInt());

        doReturn(false).when(validationServiceMock).isEmail(anyString());

        assertThrows(RuntimeException.class, () -> employeeService.validateEmployeeData(
            new EmployeeSetterDto("marwa", "null", 0, 0) ,0));
    }

    @Test
    void validateEmployeeData_throw_exception_email_not_unique() {
        doReturn(true).when(validationServiceMock).isNameUnique(anyString(), anyInt());

        doReturn(true).when(validationServiceMock).isAgeValid(anyInt());

        doReturn(true).when(validationServiceMock).isEmail(anyString());

        doReturn(false).when(validationServiceMock).isEmailUnique(anyString(), anyInt());

        assertThrows(RuntimeException.class, () -> employeeService.validateEmployeeData(
            new EmployeeSetterDto("marwa", "null", 0, 0), 0));
    }

    @Test
    void validateEmployeeData_successfully() {

    doReturn(true).when(validationServiceMock).isNameUnique(anyString(),
    anyInt());

    doReturn(true).when(validationServiceMock).isAgeValid(anyInt());

    doReturn(true).when(validationServiceMock).isEmail(anyString());

    doReturn(true).when(validationServiceMock).isEmailUnique(anyString(),anyInt());
    // employeeService.validateEmployeeData(
    //     new EmployeeSetterDto(),0);

    assertDoesNotThrow(()->employeeService.validateEmployeeData(
        // new EmployeeSetterDto(),0));
         new EmployeeSetterDto("marwa", "null", 0, 0), 0));

    // verify(null)
    }

}
