package warehouse.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Scanner;

public class BaseUtils {
    public static Gson gson = new GsonBuilder().setDateFormat("dd.MM.YYYY  HH:mm:ss").setPrettyPrinting().create();
    public static Gson gsonWithNulls = (new GsonBuilder()).setPrettyPrinting().serializeNulls().create();

    private static final Scanner readText;
    private static final Scanner readNumerics;

    static {
        readText = new Scanner(System.in);
        readNumerics = new Scanner(System.in);
    }

    public BaseUtils() {

    }

    public static void print(String data) {
        System.out.print(data);
    }

    public static void println(String data) {
        System.out.println(data);
    }

    public static String readText() {
        return readText.nextLine();
    }

    public static Integer readInteger() {
        return readNumerics.nextInt();
    }

    public static Double readDouble() {
        return readNumerics.nextDouble();
    }

    public static Long readLong() {
        return readNumerics.nextLong();
    }
}
