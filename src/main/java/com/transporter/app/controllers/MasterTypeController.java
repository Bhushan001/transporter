package com.transporter.app.controllers;

import com.transporter.app.entity.MasterType;
import com.transporter.app.exception.MasterTypeNotFoundException;
import com.transporter.app.services.MasterTypeService;
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
@RequestMapping("/masterTypes")
public class MasterTypeController {

    private Logger logger = LoggerFactory.getLogger(MasterTypeController.class);

    @Autowired
    MasterTypeService masterTypeService;

    @GetMapping("/list")
    public List<MasterType> getAllMasterTypes() {
        return masterTypeService.getAllMasterTypes();
    }

    @GetMapping("/{id}")
    public MasterType retrieveMasterType(@PathVariable long id) {
        Optional<MasterType> masterType = masterTypeService.getMasterTypeById(id);
        if (!masterType.isPresent())
            throw new MasterTypeNotFoundException();
        return masterType.get();
    }

    @PostMapping("/save")
    public ResponseEntity<Object> createMasterType(@RequestBody MasterType inputmasterType) {
        MasterType savedMasterType = masterTypeService.save(inputmasterType);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedMasterType.getMasterTypeId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMasterType(@RequestBody MasterType masterType, @PathVariable long id) {
        Optional<MasterType> masterTypeOptional = masterTypeService.getMasterTypeById(id);
        if (!masterTypeOptional.isPresent())
            return ResponseEntity.notFound().build();
        masterType.setMasterTypeId(id);
        masterTypeService.save(masterType);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteMasterType(@PathVariable long id) {
        masterTypeService.deleteById(id);
    }
}
