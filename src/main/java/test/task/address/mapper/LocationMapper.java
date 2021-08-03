package test.task.address.mapper;

import org.springframework.stereotype.Component;
import test.task.address.model.Coordinates;
import test.task.address.model.Location;
import test.task.address.model.dto.LocationResponseDto;

@Component
public class LocationMapper {
    public Location getLocationFromLocationResponseDto(LocationResponseDto locationResponseDto) {
        Location location = new Location();
        location.setAddress(locationResponseDto.getAddress());
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(locationResponseDto.getLat());
        coordinates.setLongitude(locationResponseDto.getLon());
        location.setCoordinates(coordinates);
        return location;
    }
}
