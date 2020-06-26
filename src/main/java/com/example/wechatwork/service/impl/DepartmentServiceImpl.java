package com.example.wechatwork.service.impl;

import com.example.wechatwork.gateway.WechatWorkGateway;
import com.example.wechatwork.model.BehaviourData;
import com.example.wechatwork.model.GetDepartmentResponse;
import com.example.wechatwork.model.GetUserBehaviourResponse;
import com.example.wechatwork.model.GetUsersWithCustomerContactPermResponse;
import com.example.wechatwork.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static java.util.Objects.nonNull;

/**
 * Created by Mani Sharma on 25-06-2020.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

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
    public GetUsersWithCustomerContactPermResponse getMembersList() {
        return wechatWorkGateway.getMembersList();
    }

    @Override
    public Map<LocalDate, BehaviourData> getStatisticsData() {
        Map<LocalDate, BehaviourData> behaviourDataMap = new TreeMap<>();
        GetUsersWithCustomerContactPermResponse response = wechatWorkGateway.getMembersList();
        if (response.getErrcode().equalsIgnoreCase("0") && nonNull(response.getFollow_user()) && response.getFollow_user().size() > 0) {
            GetUserBehaviourResponse getUserBehaviourResponse = wechatWorkGateway.getStatisticsData(response.getFollow_user());
            LOGGER.info("getUserBehaviourResponse: {}", getUserBehaviourResponse);
            if (nonNull(getUserBehaviourResponse) && getUserBehaviourResponse.getErrcode().equalsIgnoreCase("0") && nonNull(getUserBehaviourResponse.getBehavior_data()) && getUserBehaviourResponse.getBehavior_data().size() > 0) {
                for (BehaviourData behaviourData : getUserBehaviourResponse.getBehavior_data()) {
                    LocalDate date = LocalDateTime.ofEpochSecond(behaviourData.getStat_time(), 0, ZoneOffset.UTC).toLocalDate();
                    LOGGER.info("Date conversion date: {}, stat_time: {}", date, behaviourData.getStat_time());
                    behaviourDataMap.put(date, behaviourData);
                }
            } else {
                LOGGER.error("No behaviour data");
            }
        }
        return behaviourDataMap;
    }
}
