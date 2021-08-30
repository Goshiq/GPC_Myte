package me.myte.gpc.contest.services;

import me.myte.gpc.contest.models.Place;
import me.myte.gpc.contest.repositories.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Place    findById(long id) {
        Optional<Place> place = placeRepository.findById(id);
        return (place.orElse(null));
    }

    public void     add(Place place) {
        placeRepository.save(place);
    }

    public List<Place> findAll() {
        return placeRepository.findAll();
    }
}
