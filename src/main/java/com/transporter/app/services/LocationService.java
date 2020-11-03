package com.transporter.app.services;

import com.transporter.app.entity.Location;
import com.transporter.app.entity.State;
import com.transporter.app.repository.LocationRepository;
import com.transporter.app.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    public Location save(Location location){
        return locationRepository.save(location);
    }

    public void deleteById(long id) {
        locationRepository.deleteById(id);
    }

}
