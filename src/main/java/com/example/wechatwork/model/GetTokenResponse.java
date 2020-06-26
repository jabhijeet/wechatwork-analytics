package com.example.wechatwork.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GetTokenResponse {
    private String errcode;
    private String errmsg;
    private String access_token;
    private String expires_in;
}
