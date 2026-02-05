package com.dsantos;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DontPadController {

    private DontPadService dontPadService;

    public DontPadController(DontPadService dontPadService) {
        this.dontPadService = dontPadService;
    }

    @GetMapping
    public String getPage(HttpServletRequest httpServletRequest) {
        return dontPadService.getPage(httpServletRequest.getRequestURI());
    }
}
