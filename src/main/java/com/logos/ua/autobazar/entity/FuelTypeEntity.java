package com.logos.ua.autobazar.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fuel_type")
@NoArgsConstructor
@Getter
@Setter
public class FuelTypeEntity extends BaseEntity {

    @Column(name = "fuel_name")
    private String fuelName;

    @OneToMany(mappedBy = "fuelType")
    private List<ModelEntity> models;

    @Override
    public String toString() {
        return "fuel " + fuelName;
    }
}
