package ar.com.farfa.bankingcli.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record Transaccion (
    TipoTransaccion tipo, // solo puedo instanciar los que están en el enum
    BigDecimal monto,
    LocalDateTime fecha,
    String dniDestino
) {
    public enum TipoTransaccion {
        DEPOSITO,
        RETIRO,
        TRANSFERENCIA_ENVIADA,
        TRANSFERENCIA_RECIBIDA
    } // el enum es muy utuli para definir Dias, Monedas, Estados, Roles, etc
}

