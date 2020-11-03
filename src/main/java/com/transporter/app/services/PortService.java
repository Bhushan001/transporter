package com.transporter.app.services;

import com.transporter.app.entity.Port;
import com.transporter.app.repository.PortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortService {

    @Autowired
    PortRepository portRepository;

    public List<Port> getAllPorts(){
        return portRepository.findAll();
    }

    public Optional<Port> getPortById(Long id) {
        return portRepository.findById(id);
    }

    public Port save(Port port){
        return portRepository.save(port);
    }

    public void deleteById(long id) {
        portRepository.deleteById(id);
    }

}
