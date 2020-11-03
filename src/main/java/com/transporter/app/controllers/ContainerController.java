package com.transporter.app.controllers;

import com.transporter.app.entity.Container;
import com.transporter.app.exception.ContainerNotFoundException;
import com.transporter.app.services.ContainerService;
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
@RequestMapping("/containers")
public class ContainerController {

    private Logger logger = LoggerFactory.getLogger(ContainerController.class);

    @Autowired
    ContainerService containerService;

    @GetMapping("/list")
    public List<Container> getAllContainers() {
        return containerService.getAllContainers();
    }

    @GetMapping("/{id}")
    public Container retrieveContainer(@PathVariable long id) {
        Optional<Container> container = containerService.getContainerById(id);
        if (!container.isPresent())
            throw new ContainerNotFoundException();
        return container.get();
    }

    @PostMapping("/save")
    public ResponseEntity<Object> createContainer(@RequestBody Container container) {
        Container savedContainer = containerService.save(container);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedContainer.getContainerId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateContainer(@RequestBody Container container, @PathVariable long id) {
        Optional<Container> containerOptional = containerService.getContainerById(id);
        if (!containerOptional.isPresent())
            return ResponseEntity.notFound().build();
        container.setContainerId(id);
        containerService.save(container);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteContainer(@PathVariable long id) {
        containerService.deleteById(id);
    }
}
