package me.myte.gpc.test.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.myte.gpc.test.models.Place;
import me.myte.gpc.test.repositories.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public Place    findById(long id) {
        Optional<Place> place = placeRepository.findById(id);
        return (place.orElse(null));
    }

    public Place    findByCoordinates(Double[] coordinates) {
        return (ExternalAPI.findByCoordinates(coordinates));
    }

    public Place    findByString(String request) {
        return (ExternalAPI.findByName(request));
    }

    public List<Place> findAll() {
        return placeRepository.findAll();
    }
}