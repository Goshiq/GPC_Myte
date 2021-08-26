package me.myte.gpc.test.controllers;

import me.myte.gpc.test.models.Place;
import me.myte.gpc.test.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private PlaceService service;

    @GetMapping
    public List<Place> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Place findById(@PathVariable("id") long id) {
        return service.findById(id);
    }

}
