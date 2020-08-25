package com.zhicall;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${test.userTitle}")
    private String userTitle;

    @Value("${test.userName}")
    private String userName;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello " + userTitle + " " + userName + "!";
    }
}