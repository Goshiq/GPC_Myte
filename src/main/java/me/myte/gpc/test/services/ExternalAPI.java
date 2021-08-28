package me.myte.gpc.test.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.myte.gpc.test.models.Place;
import net.minidev.json.JSONObject;
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
    private static ObjectMapper mapper = new ObjectMapper();
    private static Place ans;

    public static Place findByAddress(String address) {
        url = prefix + address;
        newPlace = WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
        String response = newPlace.block();
        return parseResponse(response);
    }

    public static Place findByCoordinates(Double[] coords) {
        url = prefix + coords[0] + "," + coords[1];
        newPlace = WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
        String response = newPlace.block();
        return parseResponse(response);
    }

    private static Place parseResponse(String response) {
        List<Place> ans = new ArrayList<>();
        String expression = "},\"name\":\"(.+?)\",\"description\":\"(.+?)\".+?}},\"Point\":\\{\"pos\":\"([\\d.]+ [\\d.]+)";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(response);
        while (matcher.find()) {
            String name = matcher.group(1);
            String description = matcher.group(2);
            String[] point = matcher.group(3).split(" ");
            Double[] coords = new Double[]{Double.parseDouble(point[0]), Double.parseDouble(point[1])};
            ans.add(new Place(name + " " + description, Double.parseDouble(point[0]), Double.parseDouble(point[1])));
        }
        System.out.println(ans);
        return null;
    }
}
