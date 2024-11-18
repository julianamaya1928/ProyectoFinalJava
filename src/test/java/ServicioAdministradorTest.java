package test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniquindio.edu.co.excepciones.AutenticacionException;
import uniquindio.edu.co.modelo.Empleado;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioAdministradorTest {

    private ServicioAdministrador servicioAdministrador;
    private ServicioEmpleado servicioEmpleadoMock;

    @BeforeEach
    public void setUp() {
        servicioEmpleadoMock = mock(ServicioEmpleado.class);
        servicioAdministrador = new ServicioAdministrador(servicioEmpleadoMock);
    }

    @Test
    public void testAutenticarAdministradorExitoso() throws AutenticacionException {
        // Setup
        String documento = "12345";
        String clave = "adminpass";

        Empleado admin = new Empleado(documento, "admin1", clave, true, "Pregunta?", "Respuesta");
        servicioAdministrador = new ServicioAdministrador(servicioEmpleadoMock); // Reiniciar con admin cargado

        // Act & Assert
        assertDoesNotThrow(() -> servicioAdministrador.autenticarAdministrador(documento, clave));
    }

    @Test
    public void testAutenticarAdministradorFalla() {
        String documento = "12345";
        String claveIncorrecta = "wrongpass";

        assertThrows(AutenticacionException.class, 
            () -> servicioAdministrador.autenticarAdministrador(documento, claveIncorrecta));
    }

    @Test
    public void testRegistrarEmpleadoExitoso() throws AutenticacionException {
        String documento = "67890";
        String nombre = "Empleado1";
        String clave = "password";
        String pregunta = "Pregunta1";
        String respuesta = "Respuesta1";

        when(servicioEmpleadoMock.buscarEmpleado(documento)).thenReturn(null);

        servicioAdministrador.registrarEmpleado(documento, nombre, clave, pregunta, respuesta);

        verify(servicioEmpleadoMock, times(1)).registrarEmpleado(any(Empleado.class));
    }

    @Test
    public void testRegistrarEmpleadoFallaDocumentoExistente() {
        String documento = "67890";
        when(servicioEmpleadoMock.buscarEmpleado(documento)).thenReturn(new Empleado(documento, "Nombre", "clave", false, "Pregunta", "Respuesta"));

        assertThrows(AutenticacionException.class, 
            () -> servicioAdministrador.registrarEmpleado(documento, "NuevoEmpleado", "clave", "Pregunta", "Respuesta"));
    }

    @Test
    public void testVerEmpleados() {
        List<Empleado> empleadosMock = List.of(
            new Empleado("123", "Empleado1", "clave1", false, "Pregunta1", "Respuesta1"),
            new Empleado("456", "Empleado2", "clave2", false, "Pregunta2", "Respuesta2")
        );

        when(servicioEmpleadoMock.verEmpleados()).thenReturn(empleadosMock);

        List<Empleado> empleados = servicioAdministrador.verEmpleados();

        assertEquals(2, empleados.size());
        verify(servicioEmpleadoMock, times(1)).verEmpleados();
    }

    @Test
    public void testActualizarEmpleadoExitoso() {
        String documento = "123";
        Empleado empleado = new Empleado(documento, "Empleado1", "clave1", false, "Pregunta", "Respuesta");
        when(servicioEmpleadoMock.buscarEmpleado(documento)).thenReturn(empleado);

        boolean resultado = servicioAdministrador.actualizarEmpleado(documento, "NuevoNombre", "NuevaClave", "NuevaPregunta", "NuevaRespuesta");

        assertTrue(resultado);
        verify(servicioEmpleadoMock, times(1)).actualizarEmpleado(eq(empleado), anyString(), anyString(), anyString(), anyString());
    }

    @Test
    public void testActualizarEmpleadoFalla() {
        String documento = "999";
        when(servicioEmpleadoMock.buscarEmpleado(documento)).thenReturn(null);

        boolean resultado = servicioAdministrador.actualizarEmpleado(documento, "NuevoNombre", "NuevaClave", "NuevaPregunta", "NuevaRespuesta");

        assertFalse(resultado);
    }

    @Test
    public void testBloquearEmpleado() {
        String documento = "123";
        Empleado empleado = new Empleado(documento, "Empleado1", "clave1", false, "Pregunta", "Respuesta");
        when(servicioEmpleadoMock.buscarEmpleado(documento)).thenReturn(empleado);

        servicioAdministrador.bloquearEmpleado(documento);

        verify(servicioEmpleadoMock, times(1)).bloquearEmpleado(empleado);
    }

    @Test
    public void testDesbloquearEmpleado() {
        String documento = "123";
        Empleado empleado = new Empleado(documento, "Empleado1", "clave1", false, "Pregunta", "Respuesta");
        when(servicioEmpleadoMock.buscarEmpleado(documento)).thenReturn(empleado);

        servicioAdministrador.desbloquearEmpleado(documento);

        verify(servicioEmpleadoMock, times(1)).desbloquearEmpleado(empleado);
    }
}
