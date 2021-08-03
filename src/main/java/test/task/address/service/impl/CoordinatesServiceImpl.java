package test.task.address.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import test.task.address.model.Coordinates;
import test.task.address.repository.CoordinatesRepository;
import test.task.address.service.CoordinatesService;

@Service
public class CoordinatesServiceImpl implements CoordinatesService {
    private CoordinatesRepository coordinatesRepository;

    @Override
    public Coordinates add(Coordinates coordinates) {
        return coordinatesRepository.save(coordinates);
    }

    @Override
    public List<Coordinates> getAll() {
        return coordinatesRepository.findAll();
    }
}
