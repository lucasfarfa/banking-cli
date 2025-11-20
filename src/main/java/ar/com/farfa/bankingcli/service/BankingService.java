package ar.com.farfa.bankingcli.service;
import ar.com.farfa.bankingcli.model.Cuenta;
import ar.com.farfa.bankingcli.model.Transaccion;
import ar.com.farfa.bankingcli.persistence.FileCuentaRepository;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class BankingService
{
    private FileCuentaRepository repositorio;
    private LinkedHashMap<Integer,Cuenta> cuentas;

    public BankingService()
    {
        // TODO
        //this.repositorio = new FileCuentaRepository();
        //cuentas = repositorio.cargarCuentasDesdeArchivo();
    }

    public boolean existeDNI(int dni) {
        return cuentas.containsKey(dni);
        // TODO: agregar excepciones
    }

    // TODO
    public void guardarCuentasEnArchivo()
    {
    }

    public void crearCuenta(int dni, String nombre, int pin)
    {
        Cuenta nuevaCuenta = new Cuenta(dni, nombre, pin);
        cuentas.put(dni,nuevaCuenta);
    }

    public void depositar (int dni, BigDecimal monto)
    {
        cuentas.get(dni).depositar(monto);
    }

    public void retirar (int dni, BigDecimal monto)
    {
        cuentas.get(dni).retirar(monto);
    }

    public boolean validarSaldo (int dni, BigDecimal monto) {
        return cuentas.get(dni).validarSaldo(monto);
    }

    public void transferirEntreCuentas(int dniOrigen, int dniDestino, BigDecimal monto)
    {
        Cuenta cuentaOrigen = cuentas.get(dniOrigen);
        Cuenta cuentaDestino = cuentas.get(dniDestino);

        cuentaOrigen.enviarTransferencia(monto,dniDestino);
        cuentaDestino.recibirTransferencia(monto,dniOrigen);
    }

    public String mostrarSaldoTransacciones (int dni)
    {
        return cuentas.get(dni).toString();
    }

}
