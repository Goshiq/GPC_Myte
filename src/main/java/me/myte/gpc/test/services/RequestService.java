package me.myte.gpc.test.services;

import lombok.extern.slf4j.Slf4j;
import me.myte.gpc.test.models.Place;
import me.myte.gpc.test.models.Request;
import me.myte.gpc.test.repositories.PlaceRepository;
import me.myte.gpc.test.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RequestService {

    private final RequestRepository requestRepository;
    private final PlaceRepository placeRepository;

    public RequestService(RequestRepository requestRepository, PlaceRepository placeRepository) {
        this.requestRepository = requestRepository;
        this.placeRepository = placeRepository;
    }

    public Request findById(long id) {
        Optional<Request> ans = requestRepository.findById(id);
        if (ans.isEmpty()) {
            return null;
        }
        return ans.get();
    }

    public List<Place> findByCoordinates(Double[] coordinates) {
        List<Place> place = ExternalAPI.findByCoordinates(coordinates);
        return savePlaces(place);
    }

    public List<Place> findByString(String request) {
        List<Place> place = ExternalAPI.findByAddress(request);
        return savePlaces(place);
    }

    private List<Place> savePlaces(List<Place> place) {
        if (place.size() != 0) {
            log.info("Found: " + place.size());
            List<Place> added = place.stream()
                    .filter(a -> placeRepository.findByAddressAndLongitudeAndLatitude(a.getAddress(), a.getLongitude(), a.getLatitude()).isEmpty())
                    .peek(placeRepository::save)
                    .collect(Collectors.toList());
            log.info("Saved " + added.size() + " results to the base."
                    + (place.size() > added.size() ? " " + (place.size() - added.size()) + " ignored.": ""));
        } else {
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
