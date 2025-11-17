package ar.com.farfa.bankingcli.presentation;

import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
    }

    public String askNotNullString(String message) {
        Scanner scanner = new Scanner(System.in);
        String retValue;
        do {
            System.out.print(message);
            retValue = scanner.nextLine();
            if (retValue.isBlank()) {
                showErrorMessage("Error! Not null or blank allowed");
            }
        } while (retValue.isBlank());

        return retValue;
    }

    public void showErrorMessage(String message) {
        System.out.println("Error! " + message);
    }

    public void showSuccessMessage(String message) {
        System.out.println(message);
    }

    public void closeScanner() {
        scanner.close();
    }
}

