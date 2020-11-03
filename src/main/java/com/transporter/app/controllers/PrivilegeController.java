package com.transporter.app.controllers;

import com.transporter.app.entity.Privilege;
import com.transporter.app.exception.PrivilegeNotFoundException;
import com.transporter.app.services.PrivilegeService;
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
@RequestMapping("/privileges")
public class PrivilegeController {

    private Logger logger = LoggerFactory.getLogger(PrivilegeController.class);

    @Autowired
    PrivilegeService privilegeService;

    @GetMapping("/list")
    public List<Privilege> getAllPrivileges() {
        return privilegeService.getAllPrivileges();
    }

    @GetMapping("/{id}")
    public Privilege retrievePrivilege(@PathVariable long id) {
        Optional<Privilege> privilege = privilegeService.getPrivilegeById(id);
        if (!privilege.isPresent())
            throw new PrivilegeNotFoundException();
        return privilege.get();
    }

    @PostMapping("/save")
    public ResponseEntity<Object> createPrivilege(@RequestBody Privilege privilege) {
        Privilege savedPrivilege = privilegeService.save(privilege);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPrivilege.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePrivilege(@RequestBody Privilege privilege, @PathVariable long id) {
        Optional<Privilege> privilegeOptional = privilegeService.getPrivilegeById(id);
        if (!privilegeOptional.isPresent())
            return ResponseEntity.notFound().build();
        privilege.setId(id);
        privilegeService.save(privilege);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deletePrivilege(@PathVariable long id) {
        privilegeService.deleteById(id);
    }
}
