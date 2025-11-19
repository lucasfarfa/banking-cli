package ar.com.farfa.bankingcli.service;
import ar.com.farfa.bankingcli.model.Cuenta;
import ar.com.farfa.bankingcli.persistence.FileCuentaRepository;

import java.math.BigDecimal;
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

    // TODO
    public void guardarCuentasEnArchivo() {
    }

    public void crearCuenta(int dni, String nombre, int pin) {
    }

    public void depositar (int dni, BigDecimal monto)
    {
    }

    public void retirar (int dni, BigDecimal monto) {
    }

    public boolean validarSaldo (int dni, BigDecimal monto) {
        return false; //TODO
    }

    public void transferirEntreCuentas(int dniOrigen, int dniDestino, BigDecimal monto) {

    }

  public void mostrarSaldoTransacciones (int dni) {
  }

}
