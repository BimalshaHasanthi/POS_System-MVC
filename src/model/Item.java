package model;

import java.util.Objects;

public class Item {
    private String itemCode;
    private String description;
    private String packSize;
    private double unitPrice;
    private int qtyOnHand;
    private double discountPercentage;

    public Item(String string, String rstString, int anInt, double aDouble, double rstDouble) {
    }

    public Item(String itemCode, String description, String packSize, double unitPrice, int qtyOnHand, double discountPercentage) {
        this.itemCode = itemCode;
        this.description = description;
        this.packSize = packSize;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
        this.discountPercentage = discountPercentage;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemCode='" + itemCode + '\'' +
                ", description='" + description + '\'' +
                ", packSize='" + packSize + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnHand=" + qtyOnHand +
                ", discountPercentage=" + discountPercentage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.unitPrice, unitPrice) == 0 &&
                qtyOnHand == item.qtyOnHand &&
                Double.compare(item.discountPercentage, discountPercentage) == 0 &&
                Objects.equals(itemCode, item.itemCode) &&
                Objects.equals(description, item.description) &&
                Objects.equals(packSize, item.packSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCode, description, packSize, unitPrice, qtyOnHand, discountPercentage);
    }


}
