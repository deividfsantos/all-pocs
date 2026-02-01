package com.dsantos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DontPadController {

    @GetMapping
    public String getIndex() {
        return "Hello World";
    }
}
