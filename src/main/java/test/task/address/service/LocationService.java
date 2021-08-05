package test.task.address.service;

import java.util.List;
import test.task.address.model.Location;

public interface LocationService {
    Location searchAndSave(String address);

    List<Location> getAllAddressesByDbCoordinates();
}
