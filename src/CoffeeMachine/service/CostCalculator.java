package CoffeeMachine.service;

import CoffeeMachine.entity.BeverageType;
import CoffeeMachine.entity.OrderedIngridient;
import CoffeeMachine.models.request.OnboardBeverageDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class CostCalculator {

    private static final Map<BeverageType, Double> taxMap;

    static {
        taxMap = new HashMap<>();
        taxMap.put(BeverageType.COFFEE, 15D);
        taxMap.put(BeverageType.TEA, 10D);
    }

    private BiFunction<BeverageType, Double, Double> taxApplicator =
            (type, cost) -> cost + (taxMap.getOrDefault(type, 0D) * cost) / 100;

    public double calculateCost(OnboardBeverageDetails beverageDetails) {
        double ingridientCost = getIngridientsCost(beverageDetails.getOrderedIngridients());
        return taxApplicator.apply(beverageDetails.getBeverageType(),ingridientCost);
    }

    private double getIngridientsCost(List<OrderedIngridient> orderedIngridients){

    }
}
