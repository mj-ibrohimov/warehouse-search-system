// CommandLineInterface.java
package console;

import service.ProductService;

import java.util.Scanner;

public class CommandLineInterface {
    private ProductService productService;
    private Scanner scanner;

    public CommandLineInterface(ProductService productService) {
        this.productService = productService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            String command = parts[0];
            switch (command) {
                case "search":
                    // Handle search command
                    break;
                case "list":
                    // Handle list command
                    break;
                case "exit":
                    running = false;
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }
    }
}
