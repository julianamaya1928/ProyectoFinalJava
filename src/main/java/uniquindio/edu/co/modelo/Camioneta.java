package uniquindio.edu.co.modelo;

public class Camioneta extends Vehiculo {
    private int numeroPasajeros;
    private int numeroPuertas;
    private double capacidadMaletero;
    private boolean tieneAireAcondicionado;
    private boolean tieneCamaraReversa;
    private boolean tieneVelocidadCrucero;
    private int numeroBolsasAire;
    private boolean tieneABS;
    private boolean esCuatroPorCuatro;
    private String tiempoCarga;
    private String autonomiaElectrica;
    private boolean esEnchufable;
    private boolean esLigero;

    public Camioneta(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, int numeroPasajeros, int numeroPuertas, double capacidadMaletero, boolean tieneAireAcondicionado, boolean tieneCamaraReversa, boolean tieneVelocidadCrucero, int numeroBolsasAire, boolean tieneABS, boolean esCuatroPorCuatro, String tiempoCarga, String autonomiaElectrica) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.numeroPasajeros = numeroPasajeros;
        this.numeroPuertas = numeroPuertas;
        this.capacidadMaletero = capacidadMaletero;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.tieneCamaraReversa = tieneCamaraReversa;
        this.tieneVelocidadCrucero = tieneVelocidadCrucero;
        this.numeroBolsasAire = numeroBolsasAire;
        this.tieneABS = tieneABS;
        this.esCuatroPorCuatro = esCuatroPorCuatro;
        this.tiempoCarga = tiempoCarga;
        this.autonomiaElectrica = autonomiaElectrica;
    }

    public Camioneta(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, int numeroPasajeros, int numeroPuertas, double capacidadMaletero, boolean tieneAireAcondicionado, boolean tieneCamaraReversa, boolean tieneVelocidadCrucero, int numeroBolsasAire, boolean tieneABS, boolean esCuatroPorCuatro) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.numeroPasajeros = numeroPasajeros;
        this.numeroPuertas = numeroPuertas;
        this.capacidadMaletero = capacidadMaletero;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.tieneCamaraReversa = tieneCamaraReversa;
        this.tieneVelocidadCrucero = tieneVelocidadCrucero;
        this.numeroBolsasAire = numeroBolsasAire;
        this.tieneABS = tieneABS;
        this.esCuatroPorCuatro = esCuatroPorCuatro;
        this.esEnchufable = true;
    }

    public Camioneta(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, int numeroPasajeros, int numeroPuertas, double capacidadMaletero, boolean tieneAireAcondicionado, boolean tieneCamaraReversa, boolean tieneVelocidadCrucero, int numeroBolsasAire, boolean tieneABS, boolean esCuatroPorCuatro, boolean esLigero) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.numeroPasajeros = numeroPasajeros;
        this.numeroPuertas = numeroPuertas;
        this.capacidadMaletero = capacidadMaletero;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.tieneCamaraReversa = tieneCamaraReversa;
        this.tieneVelocidadCrucero = tieneVelocidadCrucero;
        this.numeroBolsasAire = numeroBolsasAire;
        this.tieneABS = tieneABS;
        this.esCuatroPorCuatro = esCuatroPorCuatro;
        this.esEnchufable = false;
        this.esLigero = esLigero;
    }
}

