package com.example.wechatwork.controller;

import com.example.wechatwork.model.GetUnassignedUserResponse;
import com.example.wechatwork.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Mani Sharma on 25-06-2020.
 */
@RestController
@RequestMapping(value = "api/v1/wechatwork/analytics", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**Total active customer */
    @GetMapping("getActiveCustomerCount")
    public ResponseEntity<Integer> getTotalCustomerCount() {
        final Integer custCount = customerService.getActiveCustomerCount();
        return new ResponseEntity<Integer>(custCount, HttpStatus.OK);
    }

    @GetMapping("getUnassignedUserCount")
    public ResponseEntity<Integer> getUnassignedUsers() {
        GetUnassignedUserResponse getUnassignedUserResponse = customerService.getUnassignedUsers();
        return new ResponseEntity<Integer>(getUnassignedUserResponse.getErrcode().equalsIgnoreCase("0") ? getUnassignedUserResponse.getInfo().size() : 0, HttpStatus.OK);
    }

    /**Count of customer per member **/
    @GetMapping("getCustomerUserMapping")
    public ResponseEntity<Map<String, Integer>> getCustomerUserMapping() {
        final Map<String, Integer> map = customerService.getCustomerUserMapping();
        return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
    }

}


