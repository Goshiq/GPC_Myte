package me.myte.gpc.test.repositories;

import me.myte.gpc.test.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByAddressAndLongitudeAndLatitude(String address, Double longitude, Double latitude);
}
