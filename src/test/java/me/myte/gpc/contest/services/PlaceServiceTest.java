package me.myte.gpc.contest.services;

import me.myte.gpc.contest.GpcApplication;
import me.myte.gpc.contest.models.Place;
import me.myte.gpc.contest.repositories.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {GpcApplication.class})
class PlaceServiceTest {

    @Resource
    private PlaceRepository placeRepository;

    @Test
    void findById() {
        Place place = new Place("Some address", 1D, 2D);
        Place savedPlace = placeRepository.save(place);

        Optional<Place> testPlace = placeRepository.findById(savedPlace.getId());
        assertTrue(testPlace.isPresent());
        assertEquals(testPlace.get().getId(), savedPlace.getId());

        testPlace = placeRepository.findById(-12L);
        assertTrue(testPlace.isEmpty());
    }

    @Test
    void add() {
        List<Place> places = placeRepository.findAll();

        placeRepository.save(new Place("", 1D, 1D));

        List<Place> updatedPlaces = placeRepository.findAll();
        assertNotEquals(places.size(), updatedPlaces.size());
        assertEquals(1, updatedPlaces.size() - places.size());
    }

    @Test
    void findAll() {
        List<Place> places = placeRepository.findAll();

        assertEquals(0, places.size());
        placeRepository.save(new Place("", 1D, 1D));
        placeRepository.save(new Place("", 1D, 1D));
        placeRepository.save(new Place("", 1D, 1D));
        places = placeRepository.findAll();
        assertEquals(3, places.size());
    }
}