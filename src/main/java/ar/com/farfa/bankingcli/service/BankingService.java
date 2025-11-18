package ar.com.farfa.bankingcli.service;
import ar.com.farfa.bankingcli.model.Cuenta;
import ar.com.farfa.bankingcli.persistence.FileCuentaRepository;
import java.util.LinkedHashMap;

public class BankingService
{
    private FileCuentaRepository repositorio;
    private LinkedHashMap<String,Cuenta> cuentas;

    public BankingService()
    {
        this.repositorio = new FileCuentaRepository();
        cuentas = repositorio.cargarCuentasDesdeArchivo();
    }

    public boolean existeDNI(int dni) {
        return cuentas.containsKey(dni);
    }

    public void crearCuenta(int dni, String nombre, int pin) {
    }


    public void guardarCuentasEnArchivo() {
    }
}
