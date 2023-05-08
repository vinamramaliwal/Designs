package StrategyPattern;

import StrategyPattern.Strategy.DriveStrategy;
import StrategyPattern.Strategy.SportsDrive;

public class SportsVehicle extends Vehicle{
    SportsVehicle(){
        super(new SportsDrive());
    }
}
