package com.example.norainapp.controller;

import com.example.norainapp.view.ViewFactory;

public abstract class BaseController {
    protected final ViewFactory viewFactory;
    private final String fxmlName;

    public BaseController(ViewFactory viewFactory, String fxmlName) {
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
