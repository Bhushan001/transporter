package com.transporter.app.repository;

import com.transporter.app.entity.Location;
import com.transporter.app.entity.Port;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortRepository extends JpaRepository<Port, Long> {
    List<Port> findAll();
}
