package com.transporter.app.services;

import com.transporter.app.entity.State;
import com.transporter.app.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    StateRepository stateRepository;

    public List<State> getAllStates(){
        return stateRepository.findAll();
    }

    public Optional<State> getStateById(Long id) {
        return stateRepository.findById(id);
    }

    public State save(State state){
        return stateRepository.save(state);
    }

    public void deleteById(long id) {
        stateRepository.deleteById(id);
    }

}
