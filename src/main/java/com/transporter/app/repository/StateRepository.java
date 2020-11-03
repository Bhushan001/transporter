package com.transporter.app.repository;

import com.transporter.app.entity.State;
import com.transporter.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {
    List<State> findAll();
}
