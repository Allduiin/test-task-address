package test.task.address.service;

import java.util.List;
import org.apache.tomcat.jni.Address;
import test.task.address.model.Location;

public interface LocationService {
    Location searchAndSave(String address);

    List<String> getAllAddressesByDbCoordinates();
}
