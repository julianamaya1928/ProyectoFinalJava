package uniquindio.edu.co.servicio;

import uniquindio.edu.co.excepciones.AutenticacionException;
import uniquindio.edu.co.modelo.*;

import java.util.ArrayList;
import java.util.List;

public class ServicioEmpleado {
    private List<Empleado> empleados;
    private List<Cliente> clientes;
    private List<Vehiculo> vehiculos;
    private List<Transaccion> transacciones;

    public ServicioEmpleado() {
        this.empleados = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.transacciones = new ArrayList<>();
    }

    public void registrarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public Empleado buscarEmpleado(String documento) {
        for (Empleado e : empleados) {
            if (e.getDocumento().equals(documento)) {
                return e;
            }
        }
        return null;
    }

    public boolean autenticarEmpleado(String documento, String clave) throws AutenticacionException {
        Empleado empleado = buscarEmpleado(documento);
        if (empleado == null || !empleado.getClave().equals(clave) || empleado.isBloqueado()) {
            throw new AutenticacionException("Credenciales incorrectas.");
        }
        return true;
    }

    public void bloquearEmpleado(Empleado empleado) {
        if (empleado != null) {
            empleado.setBloqueado(true);
        }
    }

    public void desbloquearEmpleado(Empleado empleado) {
        if (empleado != null) {
            empleado.setBloqueado(false);
        }
    }


    public String recuperarClave(String documento, String respuesta) throws AutenticacionException {
        Empleado empleado = buscarEmpleado(documento);

        if (empleado == null) {
            throw new AutenticacionException("El empleado con el documento proporcionado no existe.");
        }

        if (!empleado.getRespuestaSeguridad().equals(respuesta)) {
            throw new AutenticacionException("La respuesta de seguridad es incorrecta.");
        }

        return empleado.getClave();
    }

    public List<Empleado> verEmpleados() {
        return empleados;
    }

    public boolean actualizarEmpleado(Empleado empleado, String nuevoNombre, String nuevaClave, String nuevaPregunta, String nuevaRespuesta) {
        if (empleado != null) {
            empleado.setNombre(nuevoNombre);
            empleado.setClave(nuevaClave);
            empleado.setPreguntaSeguridad(nuevaPregunta);
            empleado.setRespuestaSeguridad(nuevaRespuesta);
            return true;
        }
        return false;
    }

}

