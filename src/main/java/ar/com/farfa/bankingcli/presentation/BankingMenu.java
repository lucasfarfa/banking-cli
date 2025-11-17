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

    public void showMenu()
    {
        String option;
        do
        {
            option = ui.askNotNullString( // esto es una "Text Box"
                    """
                            [1] Crear model.Cuenta
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
                    ui.showSuccessMessage("Saliendo del sistema)");
                    ui.closeScanner();
                }
                default ->  ui.showErrorMessage("Not in range [1-6]");
            }
        } while (!option.equals("6"));
    }
}
