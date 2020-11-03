package com.transporter.app.services;

import com.transporter.app.entity.Privilege;
import com.transporter.app.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrivilegeService {

    @Autowired
    PrivilegeRepository privilegeRepository;

    public List<Privilege> getAllPrivileges(){
        return privilegeRepository.findAll();
    }

    public Optional<Privilege> getPrivilegeById(Long id) {
        return privilegeRepository.findById(id);
    }

    public Privilege save(Privilege privilege){
        return privilegeRepository.save(privilege);
    }

    public void deleteById(Long id) {
        privilegeRepository.deleteById(id);
    }

}
