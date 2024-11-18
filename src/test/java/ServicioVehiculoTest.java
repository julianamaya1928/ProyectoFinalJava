package test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uniquindio.edu.co.excepciones.RegistroVehiculoException;
import uniquindio.edu.co.modelo.Cliente;
import uniquindio.edu.co.modelo.Empleado;
import uniquindio.edu.co.modelo.Transaccion;
import uniquindio.edu.co.modelo.Vehiculo;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioVehiculoTest {

    private ServicioVehiculo servicioVehiculo;
    private ServicioTransaccion servicioTransaccionMock;
    private Vehiculo vehiculoMock;
    private Cliente clienteMock;
    private Empleado empleadoMock;

    @BeforeEach
    public void setUp() {
        servicioVehiculo = new ServicioVehiculo();
        servicioTransaccionMock = mock(ServicioTransaccion.class);
        vehiculoMock = mock(Vehiculo.class);
        clienteMock = mock(Cliente.class);
        empleadoMock = mock(Empleado.class);

        when(vehiculoMock.getMarca()).thenReturn("Toyota");
        when(vehiculoMock.getPlaca()).thenReturn("XYZ123");
        when(vehiculoMock.isDisponible()).thenReturn(true);
    }

    @Test
    public void testRegistrarVehiculo() throws RegistroVehiculoException {
        servicioVehiculo.registrarVehiculo(vehiculoMock);
        Vehiculo buscado = servicioVehiculo.buscarVehiculos("XYZ123");
        assertEquals(vehiculoMock, buscado);
    }

    @Test
    public void testRegistrarVehiculoExistenteLanzaExcepcion() {
        assertThrows(RegistroVehiculoException.class, () -> {
            servicioVehiculo.registrarVehiculo(vehiculoMock);
            servicioVehiculo.registrarVehiculo(vehiculoMock);
        });
    }

    @Test
    public void testVenderVehiculo() {
        servicioVehiculo.registrarVehiculo(vehiculoMock);
        boolean resultado = servicioVehiculo.venderVehiculo(vehiculoMock, clienteMock, empleadoMock, servicioTransaccionMock);

        assertTrue(resultado);
        verify(servicioTransaccionMock, times(1)).registrarTransaccion(any(Transaccion.class));
    }

    @Test
    public void testVenderVehiculoNoRegistrado() {
        boolean resultado = servicioVehiculo.venderVehiculo(vehiculoMock, clienteMock, empleadoMock, servicioTransaccionMock);

        assertFalse(resultado);
        verify(servicioTransaccionMock, never()).registrarTransaccion(any(Transaccion.class));
    }

    @Test
    public void testAlquilarVehiculo() {
        servicioVehiculo.registrarVehiculo(vehiculoMock);
        boolean resultado = servicioVehiculo.alquilarVehiculo(vehiculoMock, clienteMock, empleadoMock, servicioTransaccionMock);

        assertTrue(resultado);
        verify(servicioTransaccionMock, times(1)).registrarTransaccion(any(Transaccion.class));
    }

    @Test
    public void testComprarVehiculoConRevisionTecnica() {
        boolean resultado = servicioVehiculo.comprarVehiculo(vehiculoMock, clienteMock, empleadoMock, true, servicioTransaccionMock);

        assertTrue(resultado);
        verify(servicioTransaccionMock, times(1)).registrarTransaccion(any(Transaccion.class));
    }

    @Test
    public void testComprarVehiculoSinRevisionTecnica() {
        boolean resultado = servicioVehiculo.comprarVehiculo(vehiculoMock, clienteMock, empleadoMock, false, servicioTransaccionMock);

        assertFalse(resultado);
        verify(servicioTransaccionMock, never()).registrarTransaccion(any(Transaccion.class));
    }

    @Test
    public void testBuscarVehiculosExistente() {
        servicioVehiculo.registrarVehiculo(vehiculoMock);
        Vehiculo resultado = servicioVehiculo.buscarVehiculos("XYZ123");

        assertNotNull(resultado);
        assertEquals("XYZ123", resultado.getPlaca());
    }

    @Test
    public void testBuscarVehiculosNoExistente() {
        Vehiculo resultado = servicioVehiculo.buscarVehiculos("ABC123");

        assertNull(resultado);
    }
}
