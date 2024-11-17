package uniquindio.edu.co.modelo;

public class Camion extends Vehiculo{
    private double capacidadCarga;
    private boolean tieneAireAcondicionado;
    private boolean tieneFrenosDeAire;
    private boolean tieneABS;
    private int numeroEjes;
    private String tipoCamion;
    private String tiempoCarga;
    private String autonomiaElectrica;
    private boolean esEnchufable;
    private boolean esLigero;

    public Camion(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, double capacidadCarga, boolean tieneAireAcondicionado, boolean tieneFrenosDeAire, boolean tieneABS, int numeroEjes, String tipoCamion, String tiempoCarga, String autonomiaElectrica) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.capacidadCarga = capacidadCarga;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.tieneFrenosDeAire = tieneFrenosDeAire;
        this.tieneABS = tieneABS;
        this.numeroEjes = numeroEjes;
        this.tipoCamion = tipoCamion;
        this.tiempoCarga = tiempoCarga;
        this.autonomiaElectrica = autonomiaElectrica;
    }

    public Camion(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, double capacidadCarga, boolean tieneAireAcondicionado, boolean tieneFrenosDeAire, boolean tieneABS, int numeroEjes, String tipoCamion) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.capacidadCarga = capacidadCarga;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.tieneFrenosDeAire = tieneFrenosDeAire;
        this.tieneABS = tieneABS;
        this.numeroEjes = numeroEjes;
        this.tipoCamion = tipoCamion;
        this.esEnchufable = true;
    }

    public Camion(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, double capacidadCarga, boolean tieneAireAcondicionado, boolean tieneFrenosDeAire, boolean tieneABS, int numeroEjes, String tipoCamion, boolean esLigero) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.capacidadCarga = capacidadCarga;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.tieneFrenosDeAire = tieneFrenosDeAire;
        this.tieneABS = tieneABS;
        this.numeroEjes = numeroEjes;
        this.tipoCamion = tipoCamion;
        this.esEnchufable = false;
        this.esLigero = esLigero;
    }
}
