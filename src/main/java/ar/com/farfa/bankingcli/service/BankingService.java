package ar.com.farfa.bankingcli.service;
import ar.com.farfa.bankingcli.model.Transaccion;
import ar.com.farfa.bankingcli.model.Cuenta;
import ar.com.farfa.bankingcli.persistence.FileCuentaRepository;
import java.math.BigDecimal;

public class BankingService
{
    private FileCuentaRepository repositorio;

    public BankingService()
    {
        this.repositorio = new FileCuentaRepository();
        // cargo cuentas desde archivo aca?
    }

    public boolean existeDNI(int dni) {
    }

    public void crearCuenta(int dni, String nombre, int pin) {
    }

    public void cargarCuentasDesdeArchivo() {
    }

    public void guardarCuentasEnArchivo() {
    }
}
