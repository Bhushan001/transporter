package com.transporter.app.entity;

import com.transporter.app.base.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@Data
@Entity
public class Role  {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long role_id;
    private String role;

    @ManyToMany(cascade= {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    public Role(String role) {
        this.role = role;
    }
}
