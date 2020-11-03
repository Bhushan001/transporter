package com.transporter.app.entity;

import com.transporter.app.base.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
public class Container extends Auditable<String> {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long containerId;
    private String weightType;
    private String numberOfTrucks;
    private boolean isActive;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "container_trucks",
            joinColumns = @JoinColumn(name="container_id"),
            inverseJoinColumns = @JoinColumn(name = "truck_id")
    )
    private Set<Truck> truck;

    public Container(String weightType, String numberOfTrucks, boolean isActive, Set<Truck> truck) {
        this.weightType = weightType;
        this.numberOfTrucks = numberOfTrucks;
        this.isActive = isActive;
        this.truck = truck;
    }
}
