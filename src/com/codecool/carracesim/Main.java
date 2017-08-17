package com.codecool.carracesim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

// static isRaining //30% chance of rain every hour
// createVehicles() // creates 10 cars, 10 trucks and 10 motorcycles
// simulateRace() // simulates the race by calling moveForAnHour() on every vehicle 50 times and setting whether its raining.
// printRaceResults() // prints each vehicle's name, distance traveled ant type.
public class Main {

    static int NR_OF_CARS = 10;
    static int SPEED_LIMIT_CHANCE_PERCENT = 30;
    static int SPEED_LIMIT_KMH = 70;

    static int NR_OF_TRUCKS = 10;

    static int NR_OF_MOTORCYCLES = 10;

    static int HOURS_OF_RACE = 50;
    static int RAIN_CHANCE_PERCENT = 30;
    static boolean isRaining = false;

    static List<Car> cars = new ArrayList<>();
    static List<Motorcycle> motorcycles = new ArrayList<>();
    static List<Truck> trucks = new ArrayList<>();
    static List<Map<String, String>> raceResults = new ArrayList<>();


    private static void checkRain(){
       Random randomChanceForRain = new Random();
       int checkWeather = randomChanceForRain.nextInt(100);
       if (checkWeather <= RAIN_CHANCE_PERCENT){
           isRaining = true;
       } else {
           isRaining = false;
       }
    }

    public static void updateCarSpeedLimit(){

        Random speedLimitRandomGen = new Random();

        if(speedLimitRandomGen.nextInt(100) < SPEED_LIMIT_CHANCE_PERCENT) {
            Car.setSpeedLimit(SPEED_LIMIT_KMH);
        } else {
            Car.setSpeedLimit(0);
        }
    }

    public static void createVehicles() {

        for (int i = 0; i < NR_OF_CARS; i++) {
            cars.add(new Car());
        }

        for (int i = 0; i < NR_OF_MOTORCYCLES; i++) {
            motorcycles.add(new Motorcycle());
        }

        for (int i = 0; i < NR_OF_TRUCKS; i++) {
            trucks.add(new Truck());
        }

    }

    public static void simulateRace() {

        for (int i = 0; i < HOURS_OF_RACE; i++) {
            checkRain();
            updateCarSpeedLimit();

            for (Car car: cars) {
                car.moveForAnHour();
            }

            for (Motorcycle motorcycle: motorcycles) {
                motorcycle.updateSpeed(isRaining);
                motorcycle.moveForAnHour();
            }

            for (Truck truck: trucks) {
                truck.moveForAnHour();
            }
        }
    }

    public static void printRaceResults() {

        Map<String, String> currentEntry = new HashMap<>();

        for (Car car: cars) {
            currentEntry = new HashMap<>();
            currentEntry.put("Name", car.name);
            currentEntry.put("Type", "car");
            currentEntry.put("Distance", car.distanceTraveled.toString());
            raceResults.add(currentEntry);
        }

        for (Motorcycle motorcycle: motorcycles) {
            currentEntry = new HashMap<>();
            currentEntry.put("Name", motorcycle.name);
            currentEntry.put("Type", "motorcycle");
            currentEntry.put("Distance", motorcycle.distanceTraveled.toString());
            raceResults.add(currentEntry);
        }

        for (Truck truck: trucks) {
            currentEntry = new HashMap<>();
            currentEntry.put("Name", truck.name.toString());
            currentEntry.put("Type", "truck");
            currentEntry.put("Distance", truck.distanceTraveled.toString());
            raceResults.add(currentEntry);
        }

        Collections.sort(raceResults, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                if (Integer.parseInt(o1.get("Distance")) < Integer.parseInt(o2.get("Distance"))) {
                    return +1;
                } else if (Integer.parseInt(o1.get("Distance")) > Integer.parseInt(o2.get("Distance"))){
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        prettyPrinter(raceResults);
    }

    public static void prettyPrinter(List<Map<String, String>> resultsToPrint) {
        final int SEPARATOR_COMPENSATION = 10; //number of characters to add due to column separators when calculating total line length.
        int col1Width = "Name".length();
        int col2Width = "Type".length();
        int col3Width = "Distance".length();
        int totalLineLength;
        String name, type, distance;

        for (Map<String, String> entry: resultsToPrint) {
            if (entry.get("Name").length() > col1Width) {
                col1Width = entry.get("Name").length();
            }
            if (entry.get("Type").length() > col2Width) {
                col2Width = entry.get("Type").length();
            }
            if (entry.get("Distance").length() > col3Width) {
                col3Width = entry.get("Distance").length();
            }
        }

        System.out.println("| " + String.format("%-"+col1Width+"s", "Name") +
                           " | " + String.format("%-"+col2Width+"s", "Type") +
                           " | " + String.format("%-"+col3Width+"s", "Distance") + " |");

        totalLineLength = col1Width + col2Width + col3Width + SEPARATOR_COMPENSATION;

        for (int i = 0; i < totalLineLength; i++) {
            System.out.print("=");
        }

        System.out.print("\n");

        for (Map<String, String> entry: resultsToPrint) {
            name = String.format("%-"+col1Width+"s", entry.get("Name"));
            type = String.format("%-"+col2Width+"s", entry.get("Type"));
            distance = String.format("%"+col3Width+"s", entry.get("Distance"));
            System.out.println("| " + name + " | " + type + " | " + distance + " |");
        }

    }

    public static void main(String[] args) {
        createVehicles();
        simulateRace();
        printRaceResults();
    }
}
