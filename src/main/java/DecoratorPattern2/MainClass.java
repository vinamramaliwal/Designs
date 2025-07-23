package DecoratorPattern2;

public class MainClass {

    public static void main(String []args){
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());

        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());

    }
    /*
    The Decorator Pattern allows you to add responsibilities (behaviors) to objects at runtime without
     changing their class.
It follows the principle: composition over inheritance.

 Real-World Analogy
Inheritance: You bake a cake with chocolate and cream inside — can't change it once done.
Composition: You make a base cake, then add chocolate, then add cream — can remove or change the toppings anytime.

It prevents us from creating multiple classes.

| Inheritance                       | Composition                  |
| --------------------------------- | ---------------------------- |
| Statically extend behavior        | Dynamically add behavior     |
| Tight coupling                    | Loose coupling               |
| Rigid hierarchy                   | Flexible structure           |
| Code reuse via base class         | Code reuse via delegation    |
| Cannot change behavior at runtime | Can layer/compose at runtime |

     */
}
