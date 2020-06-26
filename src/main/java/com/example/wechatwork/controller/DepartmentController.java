package com.example.wechatwork.controller;

import com.example.wechatwork.model.BehaviourData;
import com.example.wechatwork.model.GetDepartmentResponse;
import com.example.wechatwork.model.GetUserBehaviourResponse;
import com.example.wechatwork.model.GetUsersWithCustomerContactPermResponse;
import com.example.wechatwork.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

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

    /**
     * Total Number of Departments
     **/
    @GetMapping("getDepartments")
    public ResponseEntity<GetDepartmentResponse> getDepartments() {
        final GetDepartmentResponse getDepartmentResponse = departmentService.getDepartments();
        return new ResponseEntity<GetDepartmentResponse>(getDepartmentResponse, HttpStatus.OK);
    }

    /**
     * List of employee with Customer contact permission
     **/
    @GetMapping("getMembersList")
    public ResponseEntity<GetUsersWithCustomerContactPermResponse> getMembersList() {
        final GetUsersWithCustomerContactPermResponse response = departmentService.getMembersList();
        return new ResponseEntity<GetUsersWithCustomerContactPermResponse>(response, HttpStatus.OK);
    }

    /**
     * Get statistics data for all users and departments
     **/
    @GetMapping("getStatisticsData")
    public ResponseEntity<Map<LocalDate, BehaviourData>> getStatisticsData() {
        final Map<LocalDate, BehaviourData> behaviourDataMap = departmentService.getStatisticsData();
        return new ResponseEntity<Map<LocalDate, BehaviourData>>(behaviourDataMap, HttpStatus.OK);
    }

}
