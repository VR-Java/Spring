package com.logos.ua.autobazar.utils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final int MIN_YEAR = 1995;
    public static final int MAX_YEAR = LocalDateTime.now().getYear();

    public static List<Double> ENGINE_CAPACITIES = Arrays.asList(1.2, 1.4, 1.6, 1.8, 1.9, 2.0, 2.2, 2.5, 3.0, 5.0);
    public static List<CarColors> COLORS = Arrays.asList(CarColors.values());
    public static List<CarMakeTitles> MAKE_TITLES = Arrays.asList(CarMakeTitles.values());
    public static List<FuelTypes> FUEL_TYPES = Arrays.asList(FuelTypes.values());

}
