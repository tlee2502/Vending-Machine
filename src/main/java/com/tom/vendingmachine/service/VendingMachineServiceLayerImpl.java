package com.tom.vendingmachine.service;

import com.tom.vendingmachine.dao.PersistenceException;
import com.tom.vendingmachine.dao.VendingMachineAuditDao;
import com.tom.vendingmachine.dao.VendingMachineDao;
import com.tom.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineServiceLayerImpl implements VendingMachineService {

    private final VendingMachineDao dao;
    private final VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public Item getItem(String name) throws PersistenceException, NoItemInventoryException {
        Item myItem = dao.getItem(name);
        if (myItem.getStockAmount() == 0 && myItem != null) {
            throw new NoItemInventoryException(myItem + " not in stock.");
        }
        return myItem;
    }

    @Override
    public void setStock(String name, int newAmount) throws PersistenceException {
        if (newAmount < 0) {
            throw new PersistenceException("newAmount should be >= 0");
        }
        dao.setAmount(name, newAmount);
    }

    @Override
    public List<Item> getAllItems() throws PersistenceException {
        return dao.getAllItems();
    }

    @Override
    public List<Item> getAllItemsInStock() throws PersistenceException {
        return dao.getAllItems()
                .stream()
                .filter((i) -> i.getStockAmount() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> getAllItemsOutOfStock() throws PersistenceException {
        return dao.getAllItems()
                .stream()
                .filter((x) -> x.getStockAmount() <= 0)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal vendItem(BigDecimal amountInMachine, Item myItem) throws InsufficientFundsException, PersistenceException {
        if (myItem.getPrice().compareTo(amountInMachine) > 0) {
            throw new InsufficientFundsException("Insufficient funds.");
        }
        // reduce the stock of item by one after vending
        setStock(myItem.getName(), myItem.getStockAmount() - 1);
        return amountInMachine.subtract(myItem.getPrice());
    }

}
