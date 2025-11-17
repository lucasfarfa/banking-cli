import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Cuenta {
    private int _DNI;
    private String _nombre;
    private BigDecimal _saldo;
    private ArrayList<Transaccion> _transacciones = new ArrayList<Transaccion>();

    public Cuenta(int DNI, String nombre, BigDecimal saldo)
    {
        this._DNI = DNI;
        this._nombre = nombre;
        this._saldo = saldo;
    }
}

