package ar.com.farfa.bankingcli.presentation;

import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
    }

    public String askNotNullString(String message) {
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

    public int askForDNI(String message) {
        int dni = 0;
        do {
            System.out.print(message);

            if (!scanner.hasNextInt()) {
                showErrorMessage("Error! Invalid DNI number");
            } else  {
                dni = scanner.nextInt();
                if(dni < 5000000 || dni > 9999999)
                {
                    showErrorMessage("Error! Invalid DNI number");
                    dni = 0;
                }
            }
        }   while (dni == 0);
        return dni;
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

    public int askFourDigitsPIN(String message) {
        int pin = 0;
        do {
            System.out.print(message);

            if (!scanner.hasNextInt()) {
                showErrorMessage("Error! Invalid PIN number");
            } else  {
                pin = scanner.nextInt();
                if(pin < 1000 || pin > 9999)
                {
                    showErrorMessage("Error! Invalid PIN number");
                    pin = 0;
                }
            }
        }   while (pin == 0);
        return pin;
    }
}

