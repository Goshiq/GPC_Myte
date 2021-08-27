package me.myte.gpc.test.controllers;

import lombok.extern.slf4j.Slf4j;
import me.myte.gpc.test.models.Place;
import me.myte.gpc.test.models.Request;
import me.myte.gpc.test.services.PlaceService;
import me.myte.gpc.test.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/search")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private PlaceService placeService;

    @GetMapping("/show")
    public List<Request> showAll() {
        log.info("Searching all the requests from the base");
        List<Request> ans = requestService.findAll();
        log.info("FOUND: " + ans.size());
        return ans;
    }

    @GetMapping("/show/{id}")
    private ResponseEntity<Request> showRequest(@PathVariable("id") String strId) {
        strId = strId.trim();
        log.info("Searching the request with ID: " + strId);
        long id;

        try {
            id = Long.parseLong(strId);
        }
        catch (NumberFormatException e) {
            log.error("Wrong ID format: " + strId);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Request ans = requestService.findById(id);
        if (ans == null) {
            log.warn("No request with ID: " + strId);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        log.info("SUCCESS");
        return new ResponseEntity<>(ans, HttpStatus.OK);
    }

    @GetMapping("/new/{request}")
    public Place findByName(@PathVariable("request") String request) {
        request = request.trim();
        log.info("Request to search: " + request);
        log.info("Saving the request to the base");
        requestService.add(new Request(request));

        Place place;
        String[] array = request.split(" ");
        if (array.length == 0) {
            log.error("No input request");
            return null;
        }
        else if (array.length == 2) {
            try {
                Double[] ans = new Double[] {Double.parseDouble(array[0]), Double.parseDouble(array[1])};
                log.info("Searching by coordinates: " + array[0] + " " + array[1]);
                place = requestService.findByCoordinates(ans);
            }
            catch (NumberFormatException e) {
                log.info("Searching by the address: " + request);
                place = requestService.findByString(request);
            }
        }
        else {
            log.info("Searching by the address: " + request);
            place = requestService.findByString(request);
        }
        return place;
    }
}
