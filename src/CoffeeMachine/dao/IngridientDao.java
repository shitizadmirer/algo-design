package CoffeeMachine.dao;

import CoffeeMachine.entity.IngridientEntity;
import CoffeeMachine.models.request.OnboardIngridientDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IngridientDao {

    public String saveNewIngridient(OnboardIngridientDetails ingridientDetails) {
        IngridientEntity ingridientEntity = new IngridientEntity(UUID.randomUUID().toString(),
                ingridientDetails.getIngridientName(), ingridientDetails.getIngridientUnitCost());
        return ingridientEntity.getIngridientId();
    }

    public List<IngridientEntity> getIngridients(List<String> ingridientIds){
        return new ArrayList<>();
    }
}
