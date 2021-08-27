package me.myte.gpc.test.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.myte.gpc.test.models.Place;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ExternalAPI {

    // key 2gis
//    private static final String key = "rupjhj5341";

    // url 2gis
//    private static final String prefix = "https://catalog.api.2gis.com/3.0/items/geocode?";

    // key yandex
    private static final String key = "b8b762e3-c400-4331-9fac-a6f69aac2e52";

    // url yandex
    private static final String prefix = "https://geocode-maps.yandex.ru/1.x/?apikey=" + key + "&format=json&geocode=";

    private static String url;
    private static Mono<String> newPlace;
    private static ObjectMapper mapper = new ObjectMapper();

    public static Place findByAddress(String address) {
        // 2gis
//        url = prefix + "q=" + name + "&fields=items.point&key=" + key;
        url = prefix + address;
        newPlace = WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
        System.out.println(newPlace.block());
        try {
            Place ans = mapper.readValue(newPlace.block(), Place.class);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new Place(address);
    }

    public static Place findByCoordinates(Double[] coords) {
        url = prefix + coords[0] + "," + coords[1];
        newPlace = WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
        System.out.println(newPlace.block());
        return new Place();
    }
}
