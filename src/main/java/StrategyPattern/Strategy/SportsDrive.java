package StrategyPattern.Strategy;

public class SportsDrive implements DriveStrategy{
    @Override
    public void drive() {
        System.out.println("Sports Drive");
    }
}
