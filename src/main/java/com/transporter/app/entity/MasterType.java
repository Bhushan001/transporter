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
public class MasterType extends Auditable<String> {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long masterTypeId;
    private String masterType;
    private String sourceType;
    private String destinationType;
    private boolean isActive;
}
