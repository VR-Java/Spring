package com.logos.ua.autobazar.utils;

import com.logos.ua.autobazar.entity.ColorEntity;
import com.logos.ua.autobazar.entity.EngineCapacityEntity;
import com.logos.ua.autobazar.entity.FuelTypeEntity;
import com.logos.ua.autobazar.entity.MakeEntity;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Random;

public class AutoUtils {

    public static void writeStandardDataToDB(EntityManager em) {
        em.getTransaction().begin();
        writeEngineCapacities(em);
        writeColors(em);
        writeCarMakes(em);
        writeFuelTypes(em);
        em.getTransaction().commit();
    }

    private static void writeEngineCapacities(EntityManager em) {
        for (int i = 0; i < Constants.ENGINE_CAPACITIES.size(); i++) {
            EngineCapacityEntity capacity = new EngineCapacityEntity();
            capacity.setEngineCapacity(BigDecimal.valueOf(Constants.ENGINE_CAPACITIES.get(i)));
            em.persist(capacity);
        }
    }

    private static void writeColors(EntityManager em) {
        for (int i = 0; i < Constants.COLORS.size(); i++) {
            ColorEntity color = new ColorEntity();
            color.setColorName(Constants.COLORS.get(i).toString());
            em.persist(color);
        }
    }

    private static void writeCarMakes(EntityManager em) {
        Random random = new Random();
        for (int i = 0; i < Constants.MAKE_TITLES.size(); i++) {
            MakeEntity make = new MakeEntity();
            make.setMakeTitle(Constants.MAKE_TITLES.get(i).toString());
            int randomYear = random.nextInt(Constants.MAX_YEAR - Constants.MIN_YEAR + 1) + Constants.MIN_YEAR;
            make.setManufactureYear(randomYear);
            em.persist(make);
        }
    }

    private static void writeFuelTypes(EntityManager em) {
        for (int i = 0; i < Constants.FUEL_TYPES.size(); i++) {
            FuelTypeEntity fuel = new FuelTypeEntity();
            fuel.setFuelName(Constants.FUEL_TYPES.get(i).toString());
            em.persist(fuel);
        }
    }

}
