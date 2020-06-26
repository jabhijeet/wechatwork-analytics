package com.example.wechatwork.controller;

import com.example.wechatwork.model.GetUnassignedCustomerResponse;
import com.example.wechatwork.service.CustomerDetailsService;
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
public class CustomerDetailsController {

    private CustomerDetailsService customerDetailsService;

    @Autowired
    public CustomerDetailsController(CustomerDetailsService customerDetailsService) {
        this.customerDetailsService = customerDetailsService;
    }

    /**
     * Get count of active customer
     * @return
     */
    @GetMapping("getActiveCustomerCount")
    public ResponseEntity<Integer> getTotalCustomerCount() {
        final Integer custCount = customerDetailsService.getActiveCustomerCount();
        return new ResponseEntity<Integer>(custCount, HttpStatus.OK);
    }

    /**
     * Get count of unassigned customer
     * @return
     */
    @GetMapping("getUnassignedCustomerCount")
    public ResponseEntity<Integer> getUnassignedCustomerCount() {
        GetUnassignedCustomerResponse getUnassignedCustomerResponse = customerDetailsService.getUnassignedCustomer();
        return new ResponseEntity<Integer>(getUnassignedCustomerResponse.getErrcode().equalsIgnoreCase("0") ? getUnassignedCustomerResponse.getInfo().size() : 0, HttpStatus.OK);
    }

    /**
     * Count of customer per member
     **/
    @GetMapping("getCustomerUserMapping")
    public ResponseEntity<Map<String, Integer>> getCustomerUserMapping() {
        final Map<String, Integer> map = customerDetailsService.getCustomerUserMapping();
        return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
    }

}


