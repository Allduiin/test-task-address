package test.task.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.task.address.model.Coordinates;

public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> {
}
