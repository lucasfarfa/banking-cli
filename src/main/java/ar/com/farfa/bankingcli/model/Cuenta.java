package ar.com.farfa.bankingcli.model;

import ar.com.farfa.bankingcli.model.Transaccion.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Cuenta {

    private final int dni;
    private final String nombre;
    private final int pin;
    private BigDecimal saldo;
    private ArrayList<Transaccion> transacciones;

    public Cuenta(int dni, String nombre, int pin)
    {
        this.dni = dni;
        this.nombre = nombre;
        this.pin = pin;
        this.saldo = BigDecimal.ZERO;
        this.transacciones= new ArrayList<>();
    }

    // La idea es que solamente se pueda un unico DNI asociado a una cuenta.
    @Override
    public boolean equals(Object obj) {
        boolean retorno = false;
        if (obj != null && obj.getClass() == this.getClass()) {
            Cuenta compare = (Cuenta) obj;
            retorno = this.dni == compare.dni;
        }
        return retorno;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(dni);
    }

    @Override
    public String toString() {
        String retorno;
        retorno = "Titular: " + nombre + "\nDNI: " + dni + "\nSaldo: " + this.saldo +
                "\nUltimas transacciones: \n"
                + getUltimasTransacciones();
        return retorno;
    }

    // Logica de Negocio
    public void depositar (BigDecimal monto)
    {
        saldo = saldo.add(monto);
        transacciones.add(new Transaccion(TipoTransaccion.DEPOSITO, monto, LocalDateTime.now(), null));
    }
    public void retirar (BigDecimal monto)
    {
        saldo = saldo.subtract(monto);
        transacciones.add(new Transaccion(TipoTransaccion.RETIRO, monto, LocalDateTime.now(), null));
    }

    public void enviarTransferencia(BigDecimal monto, int dniDestino) {
        saldo = saldo.subtract(monto);
        transacciones.add(new Transaccion(TipoTransaccion.TRANSFERENCIA_ENVIADA, monto, LocalDateTime.now(), dniDestino));
    }

    public void recibirTransferencia(BigDecimal monto, int dniOrigen) {
        saldo = saldo.add(monto);
        transacciones.add(new Transaccion(TipoTransaccion.TRANSFERENCIA_RECIBIDA, monto, LocalDateTime.now(), dniOrigen));
    }

    // devuelve ultimas cinco transacciones en formato string
    // es llamado por @Override toString de la cuenta
    private String getUltimasTransacciones() {
        String retorno;
        final int CANTIDAD_TRANSACCIONES = 5; // modificar en caso que quiera mas transacciones

        if (this.transacciones.isEmpty()) {
            retorno = "No se registraron transacciones para esta cuenta.";
        }
        else
        {
            // con math.max evito tener un indice negativo
            int indice = Math.max(0, transacciones.size() - CANTIDAD_TRANSACCIONES);
            List<Transaccion> ultimasTransacciones = transacciones.subList(indice, transacciones.size());

            StringBuilder builder = new StringBuilder();
            for (Transaccion transaccion : ultimasTransacciones) {
                builder.append(transaccion.toString());
            }
            retorno = builder.toString();
        }
        return retorno;
    }

    // retorna true si el monto es menor o igual al saldo actual
    public boolean validarSaldo(BigDecimal monto) {
        return (monto.compareTo(saldo) <= 0) ;
    }
}
