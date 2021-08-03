package test.task.address.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import test.task.address.model.Coordinates;
import test.task.address.model.Location;
import test.task.address.model.dto.CoordinatesResponseDto;
import test.task.address.model.mapper.CoordinatesMapper;
import test.task.address.repository.LocationRepository;
import test.task.address.service.CoordinatesService;
import test.task.address.service.LocationService;

@AllArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {
    private final CoordinatesMapper coordinatesMapper;
    private final CoordinatesService coordinatesService;
    private static final String[] SEARCH_REQUEST
            = new String[]{"https://nominatim.openstreetmap.org/?addressdetails=1&q=",
            "&format=json&limit=1"};
    private final LocationRepository locationRepository;

    @Override
    public Location searchAndSave(String address) {
        Location location = new Location();
        location.setAddress(address);
        try {
            Coordinates coordinates = searchCoordinatesByAddress(address);
            coordinatesService.add(coordinates);
            location.setCoordinates(searchCoordinatesByAddress(address));
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException("Problem with nominatim search method", e);
        }
        return locationRepository.save(location);
    }

    @Override
    public List<String> getAllAddressesByDbCoordinates() {
        List<Coordinates> allCoordinates = coordinatesService.getAll();
        List<String> addresses = new ArrayList<>();
        for (Coordinates coordinates : allCoordinates) {
            addresses.addAll(getAddressesByCoordinates(coordinates));
        }
        return addresses;
    }

    private Coordinates searchCoordinatesByAddress(String address)
            throws URISyntaxException, IOException, InterruptedException {
        String requestAddress = address.replaceAll(" ", "+");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(SEARCH_REQUEST[0] + requestAddress + SEARCH_REQUEST[1]))
                .GET()
                .build();
        String response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString()).body();
        CoordinatesResponseDto coordinatesResponseDto =
                new ObjectMapper().readValue(response, CoordinatesResponseDto.class);
        return coordinatesMapper.getCoordinatesFromCoordinatesResponseDto(coordinatesResponseDto);
    }

    private List<String> getAddressesByCoordinates(Coordinates coordinates) {
        return null;
    }
}
