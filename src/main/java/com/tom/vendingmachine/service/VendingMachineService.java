package com.tom.vendingmachine.service;

import com.tom.vendingmachine.dao.PersistenceException;
import com.tom.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineService {
    public Item getItem(String name) throws PersistenceException, NoItemInventoryException;  
    public void setStock(String name, int newCount) throws PersistenceException;    
    public List<Item> getAllItems() throws PersistenceException;
    public List<Item> getAllItemsInStock() throws PersistenceException;   
    public List<Item> getAllItemsOutOfStock() throws PersistenceException;  
    public BigDecimal vendItem(BigDecimal amountInMachine, Item myItem) throws InsufficientFundsException, PersistenceException; 
}
