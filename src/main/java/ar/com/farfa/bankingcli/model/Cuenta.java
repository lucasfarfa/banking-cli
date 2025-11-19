package ar.com.farfa.bankingcli.model;
import ar.com.farfa.bankingcli.model.Transaccion.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.StringJoiner;

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
        this.transacciones= new ArrayList<Transaccion>();
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
        StringJoiner sj = new StringJoiner("|");
        for (Transaccion t : transacciones) {
            String linea = t.tipo() + ":" + t.monto() + ":" + t.fecha();
            if (t.dniDestino() != null) linea += ":" + t.dniDestino();
            sj.add(linea);
        }
        return dni + "|" + nombre + "|" + saldo + "|" + pin + "|" + sj;
    }

    public static Cuenta fromString(String linea) {
        String[] partes = linea.split("\\|", 5);
        int dni = Integer.parseInt(partes[0]);
        String nombre = partes[1];
        BigDecimal saldo = new BigDecimal(partes[2]);
        int pin = Integer.parseInt(partes[3]);

        Cuenta cuenta = new Cuenta(dni, nombre, pin);
        cuenta.saldo = saldo;

        if (partes.length > 4 && !partes[4].isBlank()) {
            for (String t : partes[4].split("\\|")) {
                String[] campos = t.split(":");
                TipoTransaccion tipo = TipoTransaccion.valueOf(campos[0]);
                BigDecimal monto = new BigDecimal(campos[1]);
                LocalDateTime fecha = LocalDateTime.parse(campos[2]);
                String dniDestino = campos.length > 3 ? campos[3] : null;
                cuenta.transacciones.add(new Transaccion(tipo, monto, fecha, dniDestino));
            }
        }
        return cuenta;
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

    public void transferirEnviada(BigDecimal monto, String dniDestino) {
        saldo = saldo.subtract(monto);
        transacciones.add(new Transaccion(TipoTransaccion.TRANSFERENCIA_ENVIADA, monto, LocalDateTime.now(), dniDestino));
    }

    public void transferirRecibida(BigDecimal monto, String dniOrigen) {
        saldo = saldo.add(monto);
        transacciones.add(new Transaccion(TipoTransaccion.TRANSFERENCIA_RECIBIDA, monto, LocalDateTime.now(), dniOrigen));
    }


}
