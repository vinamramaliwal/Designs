package StrategyPattern;

import StrategyPattern.Strategy.SportsDrive;

public class MainClass {
    public static void main(String args[]){

        Vehicle v = new SportsVehicle();
        v.drive();
    }
}
