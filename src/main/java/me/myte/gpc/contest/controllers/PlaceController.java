package me.myte.gpc.contest.controllers;

import lombok.extern.slf4j.Slf4j;
import me.myte.gpc.contest.models.Place;
import me.myte.gpc.contest.services.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/place/show")
public class PlaceController {

    private final PlaceService service;

    public PlaceController(PlaceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Place> findAll() {
        log.info("Requested all places from the base");
        List<Place> ans = service.findAll();
        log.info("FOUND: " + ans.size());
        return ans;
    }

    @GetMapping("{id}")
    public ResponseEntity<Place> findById(@PathVariable("id") String strId) {
        log.info("Requested place with id: " + strId);
        long id;

        try {
            id = Long.parseLong(strId);
        }
        catch (NumberFormatException e) {
            log.error("Wrong ID format: " + strId);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Place ans = service.findById(id);
        if (ans == null) {
            log.warn("No place with ID: " + strId);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        log.info("Place successfully found");
        return new ResponseEntity<>(ans, HttpStatus.OK);
    }

}
