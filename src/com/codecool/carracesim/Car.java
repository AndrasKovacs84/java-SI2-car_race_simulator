package com.codecool.carracesim;
// Since cars are so fast there is a 30% chance that they can go only with 70km/h speed.
// static setSpeedLimit(int limit) // Call this from the Main class!
// normalSpeed // the normal speed of the car. Set to a random number in the constructor between 80-110km/h.
// name // Make a list from the words here: http://www.fantasynamegenerators.com/car-names.php and pick 2 randomly for each instance.
// distanceTraveled // holds the current distance traveled.
// moveForAnHour() // The vehicle travels for an hour. It increases the distance traveled. Call this from the main class only!

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Car {

    static List<String> POSSIBLE_NAME_COMPONENTS = Arrays.asList(
            "Apex", "Tempest", "Prophecy", "Aurora", "Epitome", "Vision", "Serpent", "Vigor", "Method", "Formula");

    static Set<String> takenNames = new HashSet<>();
    static int MIN_SPEED = 80;
    static int MAX_SPEED = 110;

    static int speedLimit = 0;
    int normalSpeed;
    String name;
    Integer distanceTraveled;

    public Car(){
        Random randomGenerator = new Random();
        this.normalSpeed = randomGenerator.nextInt((MAX_SPEED-MIN_SPEED)) + MIN_SPEED;
        this.name = setCarName();
        this.distanceTraveled = 0;
    }

    private String setCarName() {
        String chosenName = "";
        String beginning ="";
        String ending = "";
        boolean nameIsTaken = true;

        Random randomNameGen = new Random();

        while (nameIsTaken) {
            while (beginning.equals(ending)) {
                beginning = POSSIBLE_NAME_COMPONENTS.get(randomNameGen.nextInt(POSSIBLE_NAME_COMPONENTS.size()));
                ending = POSSIBLE_NAME_COMPONENTS.get(randomNameGen.nextInt(POSSIBLE_NAME_COMPONENTS.size()));
                chosenName = beginning + " " + ending;
            }
            if (!takenNames.contains(chosenName)) {
                nameIsTaken = false;
                takenNames.add(chosenName);
            }
            beginning = ending = "";
        }

        return chosenName;
    }

    static void setSpeedLimit(int limit) {
        speedLimit = limit;
    }

    public void moveForAnHour() {
        if (speedLimit != 0) {
            distanceTraveled += speedLimit;
        } else {
            distanceTraveled += normalSpeed;
        }
    }

}