package test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniquindio.edu.co.excepciones.AutenticacionException;
import uniquindio.edu.co.modelo.Empleado;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioEmpleadoTest {

    private ServicioEmpleado servicioEmpleado;

    @BeforeEach
    public void setUp() {
        servicioEmpleado = new ServicioEmpleado();
    }

    @Test
    public void testRegistrarEmpleado() {
        Empleado empleado = new Empleado("123", "Juan Pérez", "clave123", false, "Pregunta?", "Respuesta");

        servicioEmpleado.registrarEmpleado(empleado);

        assertEquals(1, servicioEmpleado.verEmpleados().size());
        assertEquals(empleado, servicioEmpleado.buscarEmpleado("123"));
    }

    @Test
    public void testBuscarEmpleadoExistente() {
        Empleado empleado = new Empleado("123", "Juan Pérez", "clave123", false, "Pregunta?", "Respuesta");
        servicioEmpleado.registrarEmpleado(empleado);

        Empleado encontrado = servicioEmpleado.buscarEmpleado("123");

        assertNotNull(encontrado);
        assertEquals("Juan Pérez", encontrado.getNombre());
    }

    @Test
    public void testBuscarEmpleadoNoExistente() {
        Empleado encontrado = servicioEmpleado.buscarEmpleado("999");

        assertNull(encontrado);
    }

    @Test
    public void testAutenticarEmpleadoExitoso() throws AutenticacionException {
        Empleado empleado = new Empleado("123", "Juan Pérez", "clave123", false, "Pregunta?", "Respuesta");
        servicioEmpleado.registrarEmpleado(empleado);

        assertTrue(servicioEmpleado.autenticarEmpleado("123", "clave123"));
    }

    @Test
    public void testAutenticarEmpleadoFallaClaveIncorrecta() {
        Empleado empleado = new Empleado("123", "Juan Pérez", "clave123", false, "Pregunta?", "Respuesta");
        servicioEmpleado.registrarEmpleado(empleado);

        assertThrows(AutenticacionException.class, () -> servicioEmpleado.autenticarEmpleado("123", "claveIncorrecta"));
    }

    @Test
    public void testAutenticarEmpleadoFallaBloqueado() {
        Empleado empleado = new Empleado("123", "Juan Pérez", "clave123", true, "Pregunta?", "Respuesta");
        servicioEmpleado.registrarEmpleado(empleado);

        assertThrows(AutenticacionException.class, () -> servicioEmpleado.autenticarEmpleado("123", "clave123"));
    }

    @Test
    public void testBloquearEmpleado() {
        Empleado empleado = new Empleado("123", "Juan Pérez", "clave123", false, "Pregunta?", "Respuesta");
        servicioEmpleado.registrarEmpleado(empleado);

        servicioEmpleado.bloquearEmpleado(empleado);

        assertTrue(empleado.isBloqueado());
    }

    @Test
    public void testDesbloquearEmpleado() {
        Empleado empleado = new Empleado("123", "Juan Pérez", "clave123", true, "Pregunta?", "Respuesta");
        servicioEmpleado.registrarEmpleado(empleado);

        servicioEmpleado.desbloquearEmpleado(empleado);

        assertFalse(empleado.isBloqueado());
    }

    @Test
    public void testRecuperarClaveExitoso() throws AutenticacionException {
        Empleado empleado = new Empleado("123", "Juan Pérez", "clave123", false, "Pregunta?", "Respuesta");
        servicioEmpleado.registrarEmpleado(empleado);

        String clave = servicioEmpleado.recuperarClave("123", "Respuesta");

        assertEquals("clave123", clave);
    }

    @Test
    public void testRecuperarClaveFallaRespuestaIncorrecta() {
        Empleado empleado = new Empleado("123", "Juan Pérez", "clave123", false, "Pregunta?", "Respuesta");
        servicioEmpleado.registrarEmpleado(empleado);

        assertThrows(AutenticacionException.class, () -> servicioEmpleado.recuperarClave("123", "RespuestaIncorrecta"));
    }

    @Test
    public void testActualizarEmpleadoExitoso() {
        Empleado empleado = new Empleado("123", "Juan Pérez", "clave123", false, "Pregunta?", "Respuesta");
        servicioEmpleado.registrarEmpleado(empleado);

        boolean actualizado = servicioEmpleado.actualizarEmpleado(empleado, "Nuevo Nombre", "NuevaClave", "Nueva Pregunta", "Nueva Respuesta");

        assertTrue(actualizado);
        assertEquals("Nuevo Nombre", empleado.getNombre());
        assertEquals("NuevaClave", empleado.getClave());
    }

    @Test
    public void testActualizarEmpleadoFalla() {
        Empleado empleado = null;

        boolean actualizado = servicioEmpleado.actualizarEmpleado(empleado, "Nuevo Nombre", "NuevaClave", "Nueva Pregunta", "Nueva Respuesta");

        assertFalse(actualizado);
    }
}
