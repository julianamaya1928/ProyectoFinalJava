package uniquindio.edu.co.modelo;

public abstract class Persona {
    private String nombre;
    private String documento;
    private String telefono;

    public Persona(String nombre, String documento, String telefono) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }
    public String getTelefono() { return telefono; }
}

