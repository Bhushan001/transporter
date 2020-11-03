package com.transporter.app.entity;

import com.transporter.app.base.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Truck extends Auditable<String> {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long truckId;
    private String truckNumber;
    private Long containerId;
    private boolean isActive;

    public Truck(String truckNumber, Long containerId, boolean isActive) {
        this.truckNumber = truckNumber;
        this.containerId = containerId;
        this.isActive = isActive;
    }
}
