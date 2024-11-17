package uniquindio.edu.co.modelo;

import java.util.Date;

public class Transaccion {
    private String id;
    private Empleado empleado;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Date fecha;
    private String tipo;      
    private String detalles;   

    public Transaccion(String id, Empleado empleado, Cliente cliente, Vehiculo vehiculo, Date fecha, String tipo, String detalles) {
        this.id = id;
        this.empleado = empleado;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fecha = fecha;
        this.tipo = tipo;
        this.detalles = detalles;
    }

    public String getId() {
        return id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}


