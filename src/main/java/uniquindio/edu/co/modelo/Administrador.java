package uniquindio.edu.co.modelo;

public class Administrador extends Empleado {
    public Administrador(String nombre, String documento, String clave, String pregunta, String respuesta) {
        super(documento, nombre, clave, true, pregunta, respuesta );
    }

}
