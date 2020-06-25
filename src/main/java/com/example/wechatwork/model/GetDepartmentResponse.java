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
@ToString
@NoArgsConstructor
public class GetDepartmentResponse {

    private String errcode;
    private String errmsg;
    private List<DepartmentDetails> department;
}

@Setter
@Getter
@ToString
@NoArgsConstructor
class DepartmentDetails {

    private Integer id;
    private String name;
    private String name_en;
    private String parentid;
    private Integer order;
}
