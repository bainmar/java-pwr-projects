package com.bartoszek.jtzrestapi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppControllerSpec {

    private AppController appController;
    @BeforeEach
    void init(){
        appController = new AppController(new AppView("API-client"));
    }

}