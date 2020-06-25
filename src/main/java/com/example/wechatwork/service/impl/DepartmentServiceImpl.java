package com.example.wechatwork.service.impl;

import com.example.wechatwork.gateway.WechatWorkGateway;
import com.example.wechatwork.model.GetDepartmentResponse;
import com.example.wechatwork.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
