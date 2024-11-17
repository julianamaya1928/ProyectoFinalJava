package uniquindio.edu.co.util;

import uniquindio.edu.co.modelo.Empleado;
import uniquindio.edu.co.modelo.Transaccion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilidadesFormato {

    public static String formatearFecha(Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha);
    }

    public static String formatoTransaccion(Transaccion transaccion) {
        return "ID Transacción: " + transaccion.getId() +
                "\nEmpleado: " + transaccion.getEmpleado().getNombre() +
                "\nFecha: " + formatearFecha(transaccion.getFecha()) +
                "\nTipo: " + transaccion.getTipo() +
                "\nDetalles: " + transaccion.getDetalles();
    }

    public static String formatoEmpleado(Empleado empleado) {
        return "Nombre: " + empleado.getNombre() +
                "\nDocumento: " + empleado.getDocumento();
    }
}
