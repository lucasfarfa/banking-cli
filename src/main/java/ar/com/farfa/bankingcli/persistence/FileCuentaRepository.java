package ar.com.farfa.bankingcli.persistence;
import ar.com.farfa.bankingcli.model.Cuenta;

import java.nio.file.*;
import java.io.*;
import java.util.*;
/*
PERSISTENCE: solamente lectura y escritura de archivo de texto
 */
public class FileCuentaRepository {

    private final String ARCHIVO = "cuentas.txt";

    public LinkedHashMap<String, Cuenta> cargarCuentasDesdeArchivo()
    {
        LinkedHashMap<String,Cuenta> cuentas;
        Path ruta = Path.of(ARCHIVO);
        String dni;
        Cuenta cuenta;

        if (!Files.exists(ruta))
        {
            // no existe archivo
        }
        else {
            try (BufferedReader br = Files.newBufferedReader(ruta)) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;
                    // getCuentaFromFile
                    // getDNIFromFile
                    //cuentas.put(dni,cuenta);
                }
                System.out.println("Cargadas " + cuentas.size() + " cuentas desde " + ruta.toAbsolutePath());
            } catch (IOException e) {
                System.err.println("Error leyendo cuentas.txt: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Línea corrupta en cuentas.txt: " + e.getMessage());
            }
            return cuentas;

        }

    }

}
