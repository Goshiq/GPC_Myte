package me.myte.gpc.test.controllers;

import me.myte.gpc.test.models.Place;
import me.myte.gpc.test.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/place/show")
public class PlaceController {

    @Autowired
    private PlaceService service;

    @GetMapping
    public List<Place> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Place> findById(@PathVariable("id") String strId) {
        long id;

        try {
            id = Long.parseLong(strId);
        }
        catch (NumberFormatException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Place ans = service.findById(id);
        if (ans == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ans, HttpStatus.OK);
    }

}
