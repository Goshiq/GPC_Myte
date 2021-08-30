package me.myte.gpc.contest.controllers;

import lombok.extern.slf4j.Slf4j;
import me.myte.gpc.contest.models.Place;
import me.myte.gpc.contest.models.Request;
import me.myte.gpc.contest.services.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/search")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/show")
    public List<Request> showAll() {
        log.info("Showing all the requests from the base");
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
    public ResponseEntity<List<Place>> findRequest(@PathVariable("request") String request) {
        request = request.trim();
        log.info("Request to search: " + request);
        log.info("Saving the request to the base");
        requestService.add(new Request(request));

        List<Place> place;
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
        if (place != null && place.size() != 0) {
            return new ResponseEntity<>(place, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
