package ar.com.farfa.bankingcli.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record Transaccion (
    TipoTransaccion tipo, // solo puedo instanciar los que están en el enum
    BigDecimal monto,
    LocalDateTime fecha,
    Integer dni
) {
    public enum TipoTransaccion {
        DEPOSITO,
        RETIRO,
        TRANSFERENCIA_ENVIADA,
        TRANSFERENCIA_RECIBIDA
    } // el enum es muy util para definir Dias, Monedas, Estados, Roles, etc

    @Override
    public String toString() {
        return fecha + " -> " + tipo + " \t " + monto + " \n";
    }

}

