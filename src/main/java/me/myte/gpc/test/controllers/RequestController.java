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
    public Place findByName(@PathVariable("request") String request) {
        Place place;
        requestService.add(new Request(request));
        String[] array = request.split(" ");
        if (array.length == 0) {
            return null;
        }
        else if (array.length == 2) {
            try {
                Double[] ans = new Double[] {Double.parseDouble(array[0]), Double.parseDouble(array[1])};
                place = placeService.findByCoordinates(ans);
            }
            catch (NumberFormatException e) {
                place = placeService.findByString(request);
            }
        }
        else {
            place = placeService.findByString(request);
        }
        return place;
    }
}
