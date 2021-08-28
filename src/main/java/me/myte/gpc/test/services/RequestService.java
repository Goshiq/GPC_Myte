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

    public Place findByCoordinates(Double[] coordinates) {
        Place place = ExternalAPI.findByCoordinates(coordinates);
        if (place != null && place.getAddress() != null) {
            log.info("Saving the result to the base");
            placeRepository.save(place);
        }
        return place;
    }

    public Place findByString(String request) {
        Place place = ExternalAPI.findByAddress(request);
        if (place != null && place.getLatitude() != null) {
            log.info("Saving the result to the base");
            placeRepository.save(place);
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
