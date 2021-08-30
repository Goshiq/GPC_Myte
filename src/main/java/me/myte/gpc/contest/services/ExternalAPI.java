package me.myte.gpc.contest.services;

import me.myte.gpc.contest.models.Place;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExternalAPI {

    // key yandex
    private static final String key = "b8b762e3-c400-4331-9fac-a6f69aac2e52";

    // url yandex
    private static final String prefix = "https://geocode-maps.yandex.ru/1.x/?apikey=" + key + "&format=json&geocode=";

    private static String url;
    private static Mono<String> newPlace;

    public static List<Place> findByAddress(String address) {
        url = prefix + address;
        newPlace = WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
        String response = newPlace.block();
        return parseResponse(response);
    }

    public static List<Place> findByCoordinates(Double[] coords) {
        url = prefix + coords[0] + "," + coords[1];
        newPlace = WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
        String response = newPlace.block();
        return parseResponse(response);
    }

    private static List<Place> parseResponse(String response) {
        List<Place> ans = new ArrayList<>();
        String expression = "},\"name\":\"(.+?)\",(\"description\":\"(.+?)\")?.+?}},\"Point\":\\{\"pos\":\"([\\d.]+ [\\d.]+)";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(response);
        while (matcher.find()) {
            String name = matcher.group(1);
            String description = matcher.group(3);
            if (description != null){
                name += " " + description;
            }
            String[] point = matcher.group(4).split(" ");
            ans.add(new Place(name, Double.parseDouble(point[0]), Double.parseDouble(point[1])));
        }
        return ans;
    }
}
