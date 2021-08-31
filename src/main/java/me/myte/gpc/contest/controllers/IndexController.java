package me.myte.gpc.contest.controllers;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j
@RequestMapping
public class IndexController {

    @GetMapping
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Nothing here", HttpStatus.BAD_REQUEST);
    }
}
