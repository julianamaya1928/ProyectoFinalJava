package test.java;

import org.junit.jupiter.api.Test;

import uniquindio.edu.co.excepciones.AutenticacionException;

import static org.junit.jupiter.api.Assertions.*;

public class AutenticacionExceptionTest {
    

    @Test
    public void testAutenticacionExceptionMessage() {
        String mensajeEsperado = "Usuario o contraseña incorrectos";
        
        // Lanza la excepción y verifica su mensaje
        AutenticacionException exception = assertThrows(
            AutenticacionException.class,
            () -> { throw new AutenticacionException(mensajeEsperado); }
        );

        assertEquals(mensajeEsperado, exception.getMessage());
    }

    @Test
    public void testAutenticacionExceptionInheritance() {
        AutenticacionException exception = new AutenticacionException("Error de autenticación");
        assertTrue(exception instanceof Exception);
    }

}
