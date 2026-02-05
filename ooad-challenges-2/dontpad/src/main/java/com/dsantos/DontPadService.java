package com.dsantos;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class DontPadService {
    private HashMap<String, String> pages;

    public String getPage(String path) {
        return pages.get(path);
    }
}
