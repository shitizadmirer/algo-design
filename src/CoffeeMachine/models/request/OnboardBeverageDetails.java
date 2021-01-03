package CoffeeMachine.models.request;

import CoffeeMachine.entity.BeverageType;
import CoffeeMachine.entity.OrderedIngridient;

import java.util.List;

public class OnboardBeverageDetails {

    private String beverageName;
    private BeverageType beverageType;
    private List<OrderedIngridient> orderedIngridients;

    public OnboardBeverageDetails(String beverageName, BeverageType beverageType, List<OrderedIngridient> orderedIngridients) {
        this.beverageName = beverageName;
        this.beverageType = beverageType;
        this.orderedIngridients = orderedIngridients;
    }

    public String getBeverageName() {
        return beverageName;
    }

    public List<OrderedIngridient> getOrderedIngridients() {
        return orderedIngridients;
    }

    public BeverageType getBeverageType() {
        return beverageType;
    }


}
