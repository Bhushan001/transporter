package com.transporter.app.controllers;

import com.transporter.app.entity.Role;
import com.transporter.app.entity.Role;
import com.transporter.app.exception.RoleNotFoundException;
import com.transporter.app.services.RoleService;
import com.transporter.app.services.RoleService;
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
@RequestMapping("/roles")
public class RoleController {

    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    @GetMapping("/list")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Role retrieveRole(@PathVariable long id) throws RoleNotFoundException {
        Optional<Role> role = roleService.getRoleById(id);
        if (!role.isPresent())
            throw new RoleNotFoundException();
        return role.get();
    }

    @PostMapping("/save")
    public ResponseEntity<Object> createRole(@RequestBody Role role) {
        Role savedRole = roleService.save(role);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedRole.getRole_id()).toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRole(@RequestBody Role role, @PathVariable long id) {
        Optional<Role> roleOptional = roleService.getRoleById(id);
        if (!roleOptional.isPresent())
            return ResponseEntity.notFound().build();
        role.setRole_id(id);
        roleService.save(role);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable long id) {
        roleService.deleteById(id);
    }
}
