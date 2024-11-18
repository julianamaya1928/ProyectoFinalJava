package test.java;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteNoEncontradoExceptionTest {

    @Test
    public void testClienteNoEncontradoExceptionMessage() {
        String mensajeEsperado = "El cliente no fue encontrado en la base de datos";

        // Lanza la excepción y verifica su mensaje
        ClienteNoEncontradoException exception = assertThrows(
            ClienteNoEncontradoException.class,
            () -> { throw new ClienteNoEncontradoException(mensajeEsperado); }
        );

        assertEquals(mensajeEsperado, exception.getMessage());
    }

    @Test
    public void testClienteNoEncontradoExceptionInheritance() {
        ClienteNoEncontradoException exception = new ClienteNoEncontradoException("Error de cliente no encontrado");
        assertTrue(exception instanceof Exception);
    }
}
