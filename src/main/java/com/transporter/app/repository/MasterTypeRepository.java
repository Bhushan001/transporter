package com.transporter.app.repository;

import com.transporter.app.entity.MasterType;
import com.transporter.app.entity.Port;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterTypeRepository extends JpaRepository<MasterType, Long> {
    List<MasterType> findAll();
}
