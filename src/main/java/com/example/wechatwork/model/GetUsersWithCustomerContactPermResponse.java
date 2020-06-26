package com.example.wechatwork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GetUsersWithCustomerContactPermResponse {

    private String errcode;
    private String errmsg;
    private List<String> follow_user;
}
