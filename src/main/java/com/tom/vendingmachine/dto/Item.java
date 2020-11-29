package com.tom.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    private String name;
    private BigDecimal price;
    private int stockAmount;

    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.stockAmount = 1;
    }
    
    public Item(String name, BigDecimal price, int amount) {
        this.name = name;
        this.price = price;
        this.stockAmount = amount;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStockAmount() {
        return stockAmount;
    }
    
    public void setStockAmount(int amount) {
        this.stockAmount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.price);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.stockAmount != other.stockAmount) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String result = name + " - $" + price + " - Stock: " + stockAmount;
        if (stockAmount <= 0) {
            result = name + " - $" + price + " - Stock: Out of Stock!";
        }
        return result;
    }

}