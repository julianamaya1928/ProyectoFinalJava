package uniquindio.edu.co.modelo;

public class PickUp extends Vehiculo {
    private int numeroPasajeros;
    private int numeroPuertas;
    private boolean tieneAireAcondicionado;
    private boolean tieneCamaraReversa;
    private int numeroBolsasAire;
    private boolean tieneABS;
    private boolean esCuatroPorCuatro;
    private double capacidadCajaCarga;
    private String tiempoCarga;
    private String autonomiaElectrica;
    private boolean esEnchufable;
    private boolean esLigero;

    public PickUp(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, int numeroPasajeros, int numeroPuertas, boolean tieneAireAcondicionado, boolean tieneCamaraReversa, int numeroBolsasAire, boolean tieneABS, boolean esCuatroPorCuatro, double capacidadCajaCarga, String tiempoCarga, String autonomiaElectrica) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.numeroPasajeros = numeroPasajeros;
        this.numeroPuertas = numeroPuertas;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.tieneCamaraReversa = tieneCamaraReversa;
        this.numeroBolsasAire = numeroBolsasAire;
        this.tieneABS = tieneABS;
        this.esCuatroPorCuatro = esCuatroPorCuatro;
        this.capacidadCajaCarga = capacidadCajaCarga;
        this.tiempoCarga = tiempoCarga;
        this.autonomiaElectrica = autonomiaElectrica;
    }

    public PickUp(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, int numeroPasajeros, int numeroPuertas, boolean tieneAireAcondicionado, boolean tieneCamaraReversa, int numeroBolsasAire, boolean tieneABS, boolean esCuatroPorCuatro, double capacidadCajaCarga) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.numeroPasajeros = numeroPasajeros;
        this.numeroPuertas = numeroPuertas;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.tieneCamaraReversa = tieneCamaraReversa;
        this.numeroBolsasAire = numeroBolsasAire;
        this.tieneABS = tieneABS;
        this.esCuatroPorCuatro = esCuatroPorCuatro;
        this.capacidadCajaCarga = capacidadCajaCarga;
        this.esEnchufable = true;
    }

    public PickUp(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, int numeroPasajeros, int numeroPuertas, boolean tieneAireAcondicionado, boolean tieneCamaraReversa, int numeroBolsasAire, boolean tieneABS, boolean esCuatroPorCuatro, double capacidadCajaCarga, boolean esLigero) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.numeroPasajeros = numeroPasajeros;
        this.numeroPuertas = numeroPuertas;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.tieneCamaraReversa = tieneCamaraReversa;
        this.numeroBolsasAire = numeroBolsasAire;
        this.tieneABS = tieneABS;
        this.esCuatroPorCuatro = esCuatroPorCuatro;
        this.capacidadCajaCarga = capacidadCajaCarga;
        this.esEnchufable = false;
        this.esLigero = esLigero;
    }
}
