package me.myte.gpc.contest.services;

import lombok.extern.log4j.Log4j;
import me.myte.gpc.contest.models.Place;
import me.myte.gpc.contest.models.Request;
import me.myte.gpc.contest.repositories.PlaceRepository;
import me.myte.gpc.contest.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j
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
        List<Place> places = ExternalAPI.findByCoordinates(coordinates);
        return savePlaces(places);
    }

    public List<Place> findByString(String request) {
        List<Place> places = ExternalAPI.findByAddress(request);
        return savePlaces(places);
    }

    private List<Place> savePlaces(List<Place> places) {
        if (places.size() != 0) {
            log.info("Found: " + places.size());
            List<Place> added = places.stream()
                    .filter(a -> placeRepository.findByAddressAndLongitudeAndLatitude(a.getAddress(), a.getLongitude(), a.getLatitude()).isEmpty())
                    .peek(placeRepository::save)
                    .collect(Collectors.toList());
            log.info("Saved " + added.size() + " results to the base."
                    + (places.size() > added.size() ? " " + (places.size() - added.size()) + " ignored.": ""));
        } else {
            log.warn("Nothing found");
        }
        return places;
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public void add(Request request) {
        requestRepository.save(request);
    }
}
