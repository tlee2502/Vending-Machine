package com.tom.vendingmachine.service;

import com.tom.vendingmachine.service.VendingMachineService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.tom.vendingmachine.dto.Item;
import java.math.BigDecimal;
/*
import com.mthree.vendingmachine.dao.VendingMachineAuditDao;
import com.mthree.vendingmachine.dao.VendingMachineDao'
*/

public class VendingMachineServiceLayerImplTest {
    
    private VendingMachineService service;
    
    public VendingMachineServiceLayerImplTest() {
        /*
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
        service = new VendingMachineServiceLayerImpl(dao, auditDao);
        */
        ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingMachineService.class);
        
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetAllItems() throws Exception {
        Item testItem = new Item("Water", new BigDecimal("0.40"), 3);
       
        assertEquals(1, service.getAllItems().size(), "Should only have 1 items");
        assertFalse(service.getAllItems().contains(testItem), "Doesn't contain water");
    }
    
    @Test
    public void getItem() throws Exception {
        Item testItem = new Item("Water", new BigDecimal("0.40"), 3);
        Item shouldBeWater = service.getItem("Water");
        assertNotNull(shouldBeWater, "Getting water should not be null");
        assertEquals(testItem.getName(), shouldBeWater.getName(), "item returned should be water");
    }
}
