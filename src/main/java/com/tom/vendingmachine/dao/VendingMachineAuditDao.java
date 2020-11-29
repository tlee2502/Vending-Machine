package com.tom.vendingmachine.dao;

public interface VendingMachineAuditDao {
     
    public void writeAuditEntry(String entry) throws PersistenceException;
    
}
