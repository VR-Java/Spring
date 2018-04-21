package com.logos.ua.autobazar;

import com.logos.ua.autobazar.entity.*;
import com.logos.ua.autobazar.utils.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Random;

public class AutobazarApp {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("mysql");
        EntityManager em = entityManagerFactory.createEntityManager();

        // write standard data (ENGINE_CAPACITIES, COLORS, MAKE_TITLES, FUEL_TYPES) to database
        // only once, at fist launch
//        AutoUtils.writeStandardDataToDB(em);

        // creating 100 Models with random characteristics + writing to DB
//        writeModelEntities(100, em);


        em.getTransaction().begin();

        printAllModels(em);

        selectCarModelsByName("Audi", em);
        selectCarModelsAgeBetween(2015, 2018, em);
        deleteCarModelsByEngineCapacity(BigDecimal.valueOf(2.0), em);

        updateCarModelName("Super model", em);

        System.out.println();
        printAllModels(em);

        em.getTransaction().commit();
        em.close();
        entityManagerFactory.close();
    }

    private static void writeModelEntities(int quantity, EntityManager em) {
        em.getTransaction().begin();
        Random random = new Random();

        int makeSize = Constants.MAKE_TITLES.size();
        int colorSize = Constants.COLORS.size();
        int engineSize = Constants.ENGINE_CAPACITIES.size();
        int fuelSize = Constants.FUEL_TYPES.size();

        for (int i = 1; i <= quantity; i++) {
            ModelEntity model = new ModelEntity();
            int makeEntityId = random.nextInt(makeSize + 1);
            model.setCarMake((MakeEntity) getEntityById(makeEntityId, MakeEntity.class, em));
            int colorEntityId = random.nextInt(colorSize + 1);
            model.setColor((ColorEntity) getEntityById(colorEntityId, ColorEntity.class, em));
            int engineEntityId = random.nextInt(engineSize + 1);
            model.setEngineCapacity((EngineCapacityEntity) getEntityById(engineEntityId, EngineCapacityEntity.class, em));
            int fuelEntityId = random.nextInt(fuelSize + 1);
            model.setFuelType((FuelTypeEntity) getEntityById(fuelEntityId, FuelTypeEntity.class, em));
            model.setModelTitle("Model x" + i);
            em.persist(model);
        }
        em.getTransaction().commit();
    }

    private static Object getEntityById(Integer id, Class c, EntityManager em) {
        //Strange, but (random.nextInt() + 1) sometimes gives 0
        // in this case we will use 1 instead 0
        if (id == 0) {
            id = 1;
        }
        String query = "select e from " + c.getSimpleName() + " e where e.id = :id";
        System.out.println(query);
        Object object = null;
        try {
            object = em.createQuery(query, c)
                    .setParameter("id", id.longValue())
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("id = " + id);
            System.out.println("Class = " + c.getSimpleName());
            e.printStackTrace();
        }
        return object;
    }

    private static void printAllModels(EntityManager em) {
        em.createQuery("select m from ModelEntity m", ModelEntity.class)
                .getResultList()
                .forEach(System.out::println);
    }

    private static void selectCarModelsByName(String name, EntityManager em) {
        System.out.println("\n   List of all " + name + ":");
        em.createQuery("select m from ModelEntity m where m.carMake.makeTitle = :name", ModelEntity.class)
                .setParameter("name", name)
                .getResultList()
                .forEach(System.out::println);
    }

    private static void selectCarModelsAgeBetween(int minYear, int maxYear, EntityManager em) {
        System.out.println("\n   List of all cars between " + minYear + " and " + maxYear + ":");
        em.createQuery("select m from ModelEntity m where m.carMake.manufactureYear between :minYear and :maxYear", ModelEntity.class)
                .setParameter("minYear", minYear)
                .setParameter("maxYear", maxYear)
                .getResultList()
                .forEach(System.out::println);

    }

    private static void deleteCarModelsByEngineCapacity(BigDecimal maxCapacityAllowed, EntityManager em) {
        em.createQuery("select m from ModelEntity m where m.engineCapacity.engineCapacity > :maxCapacityAllowed", ModelEntity.class)
                .setParameter("maxCapacityAllowed", maxCapacityAllowed)
                .getResultList()
                .forEach(m -> em.remove(m));
    }

    private static void updateCarModelName(String newName, EntityManager em) {
        em.createQuery("select m from ModelEntity m where m.modelTitle like :pattern", ModelEntity.class)
                .setParameter("pattern", "%0")
                .getResultList()
                .forEach(m -> {
                    m.setModelTitle(newName);
                    em.persist(m);
                });
    }
}
