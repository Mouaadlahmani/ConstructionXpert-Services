package com.mouad.gatway.feign;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "utilisateurs", url = "http://localhost:8084/api/auth")
public interface AuthInterface {

    @GetMapping("/validate")
    String validateToken(@RequestParam("token") String token );
}