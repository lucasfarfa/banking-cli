#BANKING CLI (Console App)
Tecnología: Java puro + Head First Java
 Objetivo: Dominar sintaxis, OOP, colecciones, BigDecimal, archivos.

 CONSIGNA – EJERCICIO
 ENTREGABLE
 text
 Crea una aplicación de consola que simule un banco básico.
 REQUERIMIENTOS:
 1. Clases:
   - Cuenta (DNI, nombre, saldo: BigDecimal, lista de transacciones)
   - Transaccion (tipo: DEPÓSITO/RETIRO/TRANSFERENCIA, monto, fecha, cuenta origen/destino)
   - Banco (gestiona todas las cuentas)
 2. Funcionalidades (menú):
   [1] Crear cuenta
   [2] Depositar
   [3] Retirar
   [4] Transferir entre cuentas (mismo banco)
   [5] Ver saldo y últimas 5 transacciones
   [6] Salir
 3. Reglas de negocio:
   - Saldo no puede ser negativo
   - Transferencia: validar saldo suficiente
   - DNI único (usa HashMap<String, Cuenta>)
 4. Persistencia:
   - Al salir: guardar cuentas en archivo `cuentas.txt` (formato: DNI|nombre|saldo)
   - Al iniciar: cargar desde archivo
 5. Validaciones:
   - Monto > 0
   - DNI solo números
   - Usar BigDecimal para saldos y montos