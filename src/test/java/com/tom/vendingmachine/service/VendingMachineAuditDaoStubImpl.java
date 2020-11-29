package com.tom.vendingmachine.service;

import com.tom.vendingmachine.dao.PersistenceException;
import com.tom.vendingmachine.dao.VendingMachineAuditDao;

public class VendingMachineAuditDaoStubImpl implements VendingMachineAuditDao {

    @Override
    public void writeAuditEntry(String entry) throws PersistenceException {
        // do nothing.
    }
    
}
