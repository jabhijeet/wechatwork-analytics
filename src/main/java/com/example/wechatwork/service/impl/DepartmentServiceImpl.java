package com.example.wechatwork.service.impl;

import com.example.wechatwork.gateway.WechatWorkGateway;
import com.example.wechatwork.model.GetDepartmentResponse;
import com.example.wechatwork.model.GetUserBehaviourResponse;
import com.example.wechatwork.model.GetUsersWithCustomerContactPermResponse;
import com.example.wechatwork.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

/**
 * Created by Mani Sharma on 25-06-2020.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private WechatWorkGateway wechatWorkGateway;

    @Autowired
    public DepartmentServiceImpl(WechatWorkGateway wechatWorkGateway) {
        this.wechatWorkGateway = wechatWorkGateway;
    }

    @Override
    public GetDepartmentResponse getDepartments() {
        return wechatWorkGateway.getDepartments();
    }

    @Override
    public GetUsersWithCustomerContactPermResponse getUsersWithCustomerContactPerm() {
        return wechatWorkGateway.getUsersWithCustomerContactPerm();
    }

    @Override
    public GetUserBehaviourResponse getBehaviourDataForAllUser() {
        GetUsersWithCustomerContactPermResponse response = wechatWorkGateway.getUsersWithCustomerContactPerm();
        if(response.getErrcode().equalsIgnoreCase("0") && Objects.nonNull(response.getFollow_user()) && response.getFollow_user().size()>0){
            System.out.println(response.getFollow_user());
            System.out.println(response.getFollow_user().size());
            return wechatWorkGateway.getBehaviourDataForAllUser(response.getFollow_user());
        }
        return null;
    }
}
