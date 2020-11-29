package com.tom.vendingmachine;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.tom.vendingmachine.controller.Controller;
/*
import com.mthree.vendingmachine.dao.VendingMachineAuditDao;
import com.mthree.vendingmachine.dao.VendingMachineAuditDaoImpl;
import com.mthree.vendingmachine.dao.VendingMachineDao;
import com.mthree.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.mthree.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.mthree.vendingmachine.ui.UserIO;
import com.mthree.vendingmachine.ui.UserIOImpl;
import com.mthree.vendingmachine.ui.VendingMachineView;
import com.mthree.vendingmachine.service.VendingMachineService;
*/
public class VendingMachineApp {

    public static void main(String[] args) {
        /*
        VendingMachineDao dao = new VendingMachineDaoFileImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoImpl();
        VendingMachineService service = new VendingMachineServiceLayerImpl(dao, auditDao);
        UserIO io = new UserIOImpl();
        VendingMachineView view = new VendingMachineView(io);
        Controller myController = new Controller(service, view);
        myController.run();
        */
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Controller myController = ctx.getBean("controller", Controller.class);
        myController.run();
        
    }
    
}
