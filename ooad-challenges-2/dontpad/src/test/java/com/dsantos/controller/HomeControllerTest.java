package com.dsantos.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeControllerTest {

    @Test
    void anyPathReturnsForwardToIndex() {
        HomeController controller = new HomeController();
        assertEquals("forward:/index.html", controller.anyPath());
    }
}
