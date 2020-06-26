package com.example.wechatwork.service;

import com.example.wechatwork.model.GetUnassignedCustomerResponse;

import java.util.Map;

/**
 * Created by Mani Sharma on 25-06-2020.
 */
public interface CustomerDetailsService {

    public Integer getActiveCustomerCount();

    public Map getCustomerUserMapping();

    public GetUnassignedCustomerResponse getUnassignedCustomer();

}
