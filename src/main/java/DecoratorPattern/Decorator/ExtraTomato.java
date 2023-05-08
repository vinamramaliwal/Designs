package DecoratorPattern.Decorator;

import DecoratorPattern.BasePizza;

public class ExtraTomato extends ToppinsDecorator{

    BasePizza basePizza;
    ExtraTomato(BasePizza p){
        basePizza=p;
    }

    @Override
    public int returnCost() {
        return 20;
    }
}
