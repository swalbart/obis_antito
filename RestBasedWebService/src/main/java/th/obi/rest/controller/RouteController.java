package th.obi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.obi.rest.entity.Location;
import th.obi.rest.repository.RouteRepository;
import th.obi.rest.entity.Route;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {
    private RouteRepository routeRepository;

    public RouteController(RouteRepository routeRepository){
        this.routeRepository = routeRepository;
    }


    // get all routes as a List<Route>
    @GetMapping("")
    public List<Route> getRoute(){
        return routeRepository.findAll();
    }

    @Autowired
    EntityManagerFactory emf;
    @GetMapping("/device_id/{id}")
    public List<Route> getAllRouteByDevice(@PathVariable long id){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select id, name, isTracked, device_id from Route where device_id= '"+id+"'");
        List<Route> routeList = (List<Route>)query.getResultList();
        em.close();
        return routeList;
    }

    // get route by id
    @GetMapping(path = "/{id}")
    public Route getRoute(@PathVariable long id){
        if(routeRepository.findById(id).isPresent())
            return routeRepository.findById(id).get();
        return null;
    }

    // create a new route
    @PostMapping(path = "")
    public void saveRoute(@RequestBody Route route){
        routeRepository.save(route);
    }

    // update a route
    // currently redundant to updateRouteAll. Might change by adding more attributes to the route entity
    @PatchMapping(path = "/{id}/all/{name}/{isTracked}")
    public void updateRouteAll(@PathVariable long id, @PathVariable String name){
        if(routeRepository.findById(id).isPresent()) {
            Route route = routeRepository.findById(id).get();
            route.setName(name);
            routeRepository.save(route);
        }
    }

    // currently redundant to updateRouteAll. Might change by adding more attributes to the route entity
    @PatchMapping(path = "/{id}/name/{name}")
    public void updateRouteName(@PathVariable long id, @PathVariable String name){
        if(routeRepository.findById(id).isPresent()) {
            Route route = routeRepository.findById(id).get();
            route.setName(name);
            routeRepository.save(route);
        }
    }

    // delete route by id
    @DeleteMapping(path = "/{id}")
    public void deleteRoute(@PathVariable long id){
        routeRepository.deleteById(id);
    }

}
