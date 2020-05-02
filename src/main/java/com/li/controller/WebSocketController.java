package com.li.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebSocketController {

    @GetMapping("/web-socket")
    public String webSocketTest(){

        return "web-socket";
    }

}
