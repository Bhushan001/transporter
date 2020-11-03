package com.transporter.app.services;

import com.transporter.app.entity.MasterType;
import com.transporter.app.repository.MasterTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MasterTypeService {

    @Autowired
    MasterTypeRepository masterTypeRepository;

    public List<MasterType> getAllMasterTypes(){
        return masterTypeRepository.findAll();
    }

    public Optional<MasterType> getMasterTypeById(Long id) {
        return masterTypeRepository.findById(id);
    }

    public MasterType save(MasterType masterType){
        return masterTypeRepository.save(masterType);
    }

    public void deleteById(long id) {
        masterTypeRepository.deleteById(id);
    }

}
