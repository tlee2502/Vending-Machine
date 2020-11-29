package com.tom.vendingmachine.dao;

import com.tom.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.tom.vendingmachine.dao.VendingMachineDao;
import com.tom.vendingmachine.dto.Item;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineDaoImplTest {
    
    VendingMachineDao testDao;
        
    public VendingMachineDaoImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testvendingmachine.txt";
        new FileWriter(testFile);
        testDao = new VendingMachineDaoFileImpl(testFile);  
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetItem() throws Exception {
        // method test inputs
        String itemName = "Mineral Water";
        Item testItem = new Item("Mineral Water", new BigDecimal("0.45"), 1);
        testDao.addItem(testItem);
        Item retrievedItem = testDao.getItem(itemName);

        assertEquals(testItem, retrievedItem);        
    }
    
    @Test
    public void testGetAll() throws Exception {
        // create two items, add them to DAO
        Item item1 = new Item("Mineral Water", new BigDecimal("0.50"), 3);
        Item item2 = new Item("Juice", new BigDecimal("0.80"), 2);
        
        testDao.addItem(item1);
        testDao.addItem(item2);
        
        List<Item> allItems = testDao.getAllItems();
        
        assertNotNull(allItems, "List of items not null");
        assertEquals(2, allItems.size(), "List of items should have 2 items");
        
        assertTrue(testDao.getAllItems().contains(item1), "List should contain water");
        assertTrue(testDao.getAllItems().contains(item2), "List should contain Juice");
    }
    
    @Test
    public void testSetAmount() throws Exception {
        Item item1 = new Item("Mineral Water", new BigDecimal("0.50"), 3);
        testDao.addItem(item1);
        testDao.setAmount("Mineral Water", 1);
        
        assertEquals(1, item1.getStockAmount(), "Should be 1");
    }
}