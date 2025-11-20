package ar.com.farfa.bankingcli.presentation;
import ar.com.farfa.bankingcli.service.BankingService;

import java.math.BigDecimal;

public class BankingMenu
{
    private final ConsoleUI ui;
    private final BankingService bankingService;

    public BankingMenu()
    {
        ui = new ConsoleUI();
        bankingService = new BankingService();
    }

    public void Ejecutar()
    {
        showMenu();
        bankingService.guardarCuentasEnArchivo();       // guardo en archivo
    }

    private void showMenu()
    {
        String option;
        do
        {
            option = ui.askNotNullString( // esto es una "Text Box"
                    """
                            [1] Crear Cuenta
                            [2] Depositar
                            [3] Retirar
                            [4] Trasnferir entre cuentas
                            [5] Ver saldo y ultimas 5 transacciones
                            [6] Salir
                            """);
            switch (option)
            {
                case "1" -> crearCuenta();
                case "2" -> depositar();
                case "3" -> retirar();
                case "4" -> trasnferirEntreCuentas();
                case "5" -> verSaldo();
                case "6" -> {
                    ui.showSuccessMessage("Saliendo del sistema");
                    ui.closeScanner();
                }
                default ->  ui.showErrorMessage("Not in range [1-6]");
            }
        } while (!option.equals("6"));
    }

    // Estos metodos piden datos al usuario e interactuan con bankingservice para realizar las acciones del menu
    // la logica de negocio se realiza en servicio

    private void crearCuenta() {
        int dni;
        do {
            dni = ui.askForDNI("Ingrese DNI: "); // valida el formato
            if (bankingService.existeDNI(dni))
            {
                ui.showErrorMessage("El DNI" + dni + " ya esta registrado.");
            }
        } while (bankingService.existeDNI(dni));

        String nombre = ui.askNotNullString("Ingrese nombre completo: ");
        int pin = ui.askFourDigitsPIN("Ingrese PIN: ");

        bankingService.crearCuenta(dni, nombre, pin);
        ui.showSuccessMessage("Cuenta creada con exito");
    }

    private void depositar() {
        int dni = obtenerDNI();
        BigDecimal monto = ui.pedirMonto("Ingrese monto: ");
        bankingService.depositar(dni, monto);
        ui.showSuccessMessage("Deposito de $" + monto + " realizado.");
    }

    private void retirar() {
        int dni = obtenerDNI();
        BigDecimal monto = ui.pedirMonto("Ingrese monto: ");

        // monto debe ser <= a saldo para validar
        if(!bankingService.validarSaldo(dni, monto))
        {
            ui.showErrorMessage("Saldo insuficiente");
        }
        else {
            bankingService.retirar(dni, monto);
            ui.showSuccessMessage("Deposito de $" + monto + " realizado.");
        }


    }

    private void trasnferirEntreCuentas() {
        int dniOrigen = obtenerDNI();
        int dniDestino = obtenerDNI();
        BigDecimal monto = ui.pedirMonto("Ingrese monto: ");

        if(!bankingService.validarSaldo(dniOrigen, monto))
        {
            ui.showErrorMessage("Saldo insuficiente en cuenta Origen");
        }
        else {
            bankingService.transferirEntreCuentas(dniOrigen, dniDestino, monto);
            ui.showSuccessMessage("Deposito de $" + monto + " realizado.");
        }
    }

    private void verSaldo() {
        int dni = obtenerDNI();
        System.out.print(bankingService.mostrarSaldoTransacciones(dni));
    }

    private int obtenerDNI() {
        int dni;
        do {
            dni = ui.askForDNI("Ingrese DNI: "); // valida el formato
            if (!bankingService.existeDNI(dni))
            {
                ui.showErrorMessage("El DNI" + dni + "no existe.");
            }
        } while (!bankingService.existeDNI(dni));
        return dni;
    }

}
