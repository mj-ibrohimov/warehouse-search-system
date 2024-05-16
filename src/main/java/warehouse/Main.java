package warehouse;

import warehouse.ui.AppUI;
import warehouse.utils.BaseUtils;

import java.io.IOException;

public class Main {
    static AppUI appUI = new AppUI();

    public static void main(String[] args) throws IOException {
        printBanner();
        appUI.run();
    }

    private static void printBanner() {
        BaseUtils.println("\n\n\033[0;36m*************** Project: Printed Product Warehouse *****************\033[0m");
        BaseUtils.println("\033[0;33m--------------- Author: Muhammadjon Ibrohimov  ---------------\033[0m");
        BaseUtils.println("\033[0;33m--------------- Email: Muhammadjon_Ibrohimov@student.itpu.uz ---------------\033[0m");
        BaseUtils.println("\033[0;33m--------------- Creation starting date: since 07.03.2024 ---------------\033[0m");
        BaseUtils.println("\033[0;33m--------------- Version: version-0.0.1 ---------------\033[0m");
    }
}
