package StrategyPattern;

import StrategyPattern.Strategy.DriveStrategy;
import StrategyPattern.Strategy.NormalDrive;

public class OffRoadVehicle extends Vehicle{
    OffRoadVehicle(){
        super(new NormalDrive());
    }
}
