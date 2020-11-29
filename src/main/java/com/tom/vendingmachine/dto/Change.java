package com.tom.vendingmachine.dto;

import java.math.BigDecimal;

public class Change {

    private int penny, nickel, dime, quarter;

    public Change(BigDecimal total, BigDecimal itemPrice) {
        BigDecimal changeAmount = total.subtract(itemPrice);
        
        if (changeAmount.doubleValue() < 0) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        BigDecimal[] parts = changeAmount.divideAndRemainder(Coin.QUARTER.getValue());
        // parts has two elements, firstly divisor, secondly the remainder
        // here we divide the amount of change by 0.25
        quarter = parts[0].intValue(); // set the number of quarters equal to the result of the divison
        changeAmount = parts[1]; // set the remainder as the change
        // work our way down (quarter > dime > nickel > penny) until changeAmount is zero
        
        parts = changeAmount.divideAndRemainder(Coin.DIME.getValue());
        dime = parts[0].intValue();
        changeAmount = parts[1];
        
        parts = changeAmount.divideAndRemainder(Coin.NICKEL.getValue());
        nickel = parts[0].intValue();
        changeAmount = parts[1];
        
        parts = changeAmount.divideAndRemainder(Coin.PENNY.getValue());
        penny = parts[0].intValue();
    }
    
    public Change(BigDecimal change) {
        this(change, new BigDecimal("0.00"));
    }

    public int getPenny() {
        return penny;
    }

    public int getNickel() {
        return nickel;
    }

    public int getDime() {
        return dime;
    }

    public int getQuarter() {
        return quarter;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.penny;
        hash = 17 * hash + this.nickel;
        hash = 17 * hash + this.dime;
        hash = 17 * hash + this.quarter;
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
        final Change other = (Change) obj;
        if (this.penny != other.penny) {
            return false;
        }
        if (this.nickel != other.nickel) {
            return false;
        }
        if (this.dime != other.dime) {
            return false;
        }
        if (this.quarter != other.quarter) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return quarter + " quarter(s), " + dime + " dime(s), " + nickel + " nickel(s), "
                + penny + " penny(ies).";
    }

}
