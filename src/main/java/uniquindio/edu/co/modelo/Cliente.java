package uniquindio.edu.co.modelo;

public class Cliente extends Persona {
    private String email;

    public Cliente(String nombre, String documento, String telefono, String email) {
        super(nombre, documento, telefono);
        this.email = email;
    }

    public String getEmail() { return email; }
}

