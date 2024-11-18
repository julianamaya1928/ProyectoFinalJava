package test.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransaccionInvalidaExceptionTest {

    @Test
    public void testTransaccionInvalidaExceptionMessage() {
        String mensajeEsperado = "La transacción no es válida";

        // Lanza la excepción y verifica su mensaje
        TransaccionInvalidaException exception = assertThrows(
            TransaccionInvalidaException.class,
            () -> { throw new TransaccionInvalidaException(mensajeEsperado); }
        );

        assertEquals(mensajeEsperado, exception.getMessage());
    }

    @Test
    public void testTransaccionInvalidaExceptionInheritance() {
        TransaccionInvalidaException exception = new TransaccionInvalidaException("Error de transacción inválida");
        assertTrue(exception instanceof Exception);
    }
}
