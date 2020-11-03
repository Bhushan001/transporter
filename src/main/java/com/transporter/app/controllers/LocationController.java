package com.transporter.app.controllers;

import com.transporter.app.entity.Location;
import com.transporter.app.exception.LocationNotFoundException;
import com.transporter.app.services.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    LocationService locationService;

    @GetMapping("/list")
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/{id}")
    public Location retrieveLocation(@PathVariable long id) {
        Optional<Location> location = locationService.getLocationById(id);
        if (!location.isPresent())
            throw new LocationNotFoundException();
        return location.get();
    }

    @PostMapping("/save")
    public ResponseEntity<Object> createLocation(@RequestBody Location inputlocation) {
        Location savedLocation = locationService.save(inputlocation);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedLocation.getLocationId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLocation(@RequestBody Location location, @PathVariable long id) {
        Optional<Location> locationOptional = locationService.getLocationById(id);
        if (!locationOptional.isPresent())
            return ResponseEntity.notFound().build();
        location.setLocationId(id);
        locationService.save(location);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable long id) {
        locationService.deleteById(id);
    }
}
