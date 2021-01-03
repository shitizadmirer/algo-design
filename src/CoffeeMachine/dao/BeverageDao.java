package CoffeeMachine.dao;

import CoffeeMachine.entity.BeverageEntity;
import CoffeeMachine.models.request.OnboardBeverageDetails;
import com.sun.istack.internal.NotNull;

import java.util.UUID;

public class BeverageDao {

    public String saveNewBeverage(OnboardBeverageDetails beverageDetails) {
        BeverageEntity beverageEntity = new BeverageEntity(UUID.randomUUID().toString(), beverageDetails.getBeverageType(),
                beverageDetails.getBeverageName(), beverageDetails.getOrderedIngridients());
        return beverageEntity.getBeverageId();
    }


    public BeverageEntity getBeverageDetails(@NotNull String beverageName) {
        return null;
    }

}
