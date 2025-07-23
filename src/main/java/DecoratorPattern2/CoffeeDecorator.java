package DecoratorPattern2;

public abstract class CoffeeDecorator implements Coffee{

    Coffee coffee;

    public CoffeeDecorator(Coffee coffee){
        this.coffee = coffee;
    }
    @Override
    public int getCost() {
        return coffee.getCost();
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }
}
