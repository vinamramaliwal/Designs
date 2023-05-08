package FactoryPattern;

public class MainClass {

    public static void main(String args[]){
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape s=shapeFactory.getShape("RECTANGLE");
        s.draw();
    }
}
