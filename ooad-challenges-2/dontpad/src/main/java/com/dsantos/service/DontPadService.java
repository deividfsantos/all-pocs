package com.dsantos.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class DontPadService {
    private final HashMap<String, String> pages;

    public DontPadService() {
        this.pages = new HashMap<>();
    }

    public String getPage(String path) {
        return pages.get(path);
    }

    public void postPage(String requestURI, String body) {
        pages.put(requestURI, body);
    }
}
