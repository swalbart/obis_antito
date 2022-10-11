package th.obi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.obi.rest.repository.LocationRepository;
import th.obi.rest.entity.Location;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {
    private LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    // for testing only
    // get all location as a List<Location>
    @GetMapping("")
    public List<Location> getAllLocation(){
        return locationRepository.findAll();
    }

    @Autowired
    EntityManagerFactory emf;
    @GetMapping("/route_id/{id}")
    public List<Location> getAllLocationByRoute(@PathVariable long id){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select id, name, latitude, longitude, timestamp, route_id from Location where route_id= '"+id+"'");
        List<Location> locationList = (List<Location>)query.getResultList();
        em.close();
        return locationList;
    }

    // get location by id
    @GetMapping(path = "/{id}")
    public Location getLocation(@PathVariable long id){
        if(locationRepository.findById(id).isPresent())
            return locationRepository.findById(id).get();
        return null;
    }

    // create a new location
    @PostMapping(path = "")
    public void saveLocation(@RequestBody Location location){
        locationRepository.save(location);
    }

    // update a location
    @PatchMapping(path = "/{id}/all/{name}/{latitude}/{longitude}/{timestamp}")
    public void updateLocationAll(@PathVariable long id, @PathVariable String name, @PathVariable float latitude, @PathVariable float longitude, @PathVariable Timestamp timestamp){
        if(locationRepository.findById(id).isPresent()) {
            Location location = locationRepository.findById(id).get();
            location.setName(name);
            location.setLatitude(latitude);
            location.setLongitude(longitude);
            location.setTimestamp(timestamp);
            locationRepository.save(location);
        }
    }

    @PatchMapping(path = "/{id}/name/{name}")
    public void updateLocationName(@PathVariable long id, @PathVariable String name){
        if(locationRepository.findById(id).isPresent()) {
            Location location = locationRepository.findById(id).get();
            location.setName(name);
            locationRepository.save(location);
        }
    }

    @PatchMapping(path = "/{id}/latitude/{latitude}")
    public void updateLocationLatitude(@PathVariable long id, @PathVariable float latitude){
        if(locationRepository.findById(id).isPresent()) {
            Location location = locationRepository.findById(id).get();
            location.setLatitude(latitude);
            locationRepository.save(location);
        }
    }

    @PatchMapping(path = "/{id}/longitude/{longitude}")
    public void updateLocationLongitude(@PathVariable long id, @PathVariable float longitude){
        if(locationRepository.findById(id).isPresent()) {
            Location location = locationRepository.findById(id).get();
            location.setLongitude(longitude);
            locationRepository.save(location);
        }
    }

    @PatchMapping(path = "/{id}/timestamp/{timestamp}")
    public void updateLocationTimestamp(@PathVariable long id, @PathVariable Timestamp timestamp) {
        if (locationRepository.findById(id).isPresent()){
            Location location = locationRepository.findById(id).get();
            location.setTimestamp(timestamp);
            locationRepository.save(location);
        }
    }

    // delete location by id
    @DeleteMapping(path = "/{id}")
    public void deleteLocation(@PathVariable long id){
        locationRepository.deleteById(id);
    }

}
