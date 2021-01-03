package CoffeeMachine.models.response;

import java.util.List;

public class BeverageAggregateDetails {

    private double taxLevied;
    private List<BeverageIngridientMeta> beverageIngridientMeta;

    private double totalCost;

    public BeverageAggregateDetails(double taxLevied, List<BeverageIngridientMeta> beverageIngridientMeta, double totalCost) {
        this.taxLevied = taxLevied;
        this.beverageIngridientMeta = beverageIngridientMeta;
        this.totalCost = totalCost;
    }

    public double getTaxLevied() {
        return taxLevied;
    }

    public List<BeverageIngridientMeta> getBeverageIngridientMeta() {
        return beverageIngridientMeta;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
