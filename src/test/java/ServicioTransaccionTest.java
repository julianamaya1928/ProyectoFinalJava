package test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniquindio.edu.co.modelo.Empleado;
import uniquindio.edu.co.modelo.Transaccion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ServicioTransaccionTest {

    private Empleado empleado1;
    private Empleado empleado2;
    private Transaccion transaccion1;
    private Transaccion transaccion2;
    private Transaccion transaccion3;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setUp() {
        ServicioTransaccion.transacciones = new ArrayList<>(); // Asegurarse de limpiar la lista estática entre pruebas

        empleado1 = new Empleado("123", "Juan Pérez", "clave123", false, "Pregunta?", "Respuesta");
        empleado2 = new Empleado("456", "Ana López", "clave456", false, "Pregunta?", "Respuesta");

        transaccion1 = new Transaccion(new Date(2024 - 1900, 10, 1), empleado1, "Compra");
        transaccion2 = new Transaccion(new Date(2024 - 1900, 10, 5), empleado1, "Venta");
        transaccion3 = new Transaccion(new Date(2024 - 1900, 10, 10), empleado2, "Alquiler");

        ServicioTransaccion.registrarTransaccion(transaccion1);
        ServicioTransaccion.registrarTransaccion(transaccion2);
        ServicioTransaccion.registrarTransaccion(transaccion3);
    }

    @Test
    public void testRegistrarTransaccion() {
        Transaccion nuevaTransaccion = new Transaccion(new Date(), empleado1, "Venta");
        ServicioTransaccion.registrarTransaccion(nuevaTransaccion);

        assertTrue(ServicioTransaccion.transacciones.contains(nuevaTransaccion));
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testObtenerTransaccionesPorPeriodoEmpleado() {
        Date fechaInicio = new Date(2024 - 1900, 10, 1);
        Date fechaFin = new Date(2024 - 1900, 10, 6);

        List<Transaccion> resultado = ServicioTransaccion.obtenerTransaccionesPorPeriodoEmpleado(fechaInicio, fechaFin, "123");

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(transaccion1));
        assertTrue(resultado.contains(transaccion2));
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testObtenerTransaccionesPorPeriodoEmpleadoSinResultados() {
        Date fechaInicio = new Date(2024 - 1900, 10, 11);
        Date fechaFin = new Date(2024 - 1900, 10, 15);

        List<Transaccion> resultado = ServicioTransaccion.obtenerTransaccionesPorPeriodoEmpleado(fechaInicio, fechaFin, "123");

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testObtenerTransaccionesPorTipo() {
        List<Transaccion> resultado = ServicioTransaccion.obtenerTransaccionesPorTipo("Venta");

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(transaccion2));
    }

    @Test
    public void testObtenerTransaccionesPorTipoSinResultados() {
        List<Transaccion> resultado = ServicioTransaccion.obtenerTransaccionesPorTipo("Donación");

        assertTrue(resultado.isEmpty());
    }
}
