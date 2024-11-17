package uniquindio.edu.co.util;

import uniquindio.edu.co.modelo.Empleado;
import uniquindio.edu.co.modelo.Transaccion;
import uniquindio.edu.co.modelo.Vehiculo;

import java.util.List;

public class GeneradorReportes {

    public static String generarReporteTransacciones(List<Transaccion> transacciones) {
        StringBuilder reporte = new StringBuilder("Reporte de Transacciones:\n");
        for (Transaccion transaccion : transacciones) {
            reporte.append(UtilidadesFormato.formatoTransaccion(transaccion)).append("\n\n");
        }
        return reporte.toString();
    }

    public static String generarReporteVehiculos(List<Vehiculo> vehiculos) {
        StringBuilder reporte = new StringBuilder("Reporte de Vehículos:\n");
        for (Vehiculo vehiculo : vehiculos) {
            reporte.append(vehiculo.toString()).append("\n\n");
        }
        return reporte.toString();
    }

    public static String generarReporteEmpleados(List<Empleado> empleados) {
        StringBuilder reporte = new StringBuilder("Reporte de Empleados:\n");
        for (Empleado empleado : empleados) {
            reporte.append(UtilidadesFormato.formatoEmpleado(empleado)).append("\n\n");
        }
        return reporte.toString();
    }
}
