package com.dsantos;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/{path:[^\\.]*}/**")
    public String getIndex() {
        return "forward:/index.html";
    }
}
