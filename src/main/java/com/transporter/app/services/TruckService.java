package com.transporter.app.services;

import com.transporter.app.entity.Truck;
import com.transporter.app.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TruckService {

    @Autowired
    TruckRepository truckRepository;

    public List<Truck> getAllTrucks(){
        return truckRepository.findAll();
    }

    public Optional<Truck> getTruckById(Long id) {
        return truckRepository.findById(id);
    }

    public Truck save(Truck truck){
        return truckRepository.save(truck);
    }

    public void deleteById(long id) {
        truckRepository.deleteById(id);
    }

}
