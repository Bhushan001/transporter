package com.transporter.app.repository;

import com.transporter.app.entity.Location;
import com.transporter.app.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findAll();
}
