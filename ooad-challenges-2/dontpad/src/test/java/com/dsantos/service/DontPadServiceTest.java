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

    @Test
    void multiplePathsAreIndependent() {
        service.postPage("/a", "A");
        service.postPage("/a/b", "A/B");
        service.postPage("/b", "B");

        assertEquals("A", service.getPage("/a"));
        assertEquals("A/B", service.getPage("/a/b"));
        assertEquals("B", service.getPage("/b"));
    }

    @Test
    void overwritePathStoresLatestValue() {
        service.postPage("/foo", "first");
        assertEquals("first", service.getPage("/foo"));
        service.postPage("/foo", "second");
        assertEquals("second", service.getPage("/foo"));
    }

    @Test
    void trailingSlashIsDifferentPath() {
        service.postPage("/foo", "no-slash");
        service.postPage("/foo/", "with-slash");

        assertEquals("no-slash", service.getPage("/foo"));
        assertEquals("with-slash", service.getPage("/foo/"));
    }

    @Test
    void emptyPathKeyIsAllowed() {
        service.postPage("", "empty");
        assertEquals("empty", service.getPage(""));
    }

    @Test
    void nullPathKeyIsAllowed() {
        service.postPage(null, "null-key");
        assertEquals("null-key", service.getPage(null));
    }

    @Test
    void largeBodyIsStoredAndRetrieved() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) sb.append('x');
        String large = sb.toString();

        service.postPage("/large", large);
        assertEquals(large, service.getPage("/large"));
    }
}
