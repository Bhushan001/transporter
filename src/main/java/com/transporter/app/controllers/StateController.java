package com.transporter.app.controllers;

import com.transporter.app.entity.State;
import com.transporter.app.exception.StateNotFoundException;
import com.transporter.app.services.StateService;
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
@RequestMapping("/states")
public class StateController {

    private Logger logger = LoggerFactory.getLogger(StateController.class);

    @Autowired
    StateService stateService;

    @GetMapping("/list")
    public List<State> getAllStates() {
        return stateService.getAllStates();
    }

    @GetMapping("/{id}")
    public State retrieveState(@PathVariable long id) {
        Optional<State> state = stateService.getStateById(id);
        if (!state.isPresent())
            throw new StateNotFoundException();
        return state.get();
    }

    @PostMapping("/save")
    public ResponseEntity<Object> createState(@RequestBody State state) {
        State savedState = stateService.save(state);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedState.getStateId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateState(@RequestBody State state, @PathVariable long id) {
        Optional<State> stateOptional = stateService.getStateById(id);
        if (!stateOptional.isPresent())
            return ResponseEntity.notFound().build();
        state.setStateId(id);
        stateService.save(state);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteState(@PathVariable long id) {
        stateService.deleteById(id);
    }
}
