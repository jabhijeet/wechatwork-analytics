package com.example.wechatwork.service.impl;

import com.example.wechatwork.gateway.WechatWorkGateway;
import com.example.wechatwork.model.GetCustomerByUserIdResponse;
import com.example.wechatwork.model.GetUnassignedCustomerResponse;
import com.example.wechatwork.model.GetUsersWithCustomerContactPermResponse;
import com.example.wechatwork.service.CustomerDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Mani Sharma on 25-06-2020.
 */

@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDetailsServiceImpl.class);

    private WechatWorkGateway wechatWorkGateway;

    @Autowired
    public CustomerDetailsServiceImpl(WechatWorkGateway wechatWorkGateway) {
        this.wechatWorkGateway = wechatWorkGateway;
    }

    @Override
    public Integer getActiveCustomerCount() {
        Integer count = 0;
        LOGGER.debug("Calling API to get membersList...");
        //Get the list of userIds from API
        GetUsersWithCustomerContactPermResponse response = wechatWorkGateway.getMembersList();
        if (response.getErrcode().equalsIgnoreCase("0")) {
            //Get the list of userIds from API
            for (String userId : response.getFollow_user()) {
                final GetCustomerByUserIdResponse customerListByUserId = wechatWorkGateway.getCustomerListByUserId(userId);
                //Exception handling not done
                if (response.getErrcode().equalsIgnoreCase("0")) {
                    count = count + (Objects.isNull(customerListByUserId.getExternal_userid()) ? 0 : customerListByUserId.getExternal_userid().size());
                } else {
                    logError("Customer List", customerListByUserId.getErrcode(), customerListByUserId.getErrmsg());
                }
            }
        } else {
            logError("Members list", response.getErrcode(), response.getErrmsg());
        }

        return count;
    }

    @Override
    public Map getCustomerUserMapping() {

        Map<String, Integer> map = new HashMap<>();
        //Get the list of userIds from API
        GetUsersWithCustomerContactPermResponse response = wechatWorkGateway.getMembersList();
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
    public GetUnassignedCustomerResponse getUnassignedCustomer() {
        return wechatWorkGateway.getUnassignedUsers();
    }

    private void logError(final String api, final String errorCode, final String errorMsg) {
        LOGGER.error("Error occurred while getting {}, errorCode: {}, errorMessage: {}", api, errorCode, errorMsg);
    }
}
