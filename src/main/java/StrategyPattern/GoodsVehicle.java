package StrategyPattern;

import StrategyPattern.Strategy.DriveStrategy;
import StrategyPattern.Strategy.NormalDrive;

public class GoodsVehicle extends Vehicle{

    GoodsVehicle(){
        super(new NormalDrive());
    }
}
