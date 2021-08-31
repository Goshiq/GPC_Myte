package me.myte.gpc.contest.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // uncomment if you want to hide the place id
    private long    id;

    public Place(String address, Double longitude, Double latitude) {
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    private String  address;

    private Double  longitude;

    private Double  latitude;
}
