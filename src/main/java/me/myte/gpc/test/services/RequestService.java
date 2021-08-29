package me.myte.gpc.test.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.myte.gpc.test.models.Place;
import me.myte.gpc.test.models.Request;
import me.myte.gpc.test.repositories.PlaceRepository;
import me.myte.gpc.test.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final PlaceRepository placeRepository;

    public Request findById(long id) {
        Optional<Request> ans = requestRepository.findById(id);
        if (ans.isEmpty()) {
            return null;
        }
        return ans.get();
    }

    public List<Place> findByCoordinates(Double[] coordinates) {
        List<Place> place = ExternalAPI.findByCoordinates(coordinates);
        if (place.size() != 0) {
            log.info("Saving the results to the base: " + place.size());
            placeRepository.saveAll(place);
        }
        else {
            log.warn("Nothing found");
        }
        return place;
    }

    public List<Place> findByString(String request) {
        List<Place> place = ExternalAPI.findByAddress(request);
        if (place.size() != 0) {
            log.info("Saving the results to the base: " + place.size());
            placeRepository.saveAll(place);
        }
        else {
            log.warn("Nothing found");
        }
        return place;
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public void add(Request request) {
        requestRepository.save(request);
    }
}
