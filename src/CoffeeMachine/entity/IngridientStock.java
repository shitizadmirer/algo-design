package CoffeeMachine.entity;

public class IngridientStock {

    private String ingridientId;
    private double maximumStock;
    private double currentStock;

    public IngridientStock(String ingridientId, double maxStock, double currentStock){
        this.currentStock = currentStock;
        this.maximumStock = maxStock;
        this.ingridientId = ingridientId;
    }

    public String getIngridientId() {
        return ingridientId;
    }

    public double getMaximumStock() {
        return maximumStock;
    }

    public double getCurrentStock() {
        return currentStock;
    }
}
