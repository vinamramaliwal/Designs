package DecoratorPattern2;

public class MilkDecorator extends CoffeeDecorator{
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int getCost() {
        return super.getCost() + 3;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with Milk";
    }
}
