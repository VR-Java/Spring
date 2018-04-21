package com.logos.ua.autobazar.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "car")
@NoArgsConstructor
@Getter
@Setter
public class CarEntity extends BaseEntity {

    @Column(name = "sell_price", columnDefinition = "DECIMAL(5,2)")
    private BigDecimal sellPrice;

    @ManyToOne
    @JoinColumn(name = "make_id")
    private MakeEntity make;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "car_seller",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "seller_id"))
    private List<SellerEntity> sellers;

    @Override
    public String toString() {
        return "CarEntity{" +
                "sellPrice=" + sellPrice +
                ", make=" + make +
                '}';
    }
}
