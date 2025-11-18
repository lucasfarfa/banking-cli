package ar.com.farfa.bankingcli.presentation;
import ar.com.farfa.bankingcli.service.BankingService;

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
        // TODO verificar que el cargar lo puedo hacer desde el constructor de bankingService :)
        showMenu();                                     // trabajo en memoria (estructuras de datos)
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

    private void crearCuenta() {
        int dni;
        do {
            dni = ui.askForDNI("Ingrese DNI: "); // valdia el formato
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
    }

    private void retirar() {
    }

    private void trasnferirEntreCuentas() {
    }

    private void verSaldo() {
    }
}
