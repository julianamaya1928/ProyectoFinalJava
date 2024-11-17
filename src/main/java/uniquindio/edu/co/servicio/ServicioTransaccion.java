package uniquindio.edu.co.servicio;

import uniquindio.edu.co.modelo.Empleado;
import uniquindio.edu.co.modelo.Transaccion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServicioTransaccion {
    private static List<Transaccion> transacciones = new ArrayList<>();

    public static void registrarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    public static List<Transaccion> obtenerTransaccionesPorPeriodoEmpleado(Date fechaInicio, Date fechaFin, String documento) {
        List<Transaccion> resultado = new ArrayList<>();
        for (Transaccion t : transacciones) {
            if (!t.getFecha().before(fechaInicio) && !t.getFecha().after(fechaFin) && t.getEmpleado().getDocumento().equals(documento)) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    public List<Transaccion> obtenerTransaccionesPorTipo(String tipo) {
        List<Transaccion> resultado = new ArrayList<>();
        for (Transaccion t : transacciones) {
            if (t.getTipo().equalsIgnoreCase(tipo)) {
                resultado.add(t);
            }
        }
        return resultado;
    }
}

