package com.dsantos.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DontPadServiceTest {
    private DontPadService service;

    @BeforeEach
    void setUp() {
        service = new DontPadService();
    }

    @Test
    void getPageReturnsNullIfNotExists() {
        assertNull(service.getPage("/foo"));
    }

    @Test
    void postPageAndGetPage() {
        service.postPage("/foo", "bar");
        assertEquals("bar", service.getPage("/foo"));
    }
}
