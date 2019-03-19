package com.ueboot.starter.backend.controller;

import com.ueboot.core.http.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangkui
 * createTime:2019/3/1911:48 AM
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping("/submitApplication")
    public Response<Void> submitApplication(){

        return new Response<>();
    }
}
