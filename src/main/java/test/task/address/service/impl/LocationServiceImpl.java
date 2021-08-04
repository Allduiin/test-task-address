package test.task.address.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import test.task.address.mapper.LocationMapper;
import test.task.address.model.Coordinates;
import test.task.address.model.Location;
import test.task.address.repository.LocationRepository;
import test.task.address.service.CoordinatesService;
import test.task.address.service.LocationService;

@AllArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {
    private static final String[] SEARCH_REQUEST
            = new String[]{"https://nominatim.openstreetmap.org/?addressdetails=1&q=",
            "&format=json&limit=1"};
    private final LocationMapper locationMapper;
    private final CoordinatesService coordinatesService;
    private final LocationRepository locationRepository;

    @Override
    public Location searchAndSave(String address) {
        Location location = new Location();
        location.setAddress(address);
        try {
            Coordinates coordinates = searchCoordinatesByAddress(address);
            coordinatesService.add(coordinates);
            location.setCoordinates(coordinates);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException("Problem with nominatim search method", e);
        }
        return locationRepository.save(location);
    }

    @Override
    public List<Location> getAllAddressesByDbCoordinates() {
        List<Coordinates> allCoordinates = coordinatesService.getAll();
        List<Location> locations = new ArrayList<>();
        for (Coordinates coordinates : allCoordinates) {
            try {
                locations.add(getLocationByCoordinates(coordinates));
            } catch (URISyntaxException | IOException | InterruptedException e) {
                throw new RuntimeException("Problem with nominatim reverse method", e);
            }
        }
        return locations;
    }

    private Coordinates searchCoordinatesByAddress(String address)
            throws URISyntaxException, IOException, InterruptedException {
        String requestAddress = address.replaceAll(" ", "+");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(SEARCH_REQUEST[0] + requestAddress + SEARCH_REQUEST[1]))
                .GET()
                .build();
        String responseAsString = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString()).body();
        JsonNode jsonNode = new ObjectMapper().readTree(responseAsString).get(0);
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(jsonNode.get("lat").asDouble());
        coordinates.setLongitude(jsonNode.get("lon").asDouble());
        return coordinates;
    }

    private Location getLocationByCoordinates(Coordinates coordinates)throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat="
                        + coordinates.getLatitude() + "&lon=" + coordinates.getLongitude()))
                .GET()
                .build();
        String response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString()).body();
        JsonNode jsonNode = new ObjectMapper().readValue(response, JsonNode.class);
        return locationMapper.getLocationFromJsonNode(jsonNode);
    }
}
