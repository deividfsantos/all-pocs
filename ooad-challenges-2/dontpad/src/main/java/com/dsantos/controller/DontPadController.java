package com.dsantos.controller;

import com.dsantos.service.DontPadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DontPadController {

    private final DontPadService dontPadService;

    public DontPadController(DontPadService dontPadService) {
        this.dontPadService = dontPadService;
    }

    @GetMapping("/path/**")
    public String getPage(HttpServletRequest httpServletRequest) {
        return dontPadService.getPage(httpServletRequest.getRequestURI());
    }

    @PostMapping("/path/**")
    public String postPage(HttpServletRequest httpServletRequest, @RequestBody String body) {
        dontPadService.postPage(httpServletRequest.getRequestURI(), body);
        return body;
    }
}
