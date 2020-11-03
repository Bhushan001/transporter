package com.transporter.app.services;

import com.transporter.app.entity.Weight;
import com.transporter.app.repository.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeightService {

    @Autowired
    WeightRepository weightRepository;

    public List<Weight> getAllWeights(){
        return weightRepository.findAll();
    }

    public Optional<Weight> getWeightById(Long id) {
        return weightRepository.findById(id);
    }

    public Weight save(Weight weight){
        return weightRepository.save(weight);
    }

    public void deleteById(long id) {
        weightRepository.deleteById(id);
    }

}
