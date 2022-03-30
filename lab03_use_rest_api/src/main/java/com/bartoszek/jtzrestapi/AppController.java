package com.bartoszek.jtzrestapi;

import java.util.ResourceBundle;

public class AppController {
    private ResourceBundle resourceBundle;
    private AppView appView;
    public AppController(AppView appView) {
        this.appView = appView;
    }

    public void initController(){
        resourceBundle = ResourceBundle.getBundle("QuestionOne");
    }

}