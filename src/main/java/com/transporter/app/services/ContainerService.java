package com.transporter.app.services;

import com.transporter.app.entity.Container;
import com.transporter.app.repository.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContainerService {

    @Autowired
    ContainerRepository containerRepository;

    public List<Container> getAllContainers(){
        return containerRepository.findAll();
    }

    public Optional<Container> getContainerById(Long id) {
        return containerRepository.findById(id);
    }

    public Container save(Container container){
        return containerRepository.save(container);
    }

    public void deleteById(long id) {
        containerRepository.deleteById(id);
    }

}
