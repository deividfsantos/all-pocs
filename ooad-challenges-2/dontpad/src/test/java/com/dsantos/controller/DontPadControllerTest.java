package com.dsantos.controller;

import com.dsantos.service.DontPadService;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import jakarta.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DontPadControllerTest {

    @Test
    void getPageReturnsServiceValue() {
        DontPadService service = new DontPadService();
        service.postPage("/path/foo", "bar");
        DontPadController controller = new DontPadController(service);

        HttpServletRequest req = new MockHttpServletRequest("GET", "/path/foo");
        assertEquals("bar", controller.getPage(req));
    }

    @Test
    void postPageCallsServiceAndReturnsBody() {
        DontPadService service = new DontPadService();
        DontPadController controller = new DontPadController(service);

        HttpServletRequest req = new MockHttpServletRequest("POST", "/path/foo");
        String body = "content";
        assertEquals(body, controller.postPage(req, body));
        assertEquals(body, service.getPage("/path/foo"));
    }
}
