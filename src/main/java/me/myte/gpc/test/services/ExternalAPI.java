package me.myte.gpc.test.services;

import me.myte.gpc.test.models.Place;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ExternalAPI {

    // 2gis
    private static final String prefix = "https://catalog.api.2gis.com/3.0/items/geocode?";
    // key
    private static final String key = "rupjhj5341";
    private static String url;
    private static Mono<Place> newPlace;

    public static Place findByName(String name) {
        url = prefix + "q=" + name + "&fields=items.point&key=" + key;
        newPlace = WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(Place.class);
//        System.out.println(url);
        return newPlace.block();
    }

    public static Place findByCoordinates(Double[] coords) {
        url = prefix + "lat=" + coords[0] + "&lon=" + coords[1] + "&fields=items.point&key=" + key;
        newPlace = WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(Place.class);
//        System.out.println(url);
        return newPlace.block();
    }
}
