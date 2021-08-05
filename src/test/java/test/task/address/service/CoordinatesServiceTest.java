package test.task.address.service;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.task.address.model.Coordinates;
import test.task.address.repository.CoordinatesRepository;
import test.task.address.service.impl.CoordinatesServiceImpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CoordinatesServiceTest {
    private static final Long ID = 1L;
    private CoordinatesRepository coordinatesRepository;
    private CoordinatesService coordinatesService;

    @Before
    public void initialize() {
        coordinatesRepository = mock(CoordinatesRepository.class);
        coordinatesService = new CoordinatesServiceImpl(coordinatesRepository);
    }

    @Test
    public void addTest() {
        Coordinates coordinates = new Coordinates();
        Coordinates expected = new Coordinates();
        expected.setId(ID);
        when(coordinatesRepository.save(coordinates)).thenReturn(expected);
        Coordinates actual = coordinatesService.add(coordinates);
        verify(coordinatesRepository).save(coordinates);
        Assert.assertEquals("Add method must return saved at DB coordinates",
                expected, actual);
    }

    @Test
    public void getAllTest() {
        Coordinates result1 = new Coordinates();
        Coordinates result2 = new Coordinates();
        List<Coordinates> expectedList = List.of(result1, result2);
        when(coordinatesRepository.findAll()).thenReturn(expectedList);
        List<Coordinates> actualList = coordinatesService.getAll();
        verify(coordinatesRepository).findAll();
        for (int i = 0; i < expectedList.size(); i++) {
            Assert.assertEquals("getAll method must return all saved at DB coordinates",
                    expectedList.get(i), actualList.get(i));
        }
    }
}
