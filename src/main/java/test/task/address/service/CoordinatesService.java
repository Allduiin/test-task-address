package test.task.address.service;

import java.util.List;
import test.task.address.model.Coordinates;

public interface CoordinatesService {
    Coordinates add(Coordinates coordinates);

    List<Coordinates> getAll();
}
