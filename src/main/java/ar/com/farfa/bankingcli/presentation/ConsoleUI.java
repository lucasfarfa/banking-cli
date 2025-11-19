package ar.com.farfa.bankingcli.presentation;

import java.math.BigDecimal;
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
        final int MIN_DNI = 5000000;
        final int MAX_DNI = 99999999;

        String entrada;
        boolean inputValido = false;
        do {
            System.out.print(message);
            entrada = scanner.nextLine();

            try {
                dni = Integer.parseInt(entrada); //intento convertir a integer

                if(dni < MIN_DNI || dni > MAX_DNI) // si tiene exito valido rango
                {
                    showErrorMessage("DNI Out of range");
                }
                else
                {
                    inputValido = true;
                }
                // si fallo la conversion
            } catch (NumberFormatException e) {
                showErrorMessage("Formato invalido");
            }
        }   while (!inputValido);
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
        final int MIN_PIN = 1000;
        final int MAX_PIN = 9999;
        String entrada;
        boolean inputValido = false;

        do {
            System.out.print(message);
            entrada = scanner.nextLine();

            try {
                pin = Integer.parseInt(entrada); //intento convertir a integer

                if(pin < MIN_PIN || pin > MAX_PIN) // si tiene exito valido rango
                {
                    showErrorMessage("PIN Out of range");
                }
                else
                {
                    inputValido = true;
                }
                // si fallo la conversion manejo except!
            } catch (NumberFormatException e) {
                showErrorMessage("Formato invalido");
            }
        }   while (!inputValido);
        return pin;
    }

    public BigDecimal pedirMonto(String message) {
        BigDecimal monto = BigDecimal.ZERO;
        String entrada;
        boolean inputValido = false;

        do {
            System.out.print(message);
            entrada = scanner.nextLine();

            try {
                monto = new BigDecimal(entrada);

                if (monto.compareTo(BigDecimal.ZERO) != 1) { // si es menor o igual a 0
                    showErrorMessage("Invalid range, must be a positive number");
                }
                else
                {
                    inputValido = true;
                }
                // si falla conversion:
            } catch (NumberFormatException e)
            {
                showErrorMessage("Invalid number");
            }

        } while (!inputValido);
        return monto;
    }
}

