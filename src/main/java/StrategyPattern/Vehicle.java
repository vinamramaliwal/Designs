package StrategyPattern;

import StrategyPattern.Strategy.DriveStrategy;

public class Vehicle {
    DriveStrategy driveStrategy;
    Vehicle(DriveStrategy driveStrategy){
        this.driveStrategy = driveStrategy;
    }
    void drive(){
driveStrategy.drive();
    }
}
