package CoffeeMachine.entity;

import java.util.List;

public class BeverageEntity {

    private String beverageId;

    private String beverageName;

    private BeverageType beverageType;

    private List<OrderedIngridient> orderedIngridients;

    public BeverageEntity(String beverageId, BeverageType beverageType,
                          String beverageName, List<OrderedIngridient> orderedIngridients){
        this.beverageId = beverageId;
        this.beverageName = beverageName;
        this.orderedIngridients = orderedIngridients;
    }

    public String getBeverageId() {
        return beverageId;
    }

    public String getBeverageName() {
        return beverageName;
    }

    public List<OrderedIngridient> getOrderedIngridients() {
        return orderedIngridients;
    }
}
