package uniquindio.edu.co;

import uniquindio.edu.co.excepciones.AutenticacionException;
import uniquindio.edu.co.excepciones.RegistroVehiculoException;
import uniquindio.edu.co.modelo.*;
import uniquindio.edu.co.servicio.*;
import uniquindio.edu.co.util.EnvioEmail;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ServicioEmpleado servicioEmpleado = new ServicioEmpleado();
        ServicioAdministrador servicioAdministrador = new ServicioAdministrador(servicioEmpleado);
        ServicioVehiculo servicioVehiculo = new ServicioVehiculo();
        ServicioTransaccion servicioTransaccion = new ServicioTransaccion();
        ServicioCliente servicioCliente = new ServicioCliente();

        while (true) {
            int opcion = mostrarMenuPrincipal();

            switch (opcion) {
                case 1:
                    gestionarAdministrador(servicioAdministrador, servicioTransaccion);
                    break;

                case 2:
                    gestionarEmpleado(servicioEmpleado, servicioCliente, servicioVehiculo, servicioTransaccion);
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, "Gracias por usar la aplicación. ¡Hasta luego!");
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    break;
            }
        }
    }

    private static int mostrarMenuPrincipal() {
        String menu = "1. Administrador\n2. Empleado\n3. Salir\nSeleccione una opción:";
        String opcionStr = JOptionPane.showInputDialog(menu);
        return Integer.parseInt(opcionStr);
    }

    private static void gestionarAdministrador(ServicioAdministrador servicioAdministrador, ServicioTransaccion servicioTransaccion) {

        while (true) {
            String documento = JOptionPane.showInputDialog("Ingrese su documento de administrador:");
            String clave = JOptionPane.showInputDialog("Ingrese su clave:");

            try {
                servicioAdministrador.autenticarAdministrador(documento, clave);
                JOptionPane.showMessageDialog(null, "Administrador autenticado exitosamente.");

                int opcionAdmin;
                do {
                    opcionAdmin = mostrarMenuAdministrador();
                    switch (opcionAdmin) {
                        case 1 -> registrarEmpleado(servicioAdministrador);
                        case 2 -> verEmpleados(servicioAdministrador);
                        case 3 -> actualizarEmpleado(servicioAdministrador);
                        case 4 -> bloquearDesbloquearEmpleado(servicioAdministrador, true);
                        case 5 -> bloquearDesbloquearEmpleado(servicioAdministrador, false);
                        case 6 -> verReportes(servicioAdministrador, servicioTransaccion);
                    }
                } while (opcionAdmin != 0);
                if (opcionAdmin==0){
                    return;
                }
            } catch (AutenticacionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error de autenticación", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private static int mostrarMenuAdministrador() {
        String menu = """
                1. Registrar empleado
                2. Ver empleados
                3. Actualizar empleado
                4. Bloquear empleado
                5. Desbloquear empleado
                6. Ver reportes
                0. Salir
                """;
        String opcionStr = JOptionPane.showInputDialog(menu);
        return Integer.parseInt(opcionStr);
    }

    private static void registrarEmpleado(ServicioAdministrador servicioAdministrador) {
        String documento = JOptionPane.showInputDialog("Ingrese el documento del empleado:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del empleado:");
        String clave = JOptionPane.showInputDialog("Ingrese la clave del empleado:");
        String preguntaSeguridad = JOptionPane.showInputDialog("Ingrese una pregunta de seguridad:");
        String respuestaSeguridad = JOptionPane.showInputDialog("Ingrese la respuesta de seguridad:");

        try {
            servicioAdministrador.registrarEmpleado(documento, nombre, clave, preguntaSeguridad, respuestaSeguridad);
            JOptionPane.showMessageDialog(null, "Empleado registrado exitosamente.");
        } catch (AutenticacionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private static void verEmpleados(ServicioAdministrador servicioAdministrador) {
        List<Empleado> empleados = servicioAdministrador.verEmpleados();
        StringBuilder infoEmpleados = new StringBuilder("Empleados:\n");
        for (Empleado empleado : empleados) {
            infoEmpleados.append("Documento: ").append(empleado.getDocumento())
                    .append(", Nombre: ").append(empleado.getNombre())
                    .append(", Bloqueado: ").append(empleado.isBloqueado() ? "Sí" : "No").append("\n");
        }
        JOptionPane.showMessageDialog(null, infoEmpleados.toString());
    }

    private static void actualizarEmpleado(ServicioAdministrador servicioAdministrador) {
        String documento = JOptionPane.showInputDialog("Documento del empleado a actualizar:");
        String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:");
        String nuevaClave = JOptionPane.showInputDialog("Nueva clave:");
        String nuevaPregunta = JOptionPane.showInputDialog("Nueva pregunta de seguridad:");
        String nuevaRespuesta = JOptionPane.showInputDialog("Nueva respuesta de seguridad:");

        if (servicioAdministrador.actualizarEmpleado(documento, nuevoNombre, nuevaClave, nuevaPregunta, nuevaRespuesta)) {
            JOptionPane.showMessageDialog(null, "Empleado actualizado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
        }
    }

    private static void bloquearDesbloquearEmpleado(ServicioAdministrador servicioAdministrador, boolean bloquear) {
        String documento = JOptionPane.showInputDialog("Documento del empleado:");
        String mensaje;
        if (bloquear) {
            servicioAdministrador.bloquearEmpleado(documento);
            mensaje = "Empleado bloqueado.";
        } else if (!bloquear) {
            servicioAdministrador.desbloquearEmpleado(documento);
            mensaje =  "Empleado desbloqueado.";
        } else {
            mensaje =  "Empleado no encontrado.";
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private static void verReportes(ServicioAdministrador servicioAdministrador, ServicioTransaccion servicioTransaccion) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            String documento = JOptionPane.showInputDialog("Ingrsa el documento del empleado");

            String fechaInicioStr = JOptionPane.showInputDialog("Ingrese la fecha de inicio (dd/MM/yyyy):");
            Date fechaInicio = dateFormat.parse(fechaInicioStr);

            String fechaFinStr = JOptionPane.showInputDialog("Ingrese la fecha de fin (dd/MM/yyyy):");
            Date fechaFin = dateFormat.parse(fechaFinStr);

            List<Transaccion> transacciones = ServicioTransaccion.obtenerTransaccionesPorPeriodoEmpleado(fechaInicio, fechaFin, documento);

            if (transacciones.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay transacciones en el período especificado.");
            } else {
                StringBuilder reporte = new StringBuilder("Reportes de transacciones:\n\n");

                for (Transaccion t : transacciones) {
                    reporte.append("Empleado: ").append(t.getEmpleado().getNombre())
                            .append("\nTipo: ").append(t.getTipo())
                            .append("\nFecha: ").append(dateFormat.format(t.getFecha()))
                            .append("\nDetalles: ").append(t.getDetalles())
                            .append("\n-------------------------\n");
                }

                JOptionPane.showMessageDialog(null, reporte.toString(), "Reporte de transacciones", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use dd/MM/yyyy.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void gestionarEmpleado(ServicioEmpleado servicioEmpleado, ServicioCliente servicioCliente, ServicioVehiculo servicioVehiculo, ServicioTransaccion servicioTransaccion) {
        String documento = JOptionPane.showInputDialog("Ingrese su documento:");
        if (servicioEmpleado.buscarEmpleado(documento)!=null) {

            String menuEmpleado = "1. Iniciar sesión\n2. Olvidó su contraseña\nSeleccione una opción:";
            String opcionEmpleadoStr = JOptionPane.showInputDialog(menuEmpleado);
            int opcionEmpleado = Integer.parseInt(opcionEmpleadoStr);

            switch (opcionEmpleado) {
                case 1:
                    String clave = JOptionPane.showInputDialog("Ingrese su clave:");
                    autenticarEmpleado(servicioEmpleado, servicioCliente, servicioVehiculo, servicioTransaccion, documento, clave);
                    break;

                case 2:
                    recuperarClave(servicioEmpleado, documento);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    break;
            }
        }else {
            JOptionPane.showMessageDialog(null, "El empleado no existe");
        }
    }

    private static void autenticarEmpleado(ServicioEmpleado servicioEmpleado, ServicioCliente servicioCliente, ServicioVehiculo servicioVehiculo, ServicioTransaccion servicioTransaccion, String documento, String clave) {
        try {
            boolean autenticado = servicioEmpleado.autenticarEmpleado(documento, clave);

            if (autenticado) {
                JOptionPane.showMessageDialog(null, "Empleado autenticado exitosamente.");
                mostrarMenuEmpleado(servicioEmpleado, servicioCliente, servicioVehiculo, servicioTransaccion, documento);
            } else {
                JOptionPane.showMessageDialog(null, "Documento o clave incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (AutenticacionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void mostrarMenuEmpleado(ServicioEmpleado servicioEmpleado, ServicioCliente servicioCliente, ServicioVehiculo servicioVehiculo, ServicioTransaccion servicioTransaccion, String documento) {
        while (true) {
            String menuEmpleado = "1. Registrar Cliente\n2. Ver Clientes\n3. Registrar Vehículo\n4. Alquilar Vehículo\n5. Vender Vehículo\n6. Comprar Vehículo\n0. Salir\nSeleccione una opción:";
            String opcionEmpleadoStr = JOptionPane.showInputDialog(menuEmpleado);
            int opcionEmpleado = Integer.parseInt(opcionEmpleadoStr);

            switch (opcionEmpleado) {
                case 1 -> registrarCliente(servicioCliente);
                case 2 -> verClientes(servicioCliente);
                case 3 -> registrarVehiculo(servicioVehiculo);
                case 4 -> alquilarVehiculo(servicioCliente, servicioVehiculo, servicioTransaccion, servicioEmpleado.buscarEmpleado(documento));
                case 5 -> venderVehiculo(servicioCliente, servicioVehiculo, servicioTransaccion, servicioEmpleado.buscarEmpleado(documento));
                case 6 -> comprarVehiculo(servicioCliente, servicioVehiculo, servicioTransaccion, servicioEmpleado.buscarEmpleado(documento));
                case 0 -> {return;}
                default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }

    private static void comprarVehiculo(ServicioCliente servicioCliente, ServicioVehiculo servicioVehiculo, ServicioTransaccion servicioTransaccion, Empleado empleado) {
        String documentoCliente = JOptionPane.showInputDialog("Ingrese el documento del cliente:");
        Cliente cliente = servicioCliente.buscarCliente(documentoCliente);

        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            return;
        }
        Vehiculo vehiculo = registrarVehiculoRetorno(servicioVehiculo);

        if (vehiculo==null) {
            JOptionPane.showMessageDialog(null, "Vehículo no encontrado.");
            return;
        }

        if (servicioVehiculo.comprarVehiculo(vehiculo, cliente, empleado, vehiculo.isRevisionTecnomecanica(), servicioTransaccion)) {
            JOptionPane.showMessageDialog(null, "Vehículo comprado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al comprar el vehículo.");
        }
    }

    private static void alquilarVehiculo(ServicioCliente servicioCliente, ServicioVehiculo servicioVehiculo, ServicioTransaccion servicioTransaccion, Empleado empleado) {
        String documentoCliente = JOptionPane.showInputDialog("Ingrese el documento del cliente:");
        Cliente cliente = servicioCliente.buscarCliente(documentoCliente);

        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            return;
        }
        String placaVehiculo = JOptionPane.showInputDialog("Ingrese la placa del vehículo a alquilar:");
        Vehiculo vehiculo = servicioVehiculo.buscarVehiculos(placaVehiculo);

        if (vehiculo==null) {
            JOptionPane.showMessageDialog(null, "Vehículo no encontrado.");
            return;
        }

        if (servicioVehiculo.alquilarVehiculo(vehiculo, cliente, empleado, servicioTransaccion)) {
            JOptionPane.showMessageDialog(null, "Vehículo alquilado exitosamente.");
            vehiculo.setDisponible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Error al alquilar el vehículo.");
        }
    }

    private static void recuperarClave(ServicioEmpleado servicioEmpleado, String documento) {
        Empleado empleado = servicioEmpleado.buscarEmpleado(documento);
        String correoNuevaClave = JOptionPane.showInputDialog("Ingrese el correo a enviar la confirmacion:");

        try {
            String nuevaClave = servicioEmpleado.recuperarClave(documento, empleado.getRespuestaSeguridad());
            EnvioEmail envioEmail = new EnvioEmail(correoNuevaClave, "Recuperacion de contraseña", nuevaClave);
            envioEmail.enviarNotificacion();
        } catch (AutenticacionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void registrarCliente(ServicioCliente servicioCliente) {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        String documento = JOptionPane.showInputDialog("Ingrese el documento del cliente:");
        String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del cliente:");
        String email = JOptionPane.showInputDialog("Ingrese el email del cliente:");

        Cliente cliente = new Cliente(nombre, documento, telefono, email);
        servicioCliente.registrarCliente(cliente);

        JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente.");
    }

    private static void registrarVehiculo(ServicioVehiculo servicioVehiculo)  {
        String[] opciones = {
                "Moto",//
                "Sedán",//
                "Deportivo",//
                "Camioneta",//
                "Pick Up",//
                "Van",//
                "Bus",//
                "Camion",//
        };

        int tipoVehiculo = JOptionPane.showOptionDialog(null, "Seleccione el tipo de vehículo:", "Tipo de Vehículo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, opciones, opciones[0]);

        String placa = JOptionPane.showInputDialog("Ingrese la placa del vehiculo:");
        String marca = JOptionPane.showInputDialog("Ingrese la marca del vehiculo:");
        boolean esNuevo = JOptionPane.showConfirmDialog(null, "¿Es nuevo?", "Tipo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del vehiculo:");
        int cambios = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de cambios:"));
        double velocidadMaxima = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la velocidad máxima:"));
        double cilindraje = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el cilindraje:"));
        String tipoCombustible = JOptionPane.showInputDialog("Ingrese el tipo de combustible (Gasolina, Diésel):");
        boolean  transmisionAutomatica = JOptionPane.showConfirmDialog(null, "¿Es de transmisión automática?", "Transmisión", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        boolean  revisionTecnomecanica = JOptionPane.showConfirmDialog(null, "¿Paso la revison tecnomecanica?", "Revision", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        boolean  esElectrico = JOptionPane.showConfirmDialog(null, "¿El vehiculo es electrico?", "Revision", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        if (tipoVehiculo==0) {
            Moto moto;
            if (esElectrico){
                String tiempoCarga = JOptionPane.showInputDialog("Ingrese el tiempo de carga");
                String autonomia = JOptionPane.showInputDialog("Ingrese la autonomia electrica");
                moto = new Moto(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, tiempoCarga, autonomia);
            } else {
                boolean  esEnchufable = JOptionPane.showConfirmDialog(null, "¿Se puede conectar?", "Enchufe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (esEnchufable) {
                    moto = new Moto(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
                } else {
                    boolean  esLigero = JOptionPane.showConfirmDialog(null, "¿Es ligero?", "Ligero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    moto = new Moto(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, esLigero);
                }
            }

            try {
                servicioVehiculo.registrarVehiculo(moto);
                JOptionPane.showMessageDialog(null, "Moto registrada exitosamente.");
            } catch (RegistroVehiculoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (tipoVehiculo==1) {
            int numeroPasajeros = 0;
            int numeroPuertas = 0;
            int numeroBolsasAire = 0;
            double capacidadMaletero = 0;
            try {
                numeroPasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de pasajeros (Solo numeros)"));
                numeroPuertas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de puertas (Solo numeros)"));
                numeroBolsasAire = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de bolsas de aire (Solo numeros)"));
                capacidadMaletero = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la capacidad del maletero (Solo numeros)"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error");
            }
            boolean tieneAireAcondicionado = JOptionPane.showConfirmDialog(null, "¿Tiene aire acondicionado?", "Aire Acondicionado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneCamaraReversa = JOptionPane.showConfirmDialog(null, "¿Tiene camara de reversa?", "Camara Reversa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneVelocidadCrucero = JOptionPane.showConfirmDialog(null, "¿Tiene velocidad de crucero?", "Velocidad Crucero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneABS = JOptionPane.showConfirmDialog(null, "¿Tiene frenos ABS?", "ABS", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneSensoresColision = JOptionPane.showConfirmDialog(null, "¿Tiene sensor de colision?", "Sensor Colision", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneSensorTraficoCruzado = JOptionPane.showConfirmDialog(null, "¿Tiene sensero de trafico cruzado?", "Sensor Trafico", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneAsistenteCarril = JOptionPane.showConfirmDialog(null, "¿Tiene asistente de carril?", "Asistente carril", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            Sedan sedan;
            if (esElectrico){
                String tiempoCarga = JOptionPane.showInputDialog("Ingrese el tiempo de carga");
                String autonomia = JOptionPane.showInputDialog("Ingrese la autonomia electrica");
                sedan = new Sedan(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, tieneAsistenteCarril, tieneSensorTraficoCruzado, tieneSensoresColision, tieneABS, numeroBolsasAire, tieneVelocidadCrucero, tieneCamaraReversa, tieneAireAcondicionado, capacidadMaletero, numeroPuertas, numeroPasajeros, autonomia, tiempoCarga);
            } else {
                boolean  esEnchufable = JOptionPane.showConfirmDialog(null, "¿Se puede conectar?", "Enchufe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (esEnchufable) {
                    sedan = new Sedan(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, tieneVelocidadCrucero, numeroBolsasAire, tieneABS, tieneSensoresColision, tieneSensorTraficoCruzado, tieneAsistenteCarril);
                } else {
                    boolean  esLigero = JOptionPane.showConfirmDialog(null, "¿Es ligero?", "Ligero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    sedan = new Sedan(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, tieneVelocidadCrucero, numeroBolsasAire, tieneABS, tieneSensoresColision, tieneSensorTraficoCruzado, tieneAsistenteCarril, esLigero);
                }
            }

            try {
                servicioVehiculo.registrarVehiculo(sedan);
                JOptionPane.showMessageDialog(null, "Sedan registrado exitosamente.");
            } catch (RegistroVehiculoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (tipoVehiculo==2) {
            int numeroPasajeros = 0;
            int numeroPuertas = 0;
            int numeroBolsasAire = 0;
            int caballosDeFuerza = 0;
            double tiempoCeroACien = 0;
            try {
                numeroPasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de pasajeros (Solo numeros)"));
                numeroPuertas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de puertas (Solo numeros)"));
                numeroBolsasAire = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de bolsas de aire (Solo numeros)"));
                caballosDeFuerza = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de caballos de fuerzo (Solo numeros)"));
                tiempoCeroACien = Double.parseDouble(JOptionPane.showInputDialog("Ingrese los segundos que demora en llegar de 0 a 100 (Solo numeros)"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error");
            }

            Deportivo deportivo;
            if (esElectrico){
                String tiempoCarga = JOptionPane.showInputDialog("Ingrese el tiempo de carga");
                String autonomia = JOptionPane.showInputDialog("Ingrese la autonomia electrica");
                deportivo = new Deportivo(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, numeroBolsasAire, caballosDeFuerza, tiempoCeroACien, tiempoCarga, autonomia);
            } else {
                boolean  esEnchufable = JOptionPane.showConfirmDialog(null, "¿Se puede conectar?", "Enchufe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (esEnchufable) {
                    deportivo = new Deportivo(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, tiempoCeroACien, caballosDeFuerza, numeroBolsasAire, numeroPuertas, numeroPasajeros);
                } else {
                    boolean  esLigero = JOptionPane.showConfirmDialog(null, "¿Es ligero?", "Ligero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    deportivo = new Deportivo(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, numeroBolsasAire, caballosDeFuerza, tiempoCeroACien, esLigero);
                }
            }
            try {
                servicioVehiculo.registrarVehiculo(deportivo);
                JOptionPane.showMessageDialog(null, "Deportivo registrada exitosamente.");
            } catch (RegistroVehiculoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (tipoVehiculo==3) {
            int numeroPasajeros = 0;
            int numeroPuertas = 0;
            double capacidadMaletero = 0;
            int numeroBolsasAire = 0;
            try {
                numeroPasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de pasajeros (Solo numeros)"));
                numeroPuertas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de puertas (Solo numeros)"));
                capacidadMaletero = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la capacidad del maletero (Solo numeros)"));
                numeroBolsasAire = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de bolsas de aire (Solo numeros)"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error");
            }
            boolean tieneAireAcondicionado = JOptionPane.showConfirmDialog(null, "¿Tiene aire acondicionado?", "Aire Acondicionado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneCamaraReversa = JOptionPane.showConfirmDialog(null, "¿Tiene camara de reversa?", "Camara Reversa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneVelocidadCrucero = JOptionPane.showConfirmDialog(null, "¿Tiene velocidad de crucero?", "Velocidad Crucero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneABS = JOptionPane.showConfirmDialog(null, "¿Tiene frenos ABS?", "ABS", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean esCuatroPorCuatro = JOptionPane.showConfirmDialog(null, "¿Tiene cuatro por cuatro?", "Cuatro por Cuatro", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            Camioneta camioneta;
            if (esElectrico){
                String tiempoCarga = JOptionPane.showInputDialog("Ingrese el tiempo de carga");
                String autonomia = JOptionPane.showInputDialog("Ingrese la autonomia electrica");
                camioneta = new Camioneta(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, tieneVelocidadCrucero, numeroBolsasAire, tieneABS, esCuatroPorCuatro, tiempoCarga, autonomia);
            } else {
                boolean  esEnchufable = JOptionPane.showConfirmDialog(null, "¿Se puede conectar?", "Enchufe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (esEnchufable) {
                    camioneta = new Camioneta(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, tieneVelocidadCrucero, numeroBolsasAire, tieneABS, esCuatroPorCuatro);
                } else {
                    boolean  esLigero = JOptionPane.showConfirmDialog(null, "¿Es ligero?", "Ligero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    camioneta = new Camioneta(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, tieneVelocidadCrucero, numeroBolsasAire, tieneABS, esCuatroPorCuatro, esLigero);
                }
            }
            try {
                servicioVehiculo.registrarVehiculo(camioneta);
                JOptionPane.showMessageDialog(null, "Camioneta registrada exitosamente.");
            } catch (RegistroVehiculoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (tipoVehiculo==4) {
            int numeroPasajeros = 0;
            int numeroPuertas = 0;
            double capacidadCajaCarga = 0;
            int numeroBolsasAire = 0;
            try {
                numeroPasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de pasajeros (Solo numeros)"));
                numeroPuertas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de puertas (Solo numeros)"));
                capacidadCajaCarga = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la capacidad de caja de carga (Solo numeros)"));
                numeroBolsasAire = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de bolsas de aire (Solo numeros)"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error");
            }
            boolean tieneAireAcondicionado = JOptionPane.showConfirmDialog(null, "¿Tiene aire acondicionado?", "Aire Acondicionado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneCamaraReversa = JOptionPane.showConfirmDialog(null, "¿Tiene camara de reversa?", "Camara Reversa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneABS = JOptionPane.showConfirmDialog(null, "¿Tiene frenos ABS?", "ABS", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean esCuatroPorCuatro = JOptionPane.showConfirmDialog(null, "¿Tiene cuatro por cuatro?", "Cuatro por Cuatro", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            PickUp pickUp;
            if (esElectrico){
                String tiempoCarga = JOptionPane.showInputDialog("Ingrese el tiempo de carga");
                String autonomia = JOptionPane.showInputDialog("Ingrese la autonomia electrica");
                pickUp = new PickUp(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS, esCuatroPorCuatro, capacidadCajaCarga, tiempoCarga, autonomia);
            } else {
                boolean  esEnchufable = JOptionPane.showConfirmDialog(null, "¿Se puede conectar?", "Enchufe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (esEnchufable) {
                    pickUp = new PickUp(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS, esCuatroPorCuatro, capacidadCajaCarga);
                } else {
                    boolean  esLigero = JOptionPane.showConfirmDialog(null, "¿Es ligero?", "Ligero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    pickUp = new PickUp(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS, esCuatroPorCuatro, capacidadCajaCarga, esLigero);
                }
            }
            try {
                servicioVehiculo.registrarVehiculo(pickUp);
                JOptionPane.showMessageDialog(null, "Pick Up registrada exitosamente.");
            } catch (RegistroVehiculoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (tipoVehiculo==5) {
            int numeroPasajeros = 0;
            int numeroPuertas = 0;
            double capacidadMaletero = 0;
            int numeroBolsasAire = 0;
            try {
                numeroPasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de pasajeros (Solo numeros)"));
                numeroPuertas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de puertas (Solo numeros)"));
                capacidadMaletero = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la capacidad del maletero (Solo numeros)"));
                numeroBolsasAire = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de bolsas de aire (Solo numeros)"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error");
            }
            boolean tieneAireAcondicionado = JOptionPane.showConfirmDialog(null, "¿Tiene aire acondicionado?", "Aire Acondicionado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneCamaraReversa = JOptionPane.showConfirmDialog(null, "¿Tiene camara de reversa?", "Camara Reversa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneABS = JOptionPane.showConfirmDialog(null, "¿Tiene frenos ABS?", "ABS", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            Van van;
            if (esElectrico){
                String tiempoCarga = JOptionPane.showInputDialog("Ingrese el tiempo de carga");
                String autonomia = JOptionPane.showInputDialog("Ingrese la autonomia electrica");
                van = new Van(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS, tiempoCarga, autonomia);
            } else {
                boolean  esEnchufable = JOptionPane.showConfirmDialog(null, "¿Se puede conectar?", "Enchufe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (esEnchufable) {
                    van = new Van(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS);
                } else {
                    boolean  esLigero = JOptionPane.showConfirmDialog(null, "¿Es ligero?", "Ligero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    van = new Van(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS, esLigero);
                }
            }
            try {
                servicioVehiculo.registrarVehiculo(van);
                JOptionPane.showMessageDialog(null, "Van registrada exitosamente.");
            } catch (RegistroVehiculoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (tipoVehiculo==6) {
            int numeroPasajeros = 0;
            int numeroPuertas = 0;
            double capacidadMaletero = 0;
            int numeroBolsasAire = 0;
            int numeroEjes = 0;
            int numeroSalidasEmergencia = 0;
            try {
                numeroPasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de pasajeros (Solo numeros)"));
                numeroPuertas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de puertas (Solo numeros)"));
                capacidadMaletero = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la capacidad del maletero (Solo numeros)"));
                numeroBolsasAire = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de bolsas de aire (Solo numeros)"));
                numeroEjes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de ejes (Solo numeros)"));
                numeroSalidasEmergencia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de salidas de emergencia (Solo numeros)"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error");
            }
            boolean tieneAireAcondicionado = JOptionPane.showConfirmDialog(null, "¿Tiene aire acondicionado?", "Aire Acondicionado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneCamaraReversa = JOptionPane.showConfirmDialog(null, "¿Tiene camara de reversa?", "Camara Reversa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneABS = JOptionPane.showConfirmDialog(null, "¿Tiene frenos ABS?", "ABS", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            Bus bus;
            if (esElectrico){
                String tiempoCarga = JOptionPane.showInputDialog("Ingrese el tiempo de carga");
                String autonomia = JOptionPane.showInputDialog("Ingrese la autonomia electrica");
                bus = new Bus(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS, numeroEjes, numeroSalidasEmergencia, tiempoCarga, autonomia);
            } else {
                boolean  esEnchufable = JOptionPane.showConfirmDialog(null, "¿Se puede conectar?", "Enchufe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (esEnchufable) {
                    bus = new Bus(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS, numeroEjes, numeroSalidasEmergencia);
                } else {
                    boolean  esLigero = JOptionPane.showConfirmDialog(null, "¿Es ligero?", "Ligero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    bus = new Bus(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS, numeroEjes, numeroSalidasEmergencia, esLigero);
                }
            }
            try {
                servicioVehiculo.registrarVehiculo(bus);
                JOptionPane.showMessageDialog(null, "Bus registrada exitosamente.");
            } catch (RegistroVehiculoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (tipoVehiculo==7) {
            double capacidadCarga = 0;
            int numeroEjes = 0;
            try {
                capacidadCarga = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la capacidad del maletero (Solo numeros)"));
                numeroEjes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de ejes (Solo numeros)"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error");
            }
            boolean tieneAireAcondicionado = JOptionPane.showConfirmDialog(null, "¿Tiene aire acondicionado?", "Aire Acondicionado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneFrenosDeAire = JOptionPane.showConfirmDialog(null, "¿Tiene frenos de aire?", "Frenos de Aire", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneABS = JOptionPane.showConfirmDialog(null, "¿Tiene frenos ABS?", "ABS", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            String tipoCamion = JOptionPane.showInputDialog("Ingrese el tipo de camion");

            Camion camion;
            if (esElectrico){
                String tiempoCarga = JOptionPane.showInputDialog("Ingrese el tiempo de carga");
                String autonomia = JOptionPane.showInputDialog("Ingrese la autonomia electrica");
                camion = new Camion(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, capacidadCarga, tieneAireAcondicionado, tieneFrenosDeAire, tieneABS, numeroEjes, tipoCamion, tiempoCarga, autonomia);
            } else {
                boolean  esEnchufable = JOptionPane.showConfirmDialog(null, "¿Se puede conectar?", "Enchufe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (esEnchufable) {
                    camion = new Camion(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, capacidadCarga, tieneAireAcondicionado, tieneFrenosDeAire, tieneABS, numeroEjes, tipoCamion);
                } else {
                    boolean  esLigero = JOptionPane.showConfirmDialog(null, "¿Es ligero?", "Ligero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    camion = new Camion(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, capacidadCarga, tieneAireAcondicionado, tieneFrenosDeAire, tieneABS, numeroEjes, tipoCamion, esLigero);
                }
            }
            try {
                servicioVehiculo.registrarVehiculo(camion);
                JOptionPane.showMessageDialog(null, "Camion registrada exitosamente.");
            } catch (RegistroVehiculoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static Vehiculo registrarVehiculoRetorno(ServicioVehiculo servicioVehiculo)  {
        String[] opciones = {
                "Moto",//
                "Sedán",//
                "Deportivo",//
                "Camioneta",//
                "Pick Up",//
                "Van",//
                "Bus",//
                "Camion",//
        };

        int tipoVehiculo = JOptionPane.showOptionDialog(null, "Seleccione el tipo de vehículo:", "Tipo de Vehículo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, opciones, opciones[0]);

        String placa = JOptionPane.showInputDialog("Ingrese la placa del vehiculo:");
        String marca = JOptionPane.showInputDialog("Ingrese la marca de la vehiculo:");
        boolean esNuevo = JOptionPane.showConfirmDialog(null, "¿Es nuevo?", "Tipo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        String modelo = JOptionPane.showInputDialog("Ingrese el modelo de la vehiculo:");
        int cambios = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de cambios:"));
        double velocidadMaxima = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la velocidad máxima:"));
        double cilindraje = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el cilindraje:"));
        String tipoCombustible = JOptionPane.showInputDialog("Ingrese el tipo de combustible (Gasolina, Diésel):");
        boolean  transmisionAutomatica = JOptionPane.showConfirmDialog(null, "¿Es de transmisión automática?", "Transmisión", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        boolean  revisionTecnomecanica = JOptionPane.showConfirmDialog(null, "¿Paso la revison tecnomecanica?", "Revision", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        boolean  esElectrico = JOptionPane.showConfirmDialog(null, "¿El vehiculo es electrico?", "Revision", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        if (tipoVehiculo==0) {
            Moto moto;
            if (esElectrico){
                String tiempoCarga = JOptionPane.showInputDialog("Ingrese el tiempo de carga");
                String autonomia = JOptionPane.showInputDialog("Ingrese la autonomia electrica");
                moto = new Moto(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, tiempoCarga, autonomia);
            } else {
                boolean  esEnchufable = JOptionPane.showConfirmDialog(null, "¿Se puede conectar?", "Enchufe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (esEnchufable) {
                    moto = new Moto(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
                } else {
                    boolean  esLigero = JOptionPane.showConfirmDialog(null, "¿Es ligero?", "Ligero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    moto = new Moto(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, esLigero);
                }
            }

            try {
                servicioVehiculo.registrarVehiculo(moto);
                JOptionPane.showMessageDialog(null, "Moto registrada exitosamente.");
                return moto;
            } catch (RegistroVehiculoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (tipoVehiculo==1) {
            int numeroPasajeros = 0;
            int numeroPuertas = 0;
            int numeroBolsasAire = 0;
            double capacidadMaletero = 0;
            try {
                numeroPasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de pasajeros (Solo numeros)"));
                numeroPuertas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de puertas (Solo numeros)"));
                numeroBolsasAire = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de bolsas de aire (Solo numeros)"));
                capacidadMaletero = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la capacidad del maletero (Solo numeros)"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error");
            }
            boolean tieneAireAcondicionado = JOptionPane.showConfirmDialog(null, "¿Tiene aire acondicionado?", "Aire Acondicionado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneCamaraReversa = JOptionPane.showConfirmDialog(null, "¿Tiene camara de reversa?", "Camara Reversa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneVelocidadCrucero = JOptionPane.showConfirmDialog(null, "¿Tiene velocidad de crucero?", "Velocidad Crucero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneABS = JOptionPane.showConfirmDialog(null, "¿Tiene frenos ABS?", "ABS", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneSensoresColision = JOptionPane.showConfirmDialog(null, "¿Tiene sensor de colision?", "Sensor Colision", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneSensorTraficoCruzado = JOptionPane.showConfirmDialog(null, "¿Tiene sensero de trafico cruzado?", "Sensor Trafico", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneAsistenteCarril = JOptionPane.showConfirmDialog(null, "¿Tiene asistente de carril?", "Asistente carril", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            Sedan sedan;
            if (esElectrico){
                String tiempoCarga = JOptionPane.showInputDialog("Ingrese el tiempo de carga");
                String autonomia = JOptionPane.showInputDialog("Ingrese la autonomia electrica");
                sedan = new Sedan(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, tieneAsistenteCarril, tieneSensorTraficoCruzado, tieneSensoresColision, tieneABS, numeroBolsasAire, tieneVelocidadCrucero, tieneCamaraReversa, tieneAireAcondicionado, capacidadMaletero, numeroPuertas, numeroPasajeros, autonomia, tiempoCarga);
            } else {
                boolean  esEnchufable = JOptionPane.showConfirmDialog(null, "¿Se puede conectar?", "Enchufe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (esEnchufable) {
                    sedan = new Sedan(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, tieneVelocidadCrucero, numeroBolsasAire, tieneABS, tieneSensoresColision, tieneSensorTraficoCruzado, tieneAsistenteCarril);
                } else {
                    boolean  esLigero = JOptionPane.showConfirmDialog(null, "¿Es ligero?", "Ligero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    sedan = new Sedan(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, tieneVelocidadCrucero, numeroBolsasAire, tieneABS, tieneSensoresColision, tieneSensorTraficoCruzado, tieneAsistenteCarril, esLigero);
                }
            }

            try {
                servicioVehiculo.registrarVehiculo(sedan);
                JOptionPane.showMessageDialog(null, "Sedan registrada exitosamente.");
                return sedan;
            } catch (RegistroVehiculoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (tipoVehiculo==2) {
            int numeroPasajeros = 0;
            int numeroPuertas = 0;
            int numeroBolsasAire = 0;
            int caballosDeFuerza = 0;
            double tiempoCeroACien = 0;
            try {
                numeroPasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de pasajeros (Solo numeros)"));
                numeroPuertas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de puertas (Solo numeros)"));
                numeroBolsasAire = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de bolsas de aire (Solo numeros)"));
                caballosDeFuerza = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de caballos de fuerzo (Solo numeros)"));
                tiempoCeroACien = Double.parseDouble(JOptionPane.showInputDialog("Ingrese los segundos que demora en llegar de 0 a 100 (Solo numeros)"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error");
            }

            Deportivo deportivo;
            if (esElectrico){
                String tiempoCarga = JOptionPane.showInputDialog("Ingrese el tiempo de carga");
                String autonomia = JOptionPane.showInputDialog("Ingrese la autonomia electrica");
                deportivo = new Deportivo(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, numeroBolsasAire, caballosDeFuerza, tiempoCeroACien, tiempoCarga, autonomia);
            } else {
                boolean  esEnchufable = JOptionPane.showConfirmDialog(null, "¿Se puede conectar?", "Enchufe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (esEnchufable) {
                    deportivo = new Deportivo(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, tiempoCeroACien, caballosDeFuerza, numeroBolsasAire, numeroPuertas, numeroPasajeros);
                } else {
                    boolean  esLigero = JOptionPane.showConfirmDialog(null, "¿Es ligero?", "Ligero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    deportivo = new Deportivo(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, numeroBolsasAire, caballosDeFuerza, tiempoCeroACien, esLigero);
                }
            }
            try {
                servicioVehiculo.registrarVehiculo(deportivo);
                JOptionPane.showMessageDialog(null, "Deportivo registrada exitosamente.");
                return deportivo;
            } catch (RegistroVehiculoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (tipoVehiculo==3) {
            int numeroPasajeros = 0;
            int numeroPuertas = 0;
            double capacidadMaletero = 0;
            int numeroBolsasAire = 0;
            try {
                numeroPasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de pasajeros (Solo numeros)"));
                numeroPuertas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de puertas (Solo numeros)"));
                capacidadMaletero = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la capacidad del maletero (Solo numeros)"));
                numeroBolsasAire = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de bolsas de aire (Solo numeros)"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error");
            }
            boolean tieneAireAcondicionado = JOptionPane.showConfirmDialog(null, "¿Tiene aire acondicionado?", "Aire Acondicionado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneCamaraReversa = JOptionPane.showConfirmDialog(null, "¿Tiene camara de reversa?", "Camara Reversa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneVelocidadCrucero = JOptionPane.showConfirmDialog(null, "¿Tiene velocidad de crucero?", "Velocidad Crucero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneABS = JOptionPane.showConfirmDialog(null, "¿Tiene frenos ABS?", "ABS", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean esCuatroPorCuatro = JOptionPane.showConfirmDialog(null, "¿Tiene cuatro por cuatro?", "Cuatro por Cuatro", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            Camioneta camioneta;
            if (esElectrico){
                String tiempoCarga = JOptionPane.showInputDialog("Ingrese el tiempo de carga");
                String autonomia = JOptionPane.showInputDialog("Ingrese la autonomia electrica");
                camioneta = new Camioneta(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, tieneVelocidadCrucero, numeroBolsasAire, tieneABS, esCuatroPorCuatro, tiempoCarga, autonomia);
            } else {
                boolean  esEnchufable = JOptionPane.showConfirmDialog(null, "¿Se puede conectar?", "Enchufe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (esEnchufable) {
                    camioneta = new Camioneta(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, tieneVelocidadCrucero, numeroBolsasAire, tieneABS, esCuatroPorCuatro);
                } else {
                    boolean  esLigero = JOptionPane.showConfirmDialog(null, "¿Es ligero?", "Ligero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    camioneta = new Camioneta(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, tieneVelocidadCrucero, numeroBolsasAire, tieneABS, esCuatroPorCuatro, esLigero);
                }
            }
            try {
                servicioVehiculo.registrarVehiculo(camioneta);
                JOptionPane.showMessageDialog(null, "Camioneta registrada exitosamente.");
                return camioneta;
            } catch (RegistroVehiculoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (tipoVehiculo==4) {
            int numeroPasajeros = 0;
            int numeroPuertas = 0;
            double capacidadCajaCarga = 0;
            int numeroBolsasAire = 0;
            try {
                numeroPasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de pasajeros (Solo numeros)"));
                numeroPuertas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de puertas (Solo numeros)"));
                capacidadCajaCarga = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la capacidad de caja de carga (Solo numeros)"));
                numeroBolsasAire = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de bolsas de aire (Solo numeros)"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error");
            }
            boolean tieneAireAcondicionado = JOptionPane.showConfirmDialog(null, "¿Tiene aire acondicionado?", "Aire Acondicionado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneCamaraReversa = JOptionPane.showConfirmDialog(null, "¿Tiene camara de reversa?", "Camara Reversa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneABS = JOptionPane.showConfirmDialog(null, "¿Tiene frenos ABS?", "ABS", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean esCuatroPorCuatro = JOptionPane.showConfirmDialog(null, "¿Tiene cuatro por cuatro?", "Cuatro por Cuatro", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            PickUp pickUp;
            if (esElectrico){
                String tiempoCarga = JOptionPane.showInputDialog("Ingrese el tiempo de carga");
                String autonomia = JOptionPane.showInputDialog("Ingrese la autonomia electrica");
                pickUp = new PickUp(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS, esCuatroPorCuatro, capacidadCajaCarga, tiempoCarga, autonomia);
            } else {
                boolean  esEnchufable = JOptionPane.showConfirmDialog(null, "¿Se puede conectar?", "Enchufe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (esEnchufable) {
                    pickUp = new PickUp(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS, esCuatroPorCuatro, capacidadCajaCarga);
                } else {
                    boolean  esLigero = JOptionPane.showConfirmDialog(null, "¿Es ligero?", "Ligero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    pickUp = new PickUp(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS, esCuatroPorCuatro, capacidadCajaCarga, esLigero);
                }
            }
            try {
                servicioVehiculo.registrarVehiculo(pickUp);
                JOptionPane.showMessageDialog(null, "Pick Up registrada exitosamente.");
                return pickUp;
            } catch (RegistroVehiculoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (tipoVehiculo==5) {
            int numeroPasajeros = 0;
            int numeroPuertas = 0;
            double capacidadMaletero = 0;
            int numeroBolsasAire = 0;
            try {
                numeroPasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de pasajeros (Solo numeros)"));
                numeroPuertas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de puertas (Solo numeros)"));
                capacidadMaletero = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la capacidad del maletero (Solo numeros)"));
                numeroBolsasAire = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de bolsas de aire (Solo numeros)"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error");
            }
            boolean tieneAireAcondicionado = JOptionPane.showConfirmDialog(null, "¿Tiene aire acondicionado?", "Aire Acondicionado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneCamaraReversa = JOptionPane.showConfirmDialog(null, "¿Tiene camara de reversa?", "Camara Reversa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneABS = JOptionPane.showConfirmDialog(null, "¿Tiene frenos ABS?", "ABS", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            Van van;
            if (esElectrico){
                String tiempoCarga = JOptionPane.showInputDialog("Ingrese el tiempo de carga");
                String autonomia = JOptionPane.showInputDialog("Ingrese la autonomia electrica");
                van = new Van(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS, tiempoCarga, autonomia);
            } else {
                boolean  esEnchufable = JOptionPane.showConfirmDialog(null, "¿Se puede conectar?", "Enchufe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (esEnchufable) {
                    van = new Van(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS);
                } else {
                    boolean  esLigero = JOptionPane.showConfirmDialog(null, "¿Es ligero?", "Ligero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    van = new Van(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS, esLigero);
                }
            }
            try {
                servicioVehiculo.registrarVehiculo(van);
                JOptionPane.showMessageDialog(null, "Van registrada exitosamente.");
                return van;
            } catch (RegistroVehiculoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (tipoVehiculo==6) {
            int numeroPasajeros = 0;
            int numeroPuertas = 0;
            double capacidadMaletero = 0;
            int numeroBolsasAire = 0;
            int numeroEjes = 0;
            int numeroSalidasEmergencia = 0;
            try {
                numeroPasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de pasajeros (Solo numeros)"));
                numeroPuertas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de puertas (Solo numeros)"));
                capacidadMaletero = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la capacidad del maletero (Solo numeros)"));
                numeroBolsasAire = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de bolsas de aire (Solo numeros)"));
                numeroEjes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de ejes (Solo numeros)"));
                numeroSalidasEmergencia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de salidas de emergencia (Solo numeros)"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error");
            }
            boolean tieneAireAcondicionado = JOptionPane.showConfirmDialog(null, "¿Tiene aire acondicionado?", "Aire Acondicionado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneCamaraReversa = JOptionPane.showConfirmDialog(null, "¿Tiene camara de reversa?", "Camara Reversa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneABS = JOptionPane.showConfirmDialog(null, "¿Tiene frenos ABS?", "ABS", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            Bus bus;
            if (esElectrico){
                String tiempoCarga = JOptionPane.showInputDialog("Ingrese el tiempo de carga");
                String autonomia = JOptionPane.showInputDialog("Ingrese la autonomia electrica");
                bus = new Bus(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS, numeroEjes, numeroSalidasEmergencia, tiempoCarga, autonomia);
            } else {
                boolean  esEnchufable = JOptionPane.showConfirmDialog(null, "¿Se puede conectar?", "Enchufe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (esEnchufable) {
                    bus = new Bus(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS, numeroEjes, numeroSalidasEmergencia);
                } else {
                    boolean  esLigero = JOptionPane.showConfirmDialog(null, "¿Es ligero?", "Ligero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    bus = new Bus(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, numeroPasajeros, numeroPuertas, capacidadMaletero, tieneAireAcondicionado, tieneCamaraReversa, numeroBolsasAire, tieneABS, numeroEjes, numeroSalidasEmergencia, esLigero);
                }
            }
            try {
                servicioVehiculo.registrarVehiculo(bus);
                JOptionPane.showMessageDialog(null, "Bus registrada exitosamente.");
                return bus;
            } catch (RegistroVehiculoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (tipoVehiculo==7) {
            double capacidadCarga = 0;
            int numeroEjes = 0;
            try {
                capacidadCarga = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la capacidad del maletero (Solo numeros)"));
                numeroEjes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de ejes (Solo numeros)"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error");
            }
            boolean tieneAireAcondicionado = JOptionPane.showConfirmDialog(null, "¿Tiene aire acondicionado?", "Aire Acondicionado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneFrenosDeAire = JOptionPane.showConfirmDialog(null, "¿Tiene frenos de aire?", "Frenos de Aire", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            boolean tieneABS = JOptionPane.showConfirmDialog(null, "¿Tiene frenos ABS?", "ABS", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            String tipoCamion = JOptionPane.showInputDialog("Ingrese el tipo de camion");

            Camion camion;
            if (esElectrico){
                String tiempoCarga = JOptionPane.showInputDialog("Ingrese el tiempo de carga");
                String autonomia = JOptionPane.showInputDialog("Ingrese la autonomia electrica");
                camion = new Camion(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, capacidadCarga, tieneAireAcondicionado, tieneFrenosDeAire, tieneABS, numeroEjes, tipoCamion, tiempoCarga, autonomia);
            } else {
                boolean  esEnchufable = JOptionPane.showConfirmDialog(null, "¿Se puede conectar?", "Enchufe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (esEnchufable) {
                    camion = new Camion(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, capacidadCarga, tieneAireAcondicionado, tieneFrenosDeAire, tieneABS, numeroEjes, tipoCamion);
                } else {
                    boolean  esLigero = JOptionPane.showConfirmDialog(null, "¿Es ligero?", "Ligero", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    camion = new Camion(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica, capacidadCarga, tieneAireAcondicionado, tieneFrenosDeAire, tieneABS, numeroEjes, tipoCamion, esLigero);
                }
            }
            try {
                servicioVehiculo.registrarVehiculo(camion);
                JOptionPane.showMessageDialog(null, "Camion registrada exitosamente.");
                return camion;
            } catch (RegistroVehiculoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    private static void venderVehiculo(ServicioCliente servicioCliente, ServicioVehiculo servicioVehiculo, ServicioTransaccion servicioTransaccion, Empleado empleado) {
        String documentoCliente = JOptionPane.showInputDialog("Ingrese el documento del cliente:");
        Cliente cliente = servicioCliente.buscarCliente(documentoCliente);

        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            return;
        }
        String placaVehiculo = JOptionPane.showInputDialog("Ingrese la placa del vehículo a alquilar:");
        Vehiculo vehiculo = servicioVehiculo.buscarVehiculos(placaVehiculo);

        if (vehiculo==null) {
            JOptionPane.showMessageDialog(null, "Vehículo no encontrado.");
            return;
        }

        if (servicioVehiculo.venderVehiculo(vehiculo, cliente, empleado, servicioTransaccion)) {
            JOptionPane.showMessageDialog(null, "Vehículo vendido exitosamente.");
            vehiculo.setDisponible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Error al vender el vehículo.");
        }
    }

    private static void verClientes(ServicioCliente servicioCliente) {
        List<Cliente> clientes = servicioCliente.verClientes();
        StringBuilder infoEmpleados = new StringBuilder("Clientes:\n");
        for (Cliente cliente : clientes) {
            infoEmpleados.append("Documento: ").append(cliente.getDocumento())
                    .append(", Nombre: ").append(cliente.getNombre());
        }
        JOptionPane.showMessageDialog(null, infoEmpleados.toString());
    }


}