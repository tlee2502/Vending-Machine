package com.tom.vendingmachine.dto;

import java.math.BigDecimal;

public class MoneyInMachine {
    
    private BigDecimal inMachine = new BigDecimal("0.00");
    
    public void addAmount(BigDecimal amount) {
        inMachine = inMachine.add(amount);
    }
    
    public void removeAmount(BigDecimal amount) {
        inMachine = inMachine.subtract(amount);
    }
    
    public BigDecimal getBalance() {
        return this.inMachine;
    }
}
