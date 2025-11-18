package ar.com.farfa.bankingcli.model;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Cuenta {

    private int _dni;
    private String _nombre;
    private int pin;
    private BigDecimal _saldo;
    private ArrayList<Transaccion> transacciones;

    public Cuenta(int dni, String nombre, int pin)
    {
        this._dni= dni;
        this._nombre = nombre;
        this.pin = pin;
        this._saldo= BigDecimal.ZERO;
        this.transacciones= new ArrayList<Transaccion>();
    }

    // La idea es que solamente se pueda un unico DNI asociado a una cuenta.
    @Override
    public boolean equals(Object obj) {
        boolean retorno = false;
        if (obj != null && obj.getClass() == this.getClass()) {
            Cuenta compare = (Cuenta) obj;
            retorno = this._dni == compare._dni;
        }
        return retorno;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(_dni);
    }

    // Logica de Negocio
    /*
    depositar
    retirar
    transferenciaEnviada
    transferenciaRecibida
     */

}
