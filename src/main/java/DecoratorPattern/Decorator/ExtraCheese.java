package DecoratorPattern.Decorator;

import DecoratorPattern.BasePizza;

public class ExtraCheese extends ToppinsDecorator{

    BasePizza basePizza;
    ExtraCheese(BasePizza p){
        basePizza=p;
    }
    @Override
    public int returnCost() {
        return basePizza.returnCost()+10;
    }

}
