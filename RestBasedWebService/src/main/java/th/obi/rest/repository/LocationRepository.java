package th.obi.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.obi.rest.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
