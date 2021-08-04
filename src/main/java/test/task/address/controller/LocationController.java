package test.task.address.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.task.address.mapper.LocationMapper;
import test.task.address.model.dto.LocationResponseDto;
import test.task.address.service.LocationService;

@RestController
@RequestMapping("location")
@AllArgsConstructor
public class LocationController {
    private final LocationService locationService;
    private final LocationMapper locationMapper;

    @GetMapping("search_and_save")
    public void searchAndSave(@RequestParam String address) {
        locationService.searchAndSave(address);
    }

    @GetMapping("get_all_addresses")
    public List<LocationResponseDto> getAllAddresses() {
        return locationService.getAllAddressesByDbCoordinates().stream()
                .map(locationMapper::getLocationResponseDtoFromLocation)
                .collect(Collectors.toList());
    }
}
