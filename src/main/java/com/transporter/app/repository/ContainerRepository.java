package com.transporter.app.repository;

import com.transporter.app.entity.Container;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContainerRepository extends JpaRepository<Container, Long> {
    List<Container> findAll();
}
