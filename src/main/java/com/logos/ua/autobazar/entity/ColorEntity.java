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
@Table(name = "color")
@NoArgsConstructor
@Getter
@Setter
public class ColorEntity extends BaseEntity {

    @Column(name = "color_name")
    private String colorName;

    @OneToMany(mappedBy = "color")
    private List<ModelEntity> models;

    @Override
    public String toString() {
        return colorName + " color";
    }
}
