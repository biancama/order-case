package com.afkl.travel.exercise;

import com.afkl.travel.exercise.model.Location;
import com.afkl.travel.exercise.model.LocationDeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ClientApplication {
    public static void main(String[] args) throws Exception {
        log.info("starting client");
        httpGetRequest();
    }

    private static void httpGetRequest() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .header("Authorization", "Basic c29tZXVzZXI6cHN3")
                .header("Content-type", "application/json")
                .header("Accept-Language", "NL")
                .uri(URI.create("http://localhost:8080/travel/locations"))
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());


        String responseBody = response.body();
        int responseStatusCode = response.statusCode();


        parse(responseBody);

    }

    public static void parse(String responseBody) throws JsonProcessingException {
        JSONArray locations = new JSONArray(responseBody);
        List<Location> allLocations = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Location.class, new LocationDeserializer(Location.class));
        mapper.registerModule(module);

        for (int i = 0 ; i < locations.length(); i++) {
            JSONObject locationsJSONObject = locations.getJSONObject(i);
            Location location = mapper.readValue(locationsJSONObject.toString(),  Location.class);
            allLocations.add(location);
        }
        var allUSCitiesCodes = allLocations.stream()
                .filter(l -> l.getType().equals("city") && l.getParentCode() != null &&l.getParentCode().equals("US"))
                .map(l -> l.getCode())
                .collect(Collectors.toSet());
        var allUSAirports = new ArrayList<>();
        for (Location allLocation : allLocations) {
            if (allUSCitiesCodes.contains(allLocation.getParentCode())) {
                allUSAirports.add(allLocation);
            }
        }
        allUSAirports.forEach(l -> System.out.println(l) );
    }
}
