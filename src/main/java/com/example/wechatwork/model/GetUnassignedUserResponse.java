package com.example.wechatwork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetUnassignedUserResponse {
    private String errcode;
    private String errmsg;
    private List<UnassignedUserDetails> info;
    private boolean is_last; // Ignoring this parameter for now in logic
}

@Getter
@Setter
@NoArgsConstructor
class UnassignedUserDetails {
    private String handover_userid;
    private String external_userid;
    private long dimission_time;

}
