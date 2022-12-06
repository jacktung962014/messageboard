package com.example.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseBody;



@Controller

public class SampleController{

    @GetMapping(value = "/")

    @ResponseBody

    public String index() {

        return "<h1>Hi!</h1>";

    }
    
    @GetMapping(value = "/aa")

    @ResponseBody

    public String indexw() {

        return "<h1>AAA!</h1>";

    }

}