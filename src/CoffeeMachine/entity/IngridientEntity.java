package CoffeeMachine.entity;

public class IngridientEntity {

    private String ingridientId;

    private String ingridientName;

    private double ingridientUnitCost;

    private double maximumStock;

    private IngridientUnitType ingridientUnitType;

    public IngridientEntity(String ingridientId, String ingridientName, double ingridientUnitCost,
                            double maximumStock, IngridientUnitType unitType){
        this.ingridientId = ingridientId;
        this.ingridientName = ingridientName;
        this.ingridientUnitCost = ingridientUnitCost;
        this.maximumStock = maximumStock;
        this.ingridientUnitType = unitType;
    }

    public String getIngridientId() {
        return ingridientId;
    }

    public String getIngridientName() {
        return ingridientName;
    }

    public double getIngridientUnitCost() {
        return ingridientUnitCost;
    }

    public double getMaximumStock() {
        return maximumStock;
    }

    public IngridientUnitType getIngridientUnitType() {
        return ingridientUnitType;
    }
}
