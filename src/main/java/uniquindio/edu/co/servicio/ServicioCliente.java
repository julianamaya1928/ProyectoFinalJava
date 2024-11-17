package uniquindio.edu.co.servicio;

import uniquindio.edu.co.modelo.Cliente;
import uniquindio.edu.co.util.EnvioEmail;

import java.util.ArrayList;
import java.util.List;

public class ServicioCliente {
    private List<Cliente> clientes;

    public ServicioCliente() {
        this.clientes = new ArrayList<>();
    }

    public List<Cliente> verClientes() {
        return clientes;
    }

    public void registrarCliente(Cliente cliente) {
        EnvioEmail envioEmail = new EnvioEmail(cliente.getEmail(), "Registrado", "Fuiste registrado en Tu Caroo");
        envioEmail.enviarNotificacion();
        clientes.add(cliente);
    }

    public Cliente buscarCliente(String documento) {
        for (Cliente c : clientes) {
            if (c.getDocumento().equals(documento)) {
                return c;
            }
        }
        return null;
    }
}
