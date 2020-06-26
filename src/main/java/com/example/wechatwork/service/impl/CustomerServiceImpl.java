package com.example.wechatwork.service.impl;

import com.example.wechatwork.gateway.WechatWorkGateway;
import com.example.wechatwork.model.GetCustomerByUserIdResponse;
import com.example.wechatwork.model.GetUnassignedUserResponse;
import com.example.wechatwork.model.GetUsersWithCustomerContactPermResponse;
import com.example.wechatwork.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Mani Sharma on 25-06-2020.
 */

@Service
public class CustomerServiceImpl implements CustomerService {

    private WechatWorkGateway wechatWorkGateway;

    @Autowired
    public CustomerServiceImpl(WechatWorkGateway wechatWorkGateway) {
        this.wechatWorkGateway = wechatWorkGateway;
    }

    @Override
    public Integer getActiveCustomerCount() {
        Integer count = 0;

        //Get the list of userIds from API
        GetUsersWithCustomerContactPermResponse response = wechatWorkGateway.getUsersWithCustomerContactPerm();
        if (response.getErrcode().equalsIgnoreCase("0")) {
            //Get the list of userIds from API
            for (String userId : response.getFollow_user()) {
                final GetCustomerByUserIdResponse customerListByUserId = wechatWorkGateway.getCustomerListByUserId(userId);
                //Exception handling not done
                if (response.getErrcode().equalsIgnoreCase("0")) {
                    count = count + (Objects.isNull(customerListByUserId.getExternal_userid()) ? 0 : customerListByUserId.getExternal_userid().size());
                }
            }
        }

        return count;
    }

    @Override
    public Map getCustomerUserMapping() {

        Map<String, Integer> map = new HashMap<>();
        //Get the list of userIds from API
        GetUsersWithCustomerContactPermResponse response = wechatWorkGateway.getUsersWithCustomerContactPerm();
        if (response.getErrcode().equalsIgnoreCase("0")) {
            for (String userId : response.getFollow_user()) {
                final GetCustomerByUserIdResponse customerListByUserId = wechatWorkGateway.getCustomerListByUserId(userId);
                //Exception handling not done
                if (response.getErrcode().equalsIgnoreCase("0")) {
                    map.put(userId, Objects.isNull(customerListByUserId.getExternal_userid()) ? 0 : customerListByUserId.getExternal_userid().size());
                }
            }
        }
        return map;
    }

    @Override
    public GetUnassignedUserResponse getUnassignedUsers() {
        return wechatWorkGateway.getUnassignedUsers();
    }
}
