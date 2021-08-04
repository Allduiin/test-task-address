package test.task.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.task.address.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
