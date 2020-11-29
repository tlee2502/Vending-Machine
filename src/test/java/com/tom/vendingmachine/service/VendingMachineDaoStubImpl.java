package com.tom.vendingmachine.service;

import com.tom.vendingmachine.dao.PersistenceException;
import com.tom.vendingmachine.dao.VendingMachineDao;
import com.tom.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    public Item onlyItem;
    
    public VendingMachineDaoStubImpl() {
        onlyItem = new Item("Water", new BigDecimal("0.40"), 2);
    }
    
    public VendingMachineDaoStubImpl(Item testItem) {
        this.onlyItem = testItem;
    }

    @Override
    public Item getItem(String itemName) throws PersistenceException {
        if (itemName.equals(onlyItem.getName())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public List<Item> getAllItems() throws PersistenceException {
        List<Item> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;
    }

    @Override
    public void setAmount(String name, int newAmount) throws PersistenceException {
        onlyItem.setStockAmount(newAmount);
    }

    @Override
    public void addItem(Item item) throws PersistenceException {
        if (item.getName().equals(onlyItem.getName())) {
            System.out.println(onlyItem.toString());
        } 
    }
    
}