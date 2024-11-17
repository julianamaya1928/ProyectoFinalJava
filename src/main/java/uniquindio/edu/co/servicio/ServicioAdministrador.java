package uniquindio.edu.co.servicio;

import uniquindio.edu.co.excepciones.AutenticacionException;
import uniquindio.edu.co.modelo.Empleado;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioAdministrador {
    private Map<String, Empleado> administradores;
    private ServicioEmpleado servicioEmpleado;

    public ServicioAdministrador(ServicioEmpleado servicioEmpleado) {
        administradores = new HashMap<>();
        this.servicioEmpleado = servicioEmpleado;
        cargarAdministradores();
    }

    public void autenticarAdministrador(String documento, String clave) throws AutenticacionException {
        Empleado administrador = administradores.get(documento);
        if (administrador == null || !administrador.getClave().equals(clave)) {
            throw new AutenticacionException("Documento o clave incorrectos para administrador.");
        }
    }

    public void registrarEmpleado(String documento, String nombre, String clave, String pregunta, String respuesta) throws AutenticacionException {
        if (servicioEmpleado.buscarEmpleado(documento) != null) {
            throw new AutenticacionException("Documento ya existente en base de datos.");
        }
        Empleado empleado = new Empleado(documento, nombre, clave, false, pregunta, respuesta);
        servicioEmpleado.registrarEmpleado(empleado);
    }

    public List<Empleado> verEmpleados() {
        return servicioEmpleado.verEmpleados();
    }

    public boolean actualizarEmpleado(String documento, String nuevoNombre, String nuevaClave, String nuevaPregunta, String nuevaRespuesta) {
        Empleado empleado = servicioEmpleado.buscarEmpleado(documento);
        if (empleado != null) {
            servicioEmpleado.actualizarEmpleado(empleado, nuevoNombre, nuevaClave, nuevaPregunta, nuevaRespuesta);
            return true;
        }
        return false;
    }

    public void bloquearEmpleado(String documento) {
        Empleado empleado = servicioEmpleado.buscarEmpleado(documento);
        if (empleado != null) {
            servicioEmpleado.bloquearEmpleado(empleado);
        }
    }

    public void desbloquearEmpleado(String documento) {
        Empleado empleado = servicioEmpleado.buscarEmpleado(documento);
        if (empleado != null) {
            servicioEmpleado.desbloquearEmpleado(empleado);
        }
    }

    private void cargarAdministradores() {
        Empleado admin1 = new Empleado("12345", "admin1", "adminpass", true, "Pregunta?", "Respuesta");
        administradores.put(admin1.getDocumento(), admin1);
    }
}

