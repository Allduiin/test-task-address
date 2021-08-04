package test.task.address.model.dto;

import lombok.Data;

@Data
public class LocationResponseDto {
    private String address;
    private CoordinatesResponseDto coordinates;
}
