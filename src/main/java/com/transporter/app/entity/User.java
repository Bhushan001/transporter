package com.transporter.app.entity;

import com.transporter.app.base.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
public class User  {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long user_id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String username;
    private int typeSysCode;
    private Boolean active;

    @ManyToMany(cascade= {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "role_id"))
    private Collection<Role> roles;

    public User(String email, String password, String firstName, String lastName, String mobileNumber, String username, int typeSysCode, Boolean active) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.typeSysCode = typeSysCode;
        this.active = active;
    }
}
