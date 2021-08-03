package test.task.address.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocationResponseDto {
    @JsonProperty("display_name")
    private String address;
    private Double lat;
    private Double lon;
}
