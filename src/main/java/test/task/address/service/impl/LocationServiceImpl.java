package test.task.address.service.impl;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import test.task.address.model.Coordinates;
import test.task.address.model.Location;
import test.task.address.repository.CoordinatesRepository;
import test.task.address.repository.LocationRepository;
import test.task.address.service.CoordinatesService;
import test.task.address.service.LocationService;

@AllArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final CoordinatesService coordinatesService;
    private final LocationRepository locationRepository;

    @Override
    public Location searchAndSave(String address) {
        Location location = new Location();
        location.setAddress(address);
        Coordinates coordinates = searchCoordinatesByAddress(address);
        coordinatesService.add(coordinates);
        location.setCoordinates(searchCoordinatesByAddress(address));
        return locationRepository.save(location);
    }

    @Override
    public List<String> getAllAddressesByDbCoordinates() {
        List<Coordinates> allCoordinates = coordinatesService.getAll();
        List<String> addresses = new ArrayList<>();
        for (Coordinates coordinates: allCoordinates) {
            addresses.addAll(getAddressesByCoordinates(coordinates));
        }
        return addresses;
    }

    private Coordinates searchCoordinatesByAddress(String address) {
        return null;
    }

    private List<String> getAddressesByCoordinates(Coordinates coordinates) {
        return null;
    }
}
