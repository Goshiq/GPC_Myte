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
        Place place = ExternalAPI.findByCoordinates(coordinates);
        if (place.getAdress() != null) {
            placeRepository.save(place);
        }
        return place;
    }

    public Place    findByString(String request) {
        Place place = ExternalAPI.findByName(request);
        if (place.getLatitude() != null) {
            placeRepository.save(place);
        }
        return place;
    }

    public List<Place> findAll() {
        return placeRepository.findAll();
    }
}
