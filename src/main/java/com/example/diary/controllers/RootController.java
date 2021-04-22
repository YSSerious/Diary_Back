package com.example.diary.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin
public class RootController {

    private static final Logger logger = LoggerFactory.getLogger(RootController.class);

    @GetMapping
    public String root() {
        logger.info("In RootController");
        return "RootController Public Content.";
    }
}
