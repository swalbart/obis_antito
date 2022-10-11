package th.obi.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.obi.rest.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

}
