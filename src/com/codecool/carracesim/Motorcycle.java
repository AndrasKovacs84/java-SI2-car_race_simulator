package com.codecool.carracesim;

import java.util.Random;

// speed is 100km/h. If rains, travels with 5-50km/h slower (randomly).
// static nameNumber // The number of the instance created. Used for its name.
// name // Are called "Motorcycle 1", "Motorcycle 2", "Motorcycle 3",... Unique.
// distanceTraveled
// moveForAnHour()
public class Motorcycle {

    static final int BASE_SPEED_WHEN_NOT_RAINING = 100;
    static final int MIN_SPEED_DECREMENT_DUE_TO_RAIN = 5;
    static final int MAX_SPEED_DECREMENT_DUE_TO_RAIN = 50;
    static Integer nameNumber = 1;

    String name;
    Integer distanceTraveled;
    int currentSpeed;

    public Motorcycle(){
        this.name = "Motorcycle " + nameNumber.toString();
        this.distanceTraveled = 0;
        nameNumber += 1;
    }

    public void updateSpeed(boolean itIsRaining) {
        Random randomSpeedDecrement = new Random();

        int speedDecrement = randomSpeedDecrement.nextInt(
                MAX_SPEED_DECREMENT_DUE_TO_RAIN - MIN_SPEED_DECREMENT_DUE_TO_RAIN) + MIN_SPEED_DECREMENT_DUE_TO_RAIN;

        if(itIsRaining){
            currentSpeed = BASE_SPEED_WHEN_NOT_RAINING - speedDecrement;
        } else {
            currentSpeed = BASE_SPEED_WHEN_NOT_RAINING;
        }
    }

    public void moveForAnHour() {
        distanceTraveled += currentSpeed;
    }

}