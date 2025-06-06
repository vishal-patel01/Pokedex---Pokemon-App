package com.lise.infotech.Lise_InfoTech.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HealthCheck {
    @GetMapping("/health-check")
public String healthCheck(){
    return "ok";
}
}
