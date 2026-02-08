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
        String large = "x".repeat(10000);

        service.postPage("/large", large);
        assertEquals(large, service.getPage("/large"));
    }

    @Test
    void deepNestedSubpathsAreStoredAndRetrieved() {
        StringBuilder base = new StringBuilder();
        // create and store values for /a, /a/b, /a/b/c, ... up to depth 10
        for (int depth = 1; depth <= 10; depth++) {
            base.append(depth == 1 ? "/a" : "/b");
            String key = String.join("", java.util.Collections.nCopies(depth, "/seg")) + "/leaf" + depth;
            service.postPage(key, "val" + depth);
            assertEquals("val" + depth, service.getPage(key));
        }
    }

    @Test
    void similarPrefixesDoNotCollide() {
        service.postPage("/app", "root-app");
        service.postPage("/application", "application");
        service.postPage("/app/settings", "app-settings");

        assertEquals("root-app", service.getPage("/app"));
        assertEquals("application", service.getPage("/application"));
        assertEquals("app-settings", service.getPage("/app/settings"));
    }

    @Test
    void repeatedSlashesAndNormalizationNotApplied() {
        // The service should treat paths literally (no normalization)
        service.postPage("/foo//bar", "double-slash");
        service.postPage("/foo/bar", "single-slash");

        assertEquals("double-slash", service.getPage("/foo//bar"));
        assertEquals("single-slash", service.getPage("/foo/bar"));
    }

    @Test
    void longPathKeyIsSupported() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 200; i++) sb.append("/segment").append(i);
        String longKey = sb.toString();
        service.postPage(longKey, "long-value");
        assertEquals("long-value", service.getPage(longKey));
    }

    @Test
    void pathsWithSpecialCharactersAreAllowed() {
        String p1 = "/caminho/íñç/测试";
        String p2 = "/caminho/%20espaco";
        service.postPage(p1, "unicode");
        service.postPage(p2, "encoded");

        assertEquals("unicode", service.getPage(p1));
        assertEquals("encoded", service.getPage(p2));
    }
}
