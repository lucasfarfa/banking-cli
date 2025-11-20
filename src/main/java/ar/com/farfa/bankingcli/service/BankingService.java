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

        this.repositorio = new FileCuentaRepository();
        this.cuentas = repositorio.cargarCuentasDesdeArchivo(); // carga el map al iniciar
    }

    public boolean existeDNI(int dni) {
        return cuentas.containsKey(dni);
    }

    public void guardarCuentasEnArchivo()
    {
        repositorio.guardarCuentasEnArchivo(cuentas);
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
        if (dniOrigen == dniDestino) {
            throw new IllegalArgumentException("No se puede transferir a la misma cuenta");
        }

        Cuenta cuentaOrigen = cuentas.get(dniOrigen);
        Cuenta cuentaDestino = cuentas.get(dniDestino);

        if (cuentaOrigen == null || cuentaDestino == null) {
            throw new IllegalArgumentException("Cuenta no encontrada");
        }

        if (!cuentaOrigen.validarSaldo(monto)) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        cuentaOrigen.enviarTransferencia(monto, dniDestino);
        cuentaDestino.recibirTransferencia(monto, dniOrigen);
    }

    public String mostrarSaldoTransacciones (int dni)
    {
        return cuentas.get(dni).toString();
    }

}
