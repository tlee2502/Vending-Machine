package com.tom.vendingmachine.dao;

import com.tom.vendingmachine.dto.Item;
import java.util.List;

public interface VendingMachineDao {
    Item getItem(String itemName) throws PersistenceException;
    List<Item> getAllItems() throws PersistenceException; 
    void setAmount(String name, int newAmount) throws PersistenceException;
    void addItem(Item item) throws PersistenceException;
 }