package com.tom.vendingmachine.controller;

import com.tom.vendingmachine.dao.PersistenceException;
import com.tom.vendingmachine.dto.Change;
import com.tom.vendingmachine.dto.Item;
import com.tom.vendingmachine.dto.MoneyInMachine;
import com.tom.vendingmachine.service.InsufficientFundsException;
import com.tom.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;
import com.tom.vendingmachine.service.VendingMachineService;

public class Controller {

    private final VendingMachineService service;
    private final VendingMachineView view;
    private MoneyInMachine balance;

    public Controller(VendingMachineService myService, VendingMachineView myView) {
        this.service = myService;
        this.view = myView;
        this.balance = new MoneyInMachine();
    }

    public void run() {
        view.displayWelcomeBanner();
        try {
            showItemsToBuy();
            addToMachine(askUserForMoney());
            view.displayMoneyInMachine(getBalance());
            do {
                int selection = getSelection();
                int exitSelect = service.getAllItemsInStock().size() + 1;
                if (selection == exitSelect) { 
                    break;
                }
                
                Item item = selectionToItem(selection);
                try {
                    BigDecimal change = vendItem(item);
                    if (keepVending()) {
                        showItemsToBuy();
                        view.displayKeepVending();
                        addToMachine(change);
                        view.displayMoneyInMachine(getBalance());
                    } else { 
                        break;
                    }
                } catch (InsufficientFundsException x) {
                    view.displayInsufficientFundsMessage(getBalance(), item);
                    // ask user for more money
                    addToMachine(askUserForMoney());
                    view.displayMoneyInMachine(getBalance());
                    // show items again
                    showItemsToBuy();
                }
            } while (true);      
        } catch (PersistenceException e) {
            view.displayErrorMessage(e.getMessage());
            System.exit(0);
        }

    }

    private void showItemsToBuy() throws PersistenceException {
        view.displayOutOfStock(service.getAllItemsOutOfStock());
        List<Item> itemsInStock = service.getAllItemsInStock();

        // if no items are in stock:        
        if (itemsInStock.isEmpty()) {
            view.displayOutOfService();
            System.exit(0);
        } else {
            view.displayStock(itemsInStock);
        }
    }

    private void addToMachine(BigDecimal amount) {
        this.balance.addAmount(amount);
    }

    private BigDecimal askUserForMoney() {
        return view.getMoneyFromUser();
    }

    private BigDecimal getBalance() {
        return this.balance.getBalance();
    }

    private int getSelection() throws PersistenceException {
        return view.askUserForChoice(1, service.getAllItemsInStock().size() + 1);
    }

    private Item selectionToItem(int choice) throws PersistenceException {
        return service.getAllItemsInStock().get(choice - 1);
    }

    private BigDecimal vendItem(Item item) throws PersistenceException, InsufficientFundsException{
        BigDecimal change = service.vendItem(getBalance(), item);
        view.displaySale(item);
        if (!change.equals(BigDecimal.ZERO)) {
            view.displayChange(new Change(change));            
        } 
        removeFromMachine(getBalance());
        return change;
    }

    private void removeFromMachine(BigDecimal amount) {
        this.balance.removeAmount(amount);
    }

    private boolean keepVending() {
        return view.getKeepVendingChoice();
    }
}
