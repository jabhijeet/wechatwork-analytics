package com.example.wechatwork.service;

import com.example.wechatwork.model.GetUnassignedUserResponse;

import java.util.Map;

/**
 * Created by Mani Sharma on 25-06-2020.
 */
public interface CustomerService {

    public Integer getActiveCustomerCount();

    public Map getCustomerUserMapping();

    public GetUnassignedUserResponse getUnassignedUsers();

}
