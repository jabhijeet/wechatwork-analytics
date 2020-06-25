package com.example.wechatwork.service.impl;

import com.example.wechatwork.gateway.WechatWorkGateway;
import com.example.wechatwork.model.GetCustomerByUserIdResponse;
import com.example.wechatwork.service.CustomerService;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    public Integer getTotalCustomerCount() {
        Integer count = 0;
        //Get the list of userIds from API
        for (String userId : ImmutableList.of("RichardScott")) {
            final GetCustomerByUserIdResponse response = wechatWorkGateway.getCustomerListByUserId(userId);
            //Exception handling not done
            if (response.getErrcode().equalsIgnoreCase("0")) {
                count = count + response.getExternal_userid().size();
            }
        }
        return count;
    }

    @Override
    public Map getTotalCustomerByUser() {

        Map<String, Integer> map = new HashMap<>();
        //Get the list of userIds from API
        for (String userId : ImmutableList.of("RichardScott")) {
            final GetCustomerByUserIdResponse response = wechatWorkGateway.getCustomerListByUserId(userId);
            //Exception handling not done
            if (response.getErrcode().equalsIgnoreCase("0")) {
                map.put(userId, response.getExternal_userid().size());
            }
        }
        return map;
    }
}
