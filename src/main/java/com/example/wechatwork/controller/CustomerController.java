package com.example.wechatwork.controller;

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

    @GetMapping("getTotalCustomerCount")
    public ResponseEntity<Integer> getTotalCustomerCount() {
        final Integer custCount = customerService.getTotalCustomerCount();
        return new ResponseEntity<Integer>(custCount, HttpStatus.OK);
    }

    @GetMapping("getTotalCustomerByUser")
    public ResponseEntity<Map<String, Integer>> getTotalCustomerByUser() {
        final Map<String, Integer> map = customerService.getTotalCustomerByUser();
        return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
    }

}


