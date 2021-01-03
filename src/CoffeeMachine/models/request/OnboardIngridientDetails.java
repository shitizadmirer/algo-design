package CoffeeMachine.models.request;

import CoffeeMachine.entity.IngridientUnitType;

public class OnboardIngridientDetails {

    private String ingridientName;
    private double ingridientUnitCost;
    private double currentStock;
    private double maximumStock;
    private IngridientUnitType ingridientUnitType;

    public String getIngridientName() {
        return ingridientName;
    }

    public double getIngridientUnitCost() {
        return ingridientUnitCost;
    }

    public double getCurrentStock() {
        return currentStock;
    }

    public IngridientUnitType getIngridientUnitType() {
        return ingridientUnitType;
    }

    public double getMaximumStock() {
        return maximumStock;
    }

    public OnboardIngridientDetails(String ingridientName, double ingridientCost, double currentStock, double maximumStock,
                                    IngridientUnitType ingridientUnitType) {
        this.ingridientName = ingridientName;
        this.ingridientUnitCost = ingridientCost;
        this.currentStock = currentStock;
        this.maximumStock = maximumStock;
        this.ingridientUnitType  = ingridientUnitType;
    }


}
