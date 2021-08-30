package me.myte.gpc.contest.repositories;

import me.myte.gpc.contest.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByAddressAndLongitudeAndLatitude(String address, Double longitude, Double latitude);
}
