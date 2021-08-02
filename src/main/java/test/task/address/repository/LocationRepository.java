package test.task.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.task.address.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
