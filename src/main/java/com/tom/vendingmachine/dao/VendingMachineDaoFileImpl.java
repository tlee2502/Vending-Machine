package com.tom.vendingmachine.dao;

import com.tom.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VendingMachineDaoFileImpl implements VendingMachineDao {

    public static String VM_FILE;
    public static final String DELIMITER = "::";
    private Map<String, Item> inventory = new TreeMap<>();

    public VendingMachineDaoFileImpl() {
        VM_FILE = "vendingmachine.txt";
    }

    public VendingMachineDaoFileImpl(String vmTextFile) {
        VM_FILE = vmTextFile;
    }

    private Item stringToItemObject(String itemAsText) {
        String[] itemParts = itemAsText.split(DELIMITER);
        // item constructor Item(StringName, BigDecPrice, IntStock)
        String stringItemName = itemParts[0];
        BigDecimal stringItemPrice = new BigDecimal(itemParts[1]);
        int stringItemStock = Integer.parseInt(itemParts[2]);
        Item itemFromFile = new Item(stringItemName,stringItemPrice, stringItemStock);
        return itemFromFile;
    }

    private void loadFile() throws PersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(VM_FILE)));
        } catch (FileNotFoundException e) {
            throw new PersistenceException("Could not load data into memory", e);
        }
        String currentLine;
        Item currentItem;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = stringToItemObject(currentLine);
            inventory.put(currentItem.getName(), currentItem);
        }
        scanner.close();
    }

    private String itemObjectToString(Item anItem) {
        // can't use toString() item as we have already overridden it
        String itemAsText = anItem.getName() + DELIMITER + anItem.getPrice()
                + DELIMITER + anItem.getStockAmount();
        return itemAsText;
    }

    private void writeFile() throws PersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(VM_FILE));
        } catch (IOException e) {
            throw new PersistenceException("Could not save item data.", e);
        }
        List<Item> itemList = new ArrayList(inventory.values());
        for (Item currentItem : itemList) {
            out.println(currentItem.getName() + DELIMITER
                    + currentItem.getPrice() + DELIMITER
                    + currentItem.getStockAmount());
            out.flush();
        }
        out.close();
    }

    @Override
    public Item getItem(String itemName) throws PersistenceException {
        loadFile();
        return inventory.get(itemName);
    }

    @Override
    public List<Item> getAllItems() throws PersistenceException {
        loadFile();
        return new ArrayList(inventory.values());
    }

    @Override
    public void setAmount(String name, int amount) throws PersistenceException {
        if (amount < 0) {
            throw new PersistenceException("New stock amount must be >= 0");
        }
        Item myItem = inventory.get(name);
        myItem.setStockAmount(amount);
        inventory.put(name, myItem);        
        writeFile();
    }
    
    // method for adding item to system > for purpose of testing
    @Override
    public void addItem(Item myItem) throws PersistenceException {
        loadFile();
        String name = myItem.getName();
        inventory.put(name, myItem);
        writeFile();
    }

}