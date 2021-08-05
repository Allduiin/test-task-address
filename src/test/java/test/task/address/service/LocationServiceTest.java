package test.task.address.service;

import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import test.task.address.mapper.LocationMapper;
import test.task.address.model.Coordinates;
import test.task.address.model.Location;
import test.task.address.repository.LocationRepository;
import test.task.address.service.impl.LocationServiceImpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LocationServiceTest {
    private static final Long ID = 1L;
    private static final String ADDRESS = "Unter+den+Linden+1+Berlin";
    private static final String ADDRESS_WITH_SPACES = "Unter den Linden 1 Berlin";
    private static final String EXPECTED_ADDRESS =
            "Kommandantenhaus, 1, Unter den Linden, Mitte, Berlin, 10117, Deutschland";
    private static final Double LAT = 52.51720765;
    private static final Double LON = 13.397834399325466;
    private LocationMapper locationMapper;
    private CoordinatesService coordinatesService;
    private LocationRepository locationRepository;
    private LocationService locationService;

    @Before
    public void initialize() {
        locationMapper = mock(LocationMapper.class);
        coordinatesService = mock(CoordinatesService.class);
        locationRepository = mock(LocationRepository.class);
        locationService = new LocationServiceImpl(locationMapper,
                coordinatesService, locationRepository);
    }

    @Test
    public void searchAndSaveTest() {
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(LAT);
        coordinates.setLongitude(LON);
        Location expected = new Location();
        expected.setCoordinates(coordinates);
        expected.setId(ID);
        when(coordinatesService.add(Mockito.any(Coordinates.class)))
                .thenReturn(coordinates);
        when(locationRepository.save(Mockito.any(Location.class)))
                .thenReturn(expected);

        Location actual = locationService.searchAndSave(ADDRESS);

        verify(coordinatesService).add(Mockito.any(Coordinates.class));
        verify(locationRepository).save(Mockito.any(Location.class));
        Assert.assertEquals("searchAndSave must return location added to db",
                expected, actual);


        Location actual2 = locationService.searchAndSave(ADDRESS_WITH_SPACES);

        Assert.assertEquals("searchAndSave must return location added to db",
                expected, actual2);
    }

    @Test
    public void getAllAddressesTest() {
        Coordinates coordinates1 = new Coordinates();
        coordinates1.setLatitude(LAT);
        coordinates1.setLongitude(LON);
        List<Coordinates> coordinatesList = List.of(coordinates1);
        Location expected = new Location();
        expected.setAddress(EXPECTED_ADDRESS);
        List<Location> expectedList = List.of(expected);
        when(coordinatesService.getAll()).thenReturn(coordinatesList);
        when(locationMapper.getLocationFromJsonNode(Mockito.any(JsonNode.class)))
                .thenReturn(expected);
        List<Location> actualList = locationService.getAllAddressesByDbCoordinates();
        verify(coordinatesService).getAll();
        for (int i = 0; i < expectedList.size(); i++) {
            Assert.assertEquals("getAll method must return all saved at DB coordinates",
                    expectedList.get(i).getAddress(), actualList.get(i).getAddress());
        }
    }


}
