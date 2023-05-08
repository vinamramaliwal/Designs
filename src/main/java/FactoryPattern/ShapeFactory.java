package FactoryPattern;

public class ShapeFactory {

    Shape getShape(String input){
        switch (input){
            case "RECTANGLE":
                return new Rectangle();
            case "CIRCLE":
                return new Circle();
            default:
                return null;
        }
    }
}
