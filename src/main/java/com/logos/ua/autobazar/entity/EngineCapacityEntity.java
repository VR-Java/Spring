package com.logos.ua.autobazar.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "engine_capacity")
@NoArgsConstructor
@Getter
@Setter
public class EngineCapacityEntity extends BaseEntity {

    @Column(name = "engine_capacity", columnDefinition = "DECIMAL(2,1)")
    private BigDecimal engineCapacity;

    @OneToMany(mappedBy = "engineCapacity")
    private List<ModelEntity> models;

    @Override
    public String toString() {
        return "engine " + engineCapacity;
    }
}
