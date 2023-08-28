package com.gizaSystem.controller;

import com.gizaSystem.dto.EmployeeGetterDto;
import com.gizaSystem.dto.EmployeeSetterDto;
import com.gizaSystem.service.EmployeeService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Log4j2
public class EmployeeController {

    private final EmployeeService employeeService;

    private final ObservationRegistry observationRegistry;

//    @Timed(value = "post_employee.time", description = "Time taken to add new employee")
    @PostMapping
    public int addEmployee(@RequestBody EmployeeSetterDto employeeDto) {
        log.info("creation request started");
        Observation observation = Observation.start("employee.create", observationRegistry);
        try (var ignored = observation.openScope()) {
            observation
                .contextualName("create new employee request (Observation API)") // 2
                .lowCardinalityKeyValue("employee.name", employeeDto.getName()) // 3
                .lowCardinalityKeyValue("employee.email", employeeDto.getEmail())
                .lowCardinalityKeyValue("employee.salary", employeeDto.getSalary() + "") // 4
                .event(Observation.Event.of("create new employee event"));

                    return employeeService.createEmployee(employeeDto);

} finally {
    Observation.Context context = observation.getContext();

    observation.event(Observation.Event.of("end employee creation"));

    log.info("context name " + context.getName());
    log.info("span name " + context.getContextualName());
    log.info("tracing for request create new employee:");
            log.info(context.getLowCardinalityKeyValue("employee.name"));
            log.info(context.getLowCardinalityKeyValue("employee.email"));
            log.info(context.getLowCardinalityKeyValue("employee.salary"));
    observation.stop();
}


    }

    @Observed(
            name = "employee.retrieve",
            contextualName = "get employee by id request observed annotation",
            lowCardinalityKeyValues = {"employee.id", "number"}
    )
    @GetMapping("/{id}")
    public EmployeeGetterDto getEmployee(@PathVariable("id") int id) {
        log.info("API request to get employee by id");
        return employeeService.getEmployee(id);
    }

    @PutMapping("/{id}")
    public int updateEmployee(@RequestBody EmployeeSetterDto employeeDto, @PathVariable("id") int id) {
        return employeeService.updateEmployee(employeeDto,id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
    }
}
