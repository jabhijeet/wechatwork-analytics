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
public class GetUserBehaviourResquest {
    private List<String> userid;
    private List<String> partyid;
    private long start_time;
    private long end_time;
}
