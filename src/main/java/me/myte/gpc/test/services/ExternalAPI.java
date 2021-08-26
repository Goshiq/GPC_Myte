package me.myte.gpc.test.services;

import me.myte.gpc.test.models.Place;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

public class ExternalAPI {

    // 2gis
    private static String url = "https://catalog.api.2gis.com/3.0/items/geocode";

    private static WebClient webclient;

    // Here key
    private static String key = "E-UwPIvFeDSLsyicuaLQ419T4uVQj46ggfVC00wXIs8";

    public static Place findByName(String name) {
        url += ("?q=" + name + "&fields=items.point&key=" + key);
        webclient = WebClient.create(url);
        return new Place("By name");
    }

    public static Place findByCoordinates(Double[] coords) {
        return new Place("By Doubles");
    }
}
