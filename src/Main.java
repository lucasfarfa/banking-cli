import java.util.Scanner;

public class Main {
    public static void main() {
        AppBank app = new AppBank();
        app.Run();
    }
}

class AppBank
{
    ValidatorUI ui;

    public AppBank ()
    {
        ui = new ValidatorUI();
    }

    public void Run()
    {
        String option;
        do
        {
            option = ui.AskNotNullString(
                    "[1] Crear Cuenta\n" +
                    "[2] Depositar\n" +
                    "[3] Retirar\n" +
                    "[4] Trasnferir entre cuentas\n" +
                    "[5] Ver saldo y ultimas 5 transacciones\n" +
                    "[6] Salir\n");
            switch (option)
            {
                case "1":
                    //
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    ui.closeScanner();
                    break;
                default:
                    ui.ShowErrorMessage("Error! Not in range [1-6]");
                    break;
            }
        } while (!option.equals("6"));
    }
}

class ValidatorUI
{
    private Scanner scanner;

    public ValidatorUI ()
    {
        scanner = new Scanner(System.in);
    }

    public String AskNotNullString(String message)
    {
        Scanner scanner = new Scanner(System.in);
        String retValue;
        do {
            System.out.print(message);
            retValue = scanner.nextLine();
            if(retValue.isBlank())
            {
                ShowErrorMessage("Error! Not null or blank allowed");
            }
        } while (retValue.isBlank());

        return retValue;
    }

    public void ShowErrorMessage(String message)
    {
        System.out.println(message);
    }

    public void closeScanner() {
        scanner.close();
    }
}