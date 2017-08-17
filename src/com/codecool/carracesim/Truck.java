package com.codecool.carracesim;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

// speed: 100km/h. 5% chance of breaking down for 2 hours.
// Truck drivers are boring. They call all their trucks a random number between 0 and 1000.
// breakdownTurnsLeft // holds how long its still broken down.
// distanceTraveled
// moveForAnHour()

public class Truck {

    static int BREAKDOWN_CHANCE_PERCENT = 5;
    static int SPEED = 100;
    static int MAX_CAP_FOR_NAME_NR = 10;
    static int BREAKDOWN_DURATION_HR = 2;

    static Set<Integer> takenNumbers = new HashSet<>();
    Integer name;
    int breakdownTurnsLeft = 0;
    Integer distanceTraveled = 0;

    public Truck(){
        this.name = generateNrAsName();
        this.distanceTraveled = 0;
        this.breakdownTurnsLeft = 0;
    }

    private int generateNrAsName() {
        Random randomNrGenForName = new Random();
        int name = 0;
        boolean nameIsTaken = true;
        while(nameIsTaken){
            name = randomNrGenForName.nextInt(MAX_CAP_FOR_NAME_NR) + 1;
            if (!takenNumbers.contains(name)) {
                nameIsTaken = false;
                takenNumbers.add(name);
            }
        }
        return name;
    }

    private void randomBreakdown() {
        Random randomBreakdown = new Random();
        if (randomBreakdown.nextInt(100) < BREAKDOWN_CHANCE_PERCENT) {
            breakdownTurnsLeft += BREAKDOWN_DURATION_HR;
        }
    }

    public void moveForAnHour() {
        if (breakdownTurnsLeft == 0) {
            distanceTraveled += SPEED;
            randomBreakdown();
        } else {
            breakdownTurnsLeft--;
        }

    }

}