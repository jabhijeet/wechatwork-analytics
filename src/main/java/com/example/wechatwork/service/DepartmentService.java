package com.example.wechatwork.service;

import com.example.wechatwork.model.GetDepartmentResponse;
import com.example.wechatwork.model.GetUserBehaviourResponse;
import com.example.wechatwork.model.GetUsersWithCustomerContactPermResponse;

/**
 * Created by Mani Sharma on 25-06-2020.
 */
public interface DepartmentService {

    public GetDepartmentResponse getDepartments();
    public GetUsersWithCustomerContactPermResponse getUsersWithCustomerContactPerm();
    public GetUserBehaviourResponse getBehaviourDataForAllUser();
}
