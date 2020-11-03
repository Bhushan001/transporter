package com.transporter.app.controllers;

import com.transporter.app.entity.Truck;
import com.transporter.app.exception.TruckNotFoundException;
import com.transporter.app.services.TruckService;
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
@RequestMapping("/trucks")
public class TruckController {

    private Logger logger = LoggerFactory.getLogger(TruckController.class);

    @Autowired
    TruckService truckService;

    @GetMapping("/list")
    public List<Truck> getAllTrucks() {
        return truckService.getAllTrucks();
    }

    @GetMapping("/{id}")
    public Truck retrieveTruck(@PathVariable long id) {
        Optional<Truck> truck = truckService.getTruckById(id);
        if (!truck.isPresent())
            throw new TruckNotFoundException();
        return truck.get();
    }

    @PostMapping("/save")
    public ResponseEntity<Object> createTruck(@RequestBody Truck truck) {
        Truck savedTruck = truckService.save(truck);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTruck.getTruckId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTruck(@RequestBody Truck truck, @PathVariable long id) {
        Optional<Truck> truckOptional = truckService.getTruckById(id);
        if (!truckOptional.isPresent())
            return ResponseEntity.notFound().build();
        truck.setTruckId(id);
        truckService.save(truck);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteTruck(@PathVariable long id) {
        truckService.deleteById(id);
    }
}
