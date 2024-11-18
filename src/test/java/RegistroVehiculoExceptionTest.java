package test.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegistroVehiculoExceptionTest {

    @Test
    public void testRegistroVehiculoExceptionMessage() {
        String mensajeEsperado = "El vehículo ya está registrado";

        // Lanza la excepción y verifica su mensaje
        RegistroVehiculoException exception = assertThrows(
            RegistroVehiculoException.class,
            () -> { throw new RegistroVehiculoException(mensajeEsperado); }
        );

        assertEquals(mensajeEsperado, exception.getMessage());
    }

    @Test
    public void testRegistroVehiculoExceptionInheritance() {
        RegistroVehiculoException exception = new RegistroVehiculoException("Error de registro de vehículo");
        assertTrue(exception instanceof Exception);
    }
}
