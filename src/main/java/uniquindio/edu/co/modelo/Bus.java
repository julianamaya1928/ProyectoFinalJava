package uniquindio.edu.co.modelo;

public class Bus extends Vehiculo{
    private int numeroPasajeros;
    private int numeroPuertas;
    private double capacidadMaletero;
    private boolean tieneAireAcondicionado;
    private boolean tieneCamaraReversa;
    private int numeroBolsasAire;
    private boolean tieneABS;
    private int numeroEjes;
    private int numeroSalidasEmergencia;
    private String tiempoCarga;
    private String autonomiaElectrica;
    private boolean esEnchufable;
    private boolean esLigero;

    public Bus(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, int numeroPasajeros, int numeroPuertas, double capacidadMaletero, boolean tieneAireAcondicionado, boolean tieneCamaraReversa, int numeroBolsasAire, boolean tieneABS, int numeroEjes, int numeroSalidasEmergencia, String tiempoCarga, String autonomiaElectrica) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.numeroPasajeros = numeroPasajeros;
        this.numeroPuertas = numeroPuertas;
        this.capacidadMaletero = capacidadMaletero;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.tieneCamaraReversa = tieneCamaraReversa;
        this.numeroBolsasAire = numeroBolsasAire;
        this.tieneABS = tieneABS;
        this.numeroEjes = numeroEjes;
        this.numeroSalidasEmergencia = numeroSalidasEmergencia;
        this.tiempoCarga = tiempoCarga;
        this.autonomiaElectrica = autonomiaElectrica;
    }

    public Bus(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, int numeroPasajeros, int numeroPuertas, double capacidadMaletero, boolean tieneAireAcondicionado, boolean tieneCamaraReversa, int numeroBolsasAire, boolean tieneABS, int numeroEjes, int numeroSalidasEmergencia) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.numeroPasajeros = numeroPasajeros;
        this.numeroPuertas = numeroPuertas;
        this.capacidadMaletero = capacidadMaletero;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.tieneCamaraReversa = tieneCamaraReversa;
        this.numeroBolsasAire = numeroBolsasAire;
        this.tieneABS = tieneABS;
        this.numeroEjes = numeroEjes;
        this.numeroSalidasEmergencia = numeroSalidasEmergencia;
        this.esEnchufable = true;
    }

    public Bus(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, int numeroPasajeros, int numeroPuertas, double capacidadMaletero, boolean tieneAireAcondicionado, boolean tieneCamaraReversa, int numeroBolsasAire, boolean tieneABS, int numeroEjes, int numeroSalidasEmergencia, boolean esLigero) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.numeroPasajeros = numeroPasajeros;
        this.numeroPuertas = numeroPuertas;
        this.capacidadMaletero = capacidadMaletero;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.tieneCamaraReversa = tieneCamaraReversa;
        this.numeroBolsasAire = numeroBolsasAire;
        this.tieneABS = tieneABS;
        this.numeroEjes = numeroEjes;
        this.numeroSalidasEmergencia = numeroSalidasEmergencia;
        this.esEnchufable = false;
        this.esLigero = esLigero;
    }
}
