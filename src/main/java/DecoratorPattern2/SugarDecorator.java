package DecoratorPattern2;

public class SugarDecorator extends CoffeeDecorator{

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int getCost() {
        return super.getCost() + 2;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with Sugar";
    }

}
