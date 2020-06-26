package com.example.wechatwork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetUsersWithCustomerContactPermResponse {

    private String errcode;
    private String errmsg;
    private List<String> follow_user;
}
