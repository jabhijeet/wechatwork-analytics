package com.example.wechatwork.cache;

import com.example.wechatwork.gateway.WechatWorkGateway;
import com.example.wechatwork.model.GetTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Mani Sharma on 25-06-2020.
 */
@Component
public class CacheInvoker {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheInvoker.class);

    private WechatWorkGateway wechatWorkGateway;

/*    private CacheManager cacheManager;*/


    @Autowired
    public CacheInvoker(WechatWorkGateway wechatWorkGateway) {
        this.wechatWorkGateway = wechatWorkGateway;
    }

    @EventListener
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        LOGGER.info("Loading token on start up.....");
        refreshToken();
    }

    //Move to constant
    @Scheduled(fixedRateString = "3600000")
    public void refreshToken() {
        LOGGER.info("Refreshing token, time: {}", LocalDateTime.now());
        final GetTokenResponse getTokenResponse = wechatWorkGateway.getAccessToken();
        LOGGER.info("Access token loaded.. {}", getTokenResponse.getAccess_token());

    }
}
