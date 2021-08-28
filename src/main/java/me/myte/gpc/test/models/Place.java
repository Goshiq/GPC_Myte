package me.myte.gpc.test.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long    id;

    public Place(String name) {
        this.address = name;
    }

    public Place(String address, Double longitude, Double latitude) {
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    private String  address;

    private Double  longitude;

    private Double  latitude;
}
