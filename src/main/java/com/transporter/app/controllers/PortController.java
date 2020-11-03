package com.transporter.app.controllers;

import com.transporter.app.entity.Port;
import com.transporter.app.exception.PortNotFoundException;
import com.transporter.app.services.PortService;
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
@RequestMapping("/ports")
public class PortController {

    private Logger logger = LoggerFactory.getLogger(PortController.class);

    @Autowired
    PortService portService;

    @GetMapping("/list")
    public List<Port> getAllPorts() {
        return portService.getAllPorts();
    }

    @GetMapping("/{id}")
    public Port retrievePort(@PathVariable long id) {
        Optional<Port> port = portService.getPortById(id);
        if (!port.isPresent())
            throw new PortNotFoundException();
        return port.get();
    }

    @PostMapping("/save")
    public ResponseEntity<Object> createPort(@RequestBody Port port) {
        Port savedPort = portService.save(port);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPort.getPortId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePort(@RequestBody Port port, @PathVariable long id) {
        Optional<Port> portOptional = portService.getPortById(id);
        if (!portOptional.isPresent())
            return ResponseEntity.notFound().build();
        port.setPortId(id);
        portService.save(port);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deletePort(@PathVariable long id) {
        portService.deleteById(id);
    }
}
