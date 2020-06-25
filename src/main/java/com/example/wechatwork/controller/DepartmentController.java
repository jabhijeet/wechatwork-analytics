package com.example.wechatwork.controller;

import com.example.wechatwork.model.GetDepartmentResponse;
import com.example.wechatwork.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mani Sharma on 25-06-2020.
 */

@RestController
@RequestMapping(value = "api/v1/wechatwork/analytics", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("getDepartments")
    public ResponseEntity<GetDepartmentResponse> getDepartments() {
        final GetDepartmentResponse getDepartmentResponse = departmentService.getDepartments();
        return new ResponseEntity<GetDepartmentResponse>(getDepartmentResponse, HttpStatus.OK);
    }

}
