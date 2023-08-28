package com.gizaSystem.controller;

import com.gizaSystem.service.EmployeeService;
import com.gizaSystem.service.ProductService;
import com.gizaSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/micrometer")
@RequiredArgsConstructor
public class MicrometerController {

    private final EmployeeService employeeService;

    private final UserService userService;

    private final ProductService productService;

    @GetMapping("/hello/all")
    public String helloFromAll() {

        return employeeService.sayHello() + "\n" +
                userService.sayHello() + "\n" +
                productService.sayHello();
    }
}
