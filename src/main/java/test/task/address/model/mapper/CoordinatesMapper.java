package test.task.address.model.mapper;

import org.springframework.stereotype.Component;
import test.task.address.model.Coordinates;
import test.task.address.model.dto.CoordinatesResponseDto;

@Component
public class CoordinatesMapper {
    public Coordinates getCoordinatesFromCoordinatesResponseDto(
            CoordinatesResponseDto coordinatesResponseDto) {
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(coordinatesResponseDto.getLatitude());
        coordinates.setLongitude(coordinatesResponseDto.getLongitude());
        return coordinates;
    }
}
