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
public class GetUserBehaviourResponse {
    private String errcode;
    private String errmsg;
    private List<BehaviourData> behavior_data;


}
