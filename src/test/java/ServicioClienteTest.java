package test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniquindio.edu.co.modelo.Cliente;
import uniquindio.edu.co.util.EnvioEmail;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioClienteTest {

    private ServicioCliente servicioCliente;

    @BeforeEach
    public void setUp() {
        servicioCliente = new ServicioCliente();
    }

    @Test
    public void testVerClientesInicialmenteVacio() {
        List<Cliente> clientes = servicioCliente.verClientes();
        assertTrue(clientes.isEmpty(), "La lista de clientes debería estar vacía inicialmente");
    }

    @Test
    public void testRegistrarCliente() {
        // Crear un mock de EnvioEmail
        EnvioEmail envioEmailMock = mock(EnvioEmail.class);
        
        // Crear un cliente
        Cliente cliente = new Cliente("123456", "Juan Pérez", "juan@correo.com", "Calle 1", "3111234567");

        // Simular comportamiento de EnvioEmail
        doNothing().when(envioEmailMock).enviarNotificacion();

        // Registrar cliente
        servicioCliente.registrarCliente(cliente);

        // Verificar que se haya añadido el cliente
        assertEquals(1, servicioCliente.verClientes().size());
        assertEquals(cliente, servicioCliente.verClientes().get(0));
    }

    @Test
    public void testBuscarClienteExistente() {
        Cliente cliente = new Cliente("123456", "Juan Pérez", "juan@correo.com", "Calle 1", "3111234567");
        servicioCliente.registrarCliente(cliente);

        Cliente encontrado = servicioCliente.buscarCliente("123456");
        assertNotNull(encontrado, "El cliente debería ser encontrado");
        assertEquals(cliente, encontrado);
    }

    @Test
    public void testBuscarClienteNoExistente() {
        Cliente encontrado = servicioCliente.buscarCliente("999999");
        assertNull(encontrado, "El cliente no debería existir en la lista");
    }
}
