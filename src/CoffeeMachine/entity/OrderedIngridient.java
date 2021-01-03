package CoffeeMachine.entity;

public class OrderedIngridient {

    private int orderInBeverage;
    private BeverageIngridient beverageIngridient;

    public OrderedIngridient(int orderInBeverage, BeverageIngridient beverageIngridient){
        this.orderInBeverage = orderInBeverage;
        this.beverageIngridient = beverageIngridient;
    }

    public int getOrderInBeverage() {
        return orderInBeverage;
    }

    public BeverageIngridient getBeverageIngridient() {
        return beverageIngridient;
    }
}
