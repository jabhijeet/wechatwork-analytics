package com.example.wechatwork.controller;

import com.example.wechatwork.model.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {
    @RequestMapping("/greeting")
    public Message greeting(@RequestParam(value = "name") String name) {
        System.out.println(name);
        return new Message(name);
    }
}