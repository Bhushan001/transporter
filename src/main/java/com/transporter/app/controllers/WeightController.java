package com.transporter.app.controllers;

import com.transporter.app.entity.Weight;
import com.transporter.app.exception.WeightNotFoundException;
import com.transporter.app.services.WeightService;
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
@RequestMapping("/weights")
public class WeightController {

    private Logger logger = LoggerFactory.getLogger(WeightController.class);

    @Autowired
    WeightService weightService;

    @GetMapping("/list")
    public List<Weight> getAllWeights() {
        return weightService.getAllWeights();
    }

    @GetMapping("/{id}")
    public Weight retrieveWeight(@PathVariable long id) {
        Optional<Weight> weight = weightService.getWeightById(id);
        if (!weight.isPresent())
            throw new WeightNotFoundException();
        return weight.get();
    }

    @PostMapping("/save")
    public ResponseEntity<Object> createWeight(@RequestBody Weight weight) {
        Weight savedWeight = weightService.save(weight);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedWeight.getWeightId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWeight(@RequestBody Weight weight, @PathVariable long id) {
        Optional<Weight> weightOptional = weightService.getWeightById(id);
        if (!weightOptional.isPresent())
            return ResponseEntity.notFound().build();
        weight.setWeightId(id);
        weightService.save(weight);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteWeight(@PathVariable long id) {
        weightService.deleteById(id);
    }
}
