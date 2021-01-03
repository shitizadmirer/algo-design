package CoffeeMachine.entity;

public class BeverageIngridient {

    private String ingridientId;
    private String ingridientName;
    private double quantity;

    public BeverageIngridient(String ingridientId, String ingridientName, double quantity) {
        this.ingridientId = ingridientId;
        this.ingridientName = ingridientName;
        this.quantity = quantity;
    }

    public String getIngridientId() {
        return ingridientId;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getIngridientName() {
        return ingridientName;
    }


}
