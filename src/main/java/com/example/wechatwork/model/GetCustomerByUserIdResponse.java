package com.example.wechatwork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by Mani Sharma on 25-06-2020.
 */

@Setter
@Getter
@NoArgsConstructor
@ToString
public class GetCustomerByUserIdResponse {

    private String errcode;
    private String errmsg;
    private List<String> external_userid;
}
