package com.gizaSystem.service;

import com.gizaSystem.dto.EmployeeGetterDto;
import com.gizaSystem.dto.EmployeeSetterDto;
import com.gizaSystem.entity.Employee;
import com.gizaSystem.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    private final ValidationService validationService;

    ModelMapper modelMapper = new ModelMapper();

    public String sayHello() {
        return "hello from employee service";
    }

    public int createEmployee(EmployeeSetterDto employeeDto) {
log.info("create new employee service");
        validateEmployeeData(employeeDto,-1);

        Employee employee = new Employee();
        modelMapper.map(employeeDto,employee);
        employee = employeeRepo.save(employee);
        return employee.getId();
    }

    public EmployeeGetterDto getEmployee(int id) {
        log.info("Service request to get employee by id");

        EmployeeGetterDto employeeDto;
        Employee employee;

        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if(optionalEmployee.isPresent()) {
            log.info("Employee Found");
            employee = optionalEmployee.get();
            employeeDto = new EmployeeGetterDto();

            modelMapper.map(employee,employeeDto);

        } else {
            log.warn("No Employee with this id");
            throw new RuntimeException("Employee with id: "+id+" NOT FOUND!!!");
        }
        return employeeDto;
    }

    public int updateEmployee(EmployeeSetterDto employeeDto, int id) {
        validateEmployeeData(employeeDto,id);
        Employee employee;
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if(optionalEmployee.isPresent()) {
            employee = optionalEmployee.get();
            modelMapper.map(employeeDto,employee);

            employee = employeeRepo.save(employee);
        } else {
            throw new RuntimeException("Employee with id: "+id+" NOT FOUND!!!");
        }
        return employee.getId();
    }

    public void deleteEmployee(int id) {
        Employee employee;
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if(optionalEmployee.isPresent()) {
            employee = optionalEmployee.get();
            employeeRepo.delete(employee);
        } else {
            throw new RuntimeException("Employee with id: "+id+" NOT FOUND!!!");
        }
    }

    void validateEmployeeData(EmployeeSetterDto employeeDto, int id) {
        if(!validationService.isNameUnique(employeeDto.getName(),id)) {
            throw new RuntimeException("Employee name: " + employeeDto.getName()+" is duplicated!");
        }
        if(!validationService.isAgeValid(employeeDto.getAge())) {
            throw new RuntimeException("Employee age: "+ employeeDto.getAge()+" must be between 20 and 50!");
        }
        if(!validationService.isEmail(employeeDto.getEmail())) {
            throw new RuntimeException("Employee email:"+ employeeDto.getEmail()+" must be in email format!");
        }
        if(!validationService.isEmailUnique(employeeDto.getEmail(),id)) {
            throw new RuntimeException("Employee email:" + employeeDto.getEmail()+" is duplicated");
        }
    }

}
