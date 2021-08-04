package test.task.address.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import test.task.address.model.Coordinates;
import test.task.address.model.Location;
import test.task.address.model.dto.CoordinatesResponseDto;
import test.task.address.model.dto.LocationResponseDto;

@Component
public class LocationMapper {
    public Location getLocationFromJsonNode(JsonNode jsonNode) {
        Location location = new Location();
        location.setAddress(jsonNode.get("display_name").asText());
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(jsonNode.get("lat").asDouble());
        coordinates.setLongitude(jsonNode.get("lon").asDouble());
        location.setCoordinates(coordinates);
        return location;
    }

    public LocationResponseDto getLocationResponseDtoFromLocation(Location location) {
        LocationResponseDto locationResponseDto = new LocationResponseDto();
        locationResponseDto.setAddress(location.getAddress());
        Coordinates coordinates = location.getCoordinates();
        CoordinatesResponseDto coordinatesResponseDto = new CoordinatesResponseDto();
        coordinatesResponseDto.setLatitude(coordinates.getLatitude());
        coordinatesResponseDto.setLongitude(coordinates.getLongitude());
        locationResponseDto.setCoordinates(coordinatesResponseDto);
        return locationResponseDto;
    }
}
