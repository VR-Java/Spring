package com.logos.ua.autobazar.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "model")
@NoArgsConstructor
@Getter
@Setter
public class ModelEntity extends BaseEntity {

    @Column(name = "model_title")
    private String modelTitle;

    @ManyToOne
    @JoinColumn(name = "engine_capacity_id")
    private EngineCapacityEntity engineCapacity;

    @ManyToOne
    @JoinColumn(name = "fuel_type_id")
    private FuelTypeEntity fuelType;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private ColorEntity color;

    @ManyToOne
    @JoinColumn(name = "make_id")
    private MakeEntity carMake;

    @Override
    public String toString() {
        return carMake + ", " + modelTitle + ", " +
                color + ", " + fuelType + ", " +
                engineCapacity + " (id = " + super.getId() + ")";
    }
}
