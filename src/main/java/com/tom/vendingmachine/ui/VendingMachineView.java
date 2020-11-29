package com.tom.vendingmachine.ui;

import com.tom.vendingmachine.dto.Change;
import com.tom.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineView {
    
    private final UserIO io;
    
    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
   public void displayWelcomeBanner() {
       io.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
       io.print("Welcome to Tom's Vending Machine");
       io.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
   }
   
   public void displayStock(List<Item> inStockItems) {
       inStockItems.forEach((i) -> {
           io.print("(" + (inStockItems.indexOf(i) + 1) + ") " + i.toString());
       });
       io.print("Enter (" + (inStockItems.size() + 1) + ") to exit.");
   }
   
   public void displayOutOfService() {
       io.print("Machine out of service.");
   }
   
   public BigDecimal getMoneyFromUser() {
       BigDecimal result = null;
       do {
           String input = io.readString("Enter money: $");
           try {
               result = new BigDecimal(input);
           } catch (NumberFormatException e) {
               io.print("Not a valid amount of money.");
           }
       } while (result == null);
       result = result.setScale(2, RoundingMode.DOWN);
       return result;
   }
   
   public void displayMoneyInMachine(BigDecimal balance) {
       io.print("There is $" + balance + " in the machine.");
   }
   
   public int askUserForChoice(int min, int max) {
       return io.readInt("Enter choice: ", min, max);
   }
   
   public void displaySale(Item snack) {
       io.print("A " + snack.getName() + " is dispensed. Enjoy!");
   }
   
   public void displayChange(Change changeAmount) {
       io.print("~There is ");
        if (changeAmount.getQuarter() > 0) {
            io.print(" ~" + changeAmount.getQuarter() + " Quater(s)");
        }
        if (changeAmount.getDime() > 0) {
            io.print(" ~" + changeAmount.getDime() + " Dime(s)");
        }
        if (changeAmount.getNickel() > 0) {
            io.print(" ~" + changeAmount.getNickel() + " Nickle(s)");
        }
        if (changeAmount.getPenny() > 0) {
            io.print(" ~" + changeAmount.getPenny() + " Penny(ies)");
        }
        io.print("  ~in change.");
   }
   
   public void displayInsufficientFundsMessage(BigDecimal amountInMachine, Item snack) {
       BigDecimal AddAmount = snack.getPrice().subtract(amountInMachine);
       io.print("Insufficient funds for " + snack.getName() + ". Only $" 
               + amountInMachine + " in machine, Please insert $" + AddAmount + " to purchase.");
   }
   
   public void displayThankYouMessage() {
       io.print("Thank you for using this machine~ See you next time!");
   }
   
   public void displayErrorMessage(String msg) {
       io.print(msg);
   }
   
   public boolean getKeepVendingChoice() {
       int choice = io.readInt("Buy something else? Enter 1 for Yes, 0 for No.", 0, 1);
       return choice == 1 ? true : false; 
   }
   
   public void displayKeepVending() {
       io.print("You insert your change back into the machine");
   }
   
   public void displayOutOfStock(List<Item> allItemsOutOfStock) {
        allItemsOutOfStock.forEach((item) -> {
            io.print(item.toString());
        });
    }
}