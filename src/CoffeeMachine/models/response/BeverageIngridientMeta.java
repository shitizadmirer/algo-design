package CoffeeMachine.models.response;

import CoffeeMachine.entity.BeverageIngridient;

public class BeverageIngridientMeta {

    private double ingridientCost;
    private BeverageIngridient beverageIngridient;

    public BeverageIngridientMeta(double ingridientCost, BeverageIngridient beverageIngridient) {
        this.ingridientCost = ingridientCost;
        this.beverageIngridient = beverageIngridient;
    }

    public double getIngridientCost() {
        return ingridientCost;
    }

    public BeverageIngridient getBeverageIngridient() {
        return beverageIngridient;
    }
}

