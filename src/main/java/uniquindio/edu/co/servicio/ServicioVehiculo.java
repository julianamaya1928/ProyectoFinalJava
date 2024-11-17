package uniquindio.edu.co.servicio;


import uniquindio.edu.co.excepciones.RegistroVehiculoException;
import uniquindio.edu.co.modelo.Cliente;
import uniquindio.edu.co.modelo.Empleado;
import uniquindio.edu.co.modelo.Transaccion;
import uniquindio.edu.co.modelo.Vehiculo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ServicioVehiculo {
    private List<Vehiculo> vehiculos;

    public ServicioVehiculo() {
        this.vehiculos = new ArrayList<>();
    }

    public boolean venderVehiculo(Vehiculo vehiculo, Cliente cliente, Empleado empleado, ServicioTransaccion servicioTransaccion) {
        if (vehiculos.remove(vehiculo)) {
            Transaccion transaccion = new Transaccion(UUID.randomUUID().toString(), empleado, cliente, vehiculo, new Date(), "Venta", "Venta de vehículo: " + vehiculo.getMarca());
            servicioTransaccion.registrarTransaccion(transaccion);
            return true;
        }
        return false;
    }

    public boolean alquilarVehiculo(Vehiculo vehiculo, Cliente cliente, Empleado empleado, ServicioTransaccion servicioTransaccion) {
        if (vehiculos.contains(vehiculo)) {
            Transaccion transaccion = new Transaccion(UUID.randomUUID().toString(), empleado, cliente, vehiculo, new Date(), "Alquiler", "Alquiler de vehículo: " + vehiculo.getMarca());
            servicioTransaccion.registrarTransaccion(transaccion);
            return true;
        }
        return false;
    }


    public boolean comprarVehiculo(Vehiculo vehiculo, Cliente cliente, Empleado empleado, boolean pasoRevisionTecnica, ServicioTransaccion servicioTransaccion) {
        if (pasoRevisionTecnica) {
            Transaccion transaccion = new Transaccion(UUID.randomUUID().toString(), empleado, cliente, vehiculo, new Date(), "Compra", "Compra de vehículo: " + vehiculo.getMarca());
            servicioTransaccion.registrarTransaccion(transaccion);
            return true;
        }
        return false;
    }

    public void registrarVehiculo(Vehiculo vehiculo) throws RegistroVehiculoException {
        if (vehiculos.contains(vehiculo)) {
            throw new RegistroVehiculoException("El vehículo ya está registrado en el sistema.");
        }
        vehiculos.add(vehiculo);
    }

    public Vehiculo buscarVehiculos(String placaVehiculo) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPlaca().equals(placaVehiculo) && vehiculo.isDisponible()) {
                return vehiculo;
            }
        }
        return null;
    }
}

