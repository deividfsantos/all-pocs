package com.dsantos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DontPadController {

    @GetMapping
    public String getIndex() {
        return "Hello World";
    }
}
