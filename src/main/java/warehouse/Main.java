package warehouse;

import warehouse.ui.AppUI;
import warehouse.utils.BaseUtils;

import java.io.IOException;

public class Main {
    static AppUI appUI=new AppUI();
    public static void main(String[] args) throws IOException {

        BaseUtils.println("\n\n*************** Project: Printed Product Warehouse *****************");
        BaseUtils.println("--------------- Author: Muhammadjon Ibrohimov  ---------------");
        BaseUtils.println("--------------- Email: Muhammadjon_Ibrohimov@student.itpu.uz ---------------");
        BaseUtils.println("--------------- Creation starting date: since 07.03.2024 ---------------");
        BaseUtils.println("--------------- Version: version-0.0.1 ---------------");
        appUI.run();
    }
}