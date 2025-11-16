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
        String xd = ui.AskNotNullString("Test: ");
        System.out.println(xd);
    }
}

class ValidatorUI
{
    public String AskNotNullString(String message)
    {
        Scanner scanner = new Scanner(System.in);
        String retValue;
        do {
            System.out.print(message);
            retValue = scanner.nextLine();
            if(retValue.isBlank())
            {
                System.out.print("Error! Not null or blank allowed\n");
            }
        } while (retValue.isBlank());

        scanner.close();
        return retValue;
    }
}