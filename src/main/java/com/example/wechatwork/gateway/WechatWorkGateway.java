package com.example.wechatwork.gateway;

import com.example.wechatwork.config.WechatWorkConfig;
import com.example.wechatwork.model.*;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
public class WechatWorkGateway {
    @Autowired
    private WechatWorkConfig config;

    @Cacheable
    public GetTokenResponse getAccessToken() {
        return getWebClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/cgi-bin/gettoken")
                        .queryParam("corpid", config.getCorpid())
                        .queryParam("corpsecret", config.getCorpsecret())
                        .build())
                .retrieve()
                .bodyToMono(GetTokenResponse.class)
                .block();
    }

    public GetUsersWithCustomerContactPermResponse getUsersWithCustomerContactPerm() {
        return getWebClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/cgi-bin/externalcontact/get_follow_user_list")
                        .queryParam("access_token", getAccessToken().getAccess_token())
                        .build())
                .retrieve()
                .bodyToMono(GetUsersWithCustomerContactPermResponse.class)
                .block();
    }

    public GetUserBehaviourResponse getBehaviourDataForAllUser(final List<String> userid) {
        val body = new HashMap<String, Object>();
        body.put("userid", "PatrickSiu");
        //body.put("partyid", 1);
        body.put("start_time", 1592533086);
        body.put("end_time", 1593137886);

        //GetUserBehaviourResquest getUserBehaviourResquest = new GetUserBehaviourResquest();

        WebClient.ResponseSpec response;
        response = getWebClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/cgi-bin/externalcontact/get_user_behavior_data")
                        .queryParam("access_token", getAccessToken().getAccess_token())
                        .build(body))
                .retrieve();

        return response.bodyToMono(GetUserBehaviourResponse.class).block();
    }

    public GetDepartmentResponse getDepartments() {
        return getWebClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/cgi-bin/department/list")
                        .queryParam("access_token", getAccessToken().getAccess_token())
                        .build())
                .retrieve()
                .bodyToMono(GetDepartmentResponse.class)
                .block();
    }

    public GetCustomerByUserIdResponse getCustomerListByUserId(final String userId) {
        return getWebClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/cgi-bin/externalcontact/list")
                        .queryParam("access_token", getAccessToken().getAccess_token())
                        .queryParam("userid", userId)
                        .build())
                .retrieve()
                .bodyToMono(GetCustomerByUserIdResponse.class)
                .block();
    }


    public String getCallbackIp(String accessToken) {

        WebClient.ResponseSpec response;
        response = getWebClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/cgi-bin/getcallbackip")
                        .queryParam("access_token", accessToken)
                        .build())
                .retrieve();

        return response.bodyToMono(String.class).block();
    }

    public String send(String accessToken, String touser, String agentid, String message) {
        val body = new HashMap<String, Object>();
        body.put("ToUser", touser);
        body.put("MsgType", "text");
        body.put("AgentID", agentid);
        val content = new HashMap<String, String>();
        content.put("Content", message);
        body.put("text", content);

        WebClient.ResponseSpec response;
        response = getWebClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/cgi-bin/message/send")
                        .queryParam("access_token", accessToken)
                        .build(body))
                .retrieve();

        return response.bodyToMono(String.class).block();
    }

    private WebClient getWebClient() {
        return WebClient
                .builder()
                .baseUrl("https://qyapi.weixin.qq.com/")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
