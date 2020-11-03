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
public class State extends Auditable<String> {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long stateId;
    private String stateName;
    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "state_location",
            joinColumns = @JoinColumn(name="state_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private Set<Location> locations;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "state_port",
            joinColumns = @JoinColumn(name="state_id"),
            inverseJoinColumns = @JoinColumn(name = "port_id")
    )
    private Set<Port> ports;

    public State(String stateName, boolean isActive) {
        this.stateName = stateName;
        this.isActive = isActive;
    }
}
