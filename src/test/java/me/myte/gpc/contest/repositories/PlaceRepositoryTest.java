package me.myte.gpc.contest.repositories;

import me.myte.gpc.contest.GpcApplication;
import me.myte.gpc.contest.models.Place;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {GpcApplication.class})
class PlaceRepositoryTest {

    @Resource
    private PlaceRepository placeRepository;

    @Test
    void findByAddressAndLongitudeAndLatitude() {
        Place place = new Place("Some street", 1D, 2D);
        placeRepository.save(place);

        Optional<Place> testPlace = placeRepository.findByAddressAndLongitudeAndLatitude(place.getAddress(), place.getLongitude(), place.getLatitude());
        assertTrue(testPlace.isPresent());
        testPlace = placeRepository.findByAddressAndLongitudeAndLatitude("", 0D, 0D);
        assertFalse(testPlace.isPresent());
    }
}