package com.logos.ua.autobazar.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "make")
@NoArgsConstructor
@Getter
@Setter
public class MakeEntity extends BaseEntity {

    @Column(name = "make_title", unique = true)
    private String makeTitle;

    @Column(name = "manufacture_year")
    private Integer manufactureYear;

    @OneToMany(mappedBy = "make")
    private List<CarEntity> cars;

    @OneToMany(mappedBy = "carMake")
    private List<ModelEntity> models;

    @Override
    public String toString() {
        return makeTitle + ", " + manufactureYear;
    }
}
