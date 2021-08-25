package me.myte.gpc.test.models;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Request {

    @GetMapping
    public String index() {
        return "index";
    }
}
