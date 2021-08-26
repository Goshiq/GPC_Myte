package me.myte.gpc.test.services;

import me.myte.gpc.test.models.Place;

public class ExternalAPI {

    public static Place findByName(String name) {
        return new Place();
    }

    public static Place findByCoordinates(double[] coords) {
        return new Place();
    }
}
