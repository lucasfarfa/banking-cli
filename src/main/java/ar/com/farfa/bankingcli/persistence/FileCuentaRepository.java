package ar.com.farfa.bankingcli.persistence;

import ar.com.farfa.bankingcli.model.Cuenta;
import ar.com.farfa.bankingcli.model.Transaccion;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.file.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FileCuentaRepository {

    private static final String ARCHIVO = "cuentas.txt";

    public LinkedHashMap<Integer, Cuenta> cargarCuentasDesdeArchivo() {
        LinkedHashMap<Integer, Cuenta> cuentas = new LinkedHashMap<>();
        Path ruta = Path.of(ARCHIVO);

        if (!Files.exists(ruta)) {
            System.out.println("No se encontró cuentas.txt → iniciando vacío");
            return cuentas;
        }

        try (BufferedReader br = Files.newBufferedReader(ruta)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] partes = linea.split("\\|", 5);
                int dni = Integer.parseInt(partes[0]);        // ← leemos el DNI del archivo
                String nombre = partes[1];
                BigDecimal saldo = new BigDecimal(partes[2]);
                int pin = Integer.parseInt(partes[3]);

                Cuenta cuenta = new Cuenta(dni, nombre, pin);
                setField(cuenta, "saldo", saldo);

                // Cargar transacciones
                if (partes.length > 4 && !partes[4].isBlank()) {
                    @SuppressWarnings("unchecked")
                    List<Transaccion> transacciones = (List<Transaccion>) getField(cuenta, "transacciones");
                    for (String tStr : partes[4].split("\\|")) {
                        String[] campos = tStr.split(":", 4);
                        var tipo = Transaccion.TipoTransaccion.valueOf(campos[0]);
                        var monto = new BigDecimal(campos[1]);
                        LocalDateTime fecha = parsearFechaFlexible(campos[2]);
                        Integer dniRel = campos.length > 3 ? Integer.parseInt(campos[3]) : null;
                        transacciones.add(new Transaccion(tipo, monto, fecha, dniRel));
                    }
                }

                cuentas.put(dni, cuenta); // ← usamos el dni del archivo, no de la cuenta
            }
            System.out.println("Cargadas " + cuentas.size() + " cuentas correctamente");
        } catch (Exception e) {
            System.err.println("ERROR CRÍTICO al cargar cuentas.txt:");
            e.printStackTrace();
        }
        return cuentas;
    }

    public void guardarCuentasEnArchivo(LinkedHashMap<Integer, Cuenta> cuentas) {
        try (BufferedWriter bw = Files.newBufferedWriter(Path.of(ARCHIVO))) {
            for (Map.Entry<Integer, Cuenta> entry : cuentas.entrySet()) {
                int dni = entry.getKey();  // ← la clave del mapa es el DNI
                Cuenta c = entry.getValue();

                StringBuilder sb = new StringBuilder();
                sb.append(dni).append("|")
                        .append(getField(c, "nombre")).append("|")
                        .append(getField(c, "saldo")).append("|")
                        .append(getField(c, "pin"));

                @SuppressWarnings("unchecked")
                List<Transaccion> transacciones = (List<Transaccion>) getField(c, "transacciones");
                if (!transacciones.isEmpty()) {
                    sb.append("|");
                    for (Transaccion t : transacciones) {
                        sb.append(t.tipo()).append(":")
                                .append(t.monto().stripTrailingZeros().toPlainString()).append(":")
                                .append(t.fecha().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                        if (t.dni() != null) sb.append(":").append(t.dni());
                        sb.append("|");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                }
                bw.write(sb.toString());
                bw.newLine();
            }
            System.out.println("Guardadas " + cuentas.size() + " cuentas en cuentas.txt");
        } catch (IOException e) {
            System.err.println("ERROR al guardar: " + e.getMessage());
        }
    }

    // Reflexión segura y encapsulada
    private static void setField(Object obj, String fieldName, Object value) {
        try {
            Field f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(obj, value);
            f.setAccessible(false);
        } catch (Exception e) {
            throw new RuntimeException("Error interno: " + fieldName, e);
        }
    }

    private static Object getField(Object obj, String fieldName) {
        try {
            Field f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            Object value = f.get(obj);
            f.setAccessible(false);
            return value;
        } catch (Exception e) {
            throw new RuntimeException("Error interno: " + fieldName, e);
        }
    }

    private static LocalDateTime parsearFechaFlexible(String texto) {
        // Intenta parsear con varios formatos comunes
        String[] formatos = {
                "yyyy-MM-dd'T'HH:mm:ss.SSS",  // 2025-11-20T12:30:45.123
                "yyyy-MM-dd'T'HH:mm:ss",      // 2025-11-20T12:30:45
                "yyyy-MM-dd'T'HH:mm",         // 2025-11-20T12:30
                "yyyy-MM-dd'T'HH"             // 2025-11-20T12
        };

        for (String formato : formatos) {
            try {
                return LocalDateTime.parse(texto, java.time.format.DateTimeFormatter.ofPattern(formato));
            } catch (Exception e) {
                // Intenta el siguiente formato
            }
        }
        // Si ninguno funciona, intenta el parser por defecto
        try {
            return LocalDateTime.parse(texto);
        } catch (Exception e) {
            throw new RuntimeException("Fecha inválida en archivo: " + texto);
        }
    }
}