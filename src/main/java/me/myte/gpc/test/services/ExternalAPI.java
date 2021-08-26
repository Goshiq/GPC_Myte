package me.myte.gpc.test.services;

import me.myte.gpc.test.models.Place;

public class ExternalAPI {

    private final String url = "https://catalog.api.2gis.com/3.0/items/geocode";

    public static Place findByName(String name) {
        return new Place("By name");
    }

    public static Place findByCoordinates(Double[] coords) {
        return new Place("By Doubles");
    }
}
