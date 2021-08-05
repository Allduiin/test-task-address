package test.task.address.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import test.task.address.mapper.LocationMapper;
import test.task.address.model.Location;
import test.task.address.model.dto.LocationResponseDto;
import test.task.address.service.LocationService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LocationControllerTest {
    private static final String ADDRESS = "Address";
    private LocationMapper locationMapper;
    private LocationService locationService;
    private LocationController locationController;

    @Before
    public void initialize() {
        this.locationMapper = mock(LocationMapper.class);
        this.locationService = mock(LocationService.class);
        locationController = new LocationController(locationService,locationMapper);
    }

    @Test
    public void searchAndSaveTest() {
        when(locationService.searchAndSave(ADDRESS)).thenReturn(Mockito.any());
        locationController.searchAndSave(ADDRESS);
        verify(locationService).searchAndSave(ADDRESS);
    }

    @Test
    public void getAllAddressesTest() {
        Location location1 = new Location();
        Location location2 = new Location();
        LocationResponseDto responseDto1 = new LocationResponseDto();
        LocationResponseDto responseDto2 = new LocationResponseDto();
        List<Location> locations = List.of(location1, location2);
        when(locationService.getAllAddressesByDbCoordinates()).thenReturn(locations);
        when(locationMapper.getLocationResponseDtoFromLocation(location1))
                .thenReturn(responseDto1);
        when(locationMapper.getLocationResponseDtoFromLocation(location2))
                .thenReturn(responseDto2);
        List<LocationResponseDto> locationResponseDtos = locationController.getAllAddresses();
        Assert.assertEquals("Method must return locationResponse Dtos",
                responseDto1, locationResponseDtos.get(0));
        Assert.assertEquals("Method must return locationResponse Dtos",
                responseDto2, locationResponseDtos.get(1));
        verify(locationService).getAllAddressesByDbCoordinates();
        verify(locationMapper, times(2))
                .getLocationResponseDtoFromLocation(Mockito.any(Location.class));
    }
}
