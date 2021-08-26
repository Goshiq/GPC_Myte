package me.myte.gpc.test.controllers;

import me.myte.gpc.test.models.Place;
import me.myte.gpc.test.models.Request;
import me.myte.gpc.test.services.PlaceService;
import me.myte.gpc.test.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private PlaceService placeService;

    @GetMapping
    public List<Request> index() {
        return requestService.findAll();
    }

    @GetMapping("/search/{request}")
    public List<Request> findByName(@PathVariable("request") String request) {
        requestService.add(new Request(request));
        Place place = placeService.findByString(request);
        return requestService.findAll();
    }
}
