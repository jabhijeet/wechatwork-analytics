package com.example.wechatwork.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BehaviourData {
    private long stat_time; // Date
    private Integer chat_cnt;// Chats
    private int message_cnt; // Messages sent in a single chat
    private int reply_percentage; //Proportion of replies to chats, , excluding group chats
    private int avg_reply_time;//The average first reply time, in minutes, excluding group chats
    private int negative_feedback_cnt;
    private int new_apply_cnt; // New customer application, existing we chat customer
    private int new_contact_cnt; // New customer

}
