package uniquindio.edu.co.modelo;

public class Sedan extends Vehiculo {
    private int numeroPasajeros;
    private int numeroPuertas;
    private double capacidadMaletero;
    private boolean tieneAireAcondicionado;
    private boolean tieneCamaraReversa;
    private boolean tieneVelocidadCrucero;
    private int numeroBolsasAire;
    private boolean tieneABS;
    private boolean tieneSensoresColision;
    private boolean tieneSensorTraficoCruzado;
    private boolean tieneAsistenteCarril;
    private String tiempoCarga;
    private String autonomiaElectrica;
    private boolean esEnchufable;
    private boolean esLigero;

    public Sedan(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, boolean tieneAsistenteCarril, boolean tieneSensorTraficoCruzado, boolean tieneSensoresColision, boolean tieneABS, int numeroBolsasAire, boolean tieneVelocidadCrucero, boolean tieneCamaraReversa, boolean tieneAireAcondicionado, double capacidadMaletero, int numeroPuertas, int numeroPasajeros, String autonomiaElectrica, String tiempoCarga) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.autonomiaElectrica = autonomiaElectrica;
        this.tiempoCarga = tiempoCarga;
        this.tieneAsistenteCarril = tieneAsistenteCarril;
        this.tieneSensorTraficoCruzado = tieneSensorTraficoCruzado;
        this.tieneSensoresColision = tieneSensoresColision;
        this.tieneABS = tieneABS;
        this.numeroBolsasAire = numeroBolsasAire;
        this.tieneVelocidadCrucero = tieneVelocidadCrucero;
        this.tieneCamaraReversa = tieneCamaraReversa;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.capacidadMaletero = capacidadMaletero;
        this.numeroPuertas = numeroPuertas;
        this.numeroPasajeros = numeroPasajeros;
    }

    public Sedan(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, int numeroPasajeros, int numeroPuertas, double capacidadMaletero, boolean tieneAireAcondicionado, boolean tieneCamaraReversa, boolean tieneVelocidadCrucero, int numeroBolsasAire, boolean tieneABS, boolean tieneSensoresColision, boolean tieneSensorTraficoCruzado, boolean tieneAsistenteCarril) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.numeroPasajeros = numeroPasajeros;
        this.numeroPuertas = numeroPuertas;
        this.capacidadMaletero = capacidadMaletero;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.tieneCamaraReversa = tieneCamaraReversa;
        this.tieneVelocidadCrucero = tieneVelocidadCrucero;
        this.numeroBolsasAire = numeroBolsasAire;
        this.tieneABS = tieneABS;
        this.tieneSensoresColision = tieneSensoresColision;
        this.tieneSensorTraficoCruzado = tieneSensorTraficoCruzado;
        this.tieneAsistenteCarril = tieneAsistenteCarril;
        this.esEnchufable = true;
    }

    public Sedan(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, int numeroPasajeros, int numeroPuertas, double capacidadMaletero, boolean tieneAireAcondicionado, boolean tieneCamaraReversa, boolean tieneVelocidadCrucero, int numeroBolsasAire, boolean tieneABS, boolean tieneSensoresColision, boolean tieneSensorTraficoCruzado, boolean tieneAsistenteCarril, boolean esLigero) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.numeroPasajeros = numeroPasajeros;
        this.numeroPuertas = numeroPuertas;
        this.capacidadMaletero = capacidadMaletero;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.tieneCamaraReversa = tieneCamaraReversa;
        this.tieneVelocidadCrucero = tieneVelocidadCrucero;
        this.numeroBolsasAire = numeroBolsasAire;
        this.tieneABS = tieneABS;
        this.tieneSensoresColision = tieneSensoresColision;
        this.tieneSensorTraficoCruzado = tieneSensorTraficoCruzado;
        this.tieneAsistenteCarril = tieneAsistenteCarril;
        this.esEnchufable = false;
        this.esLigero = esLigero;
    }

    public int getNumeroPasajeros() {
        return numeroPasajeros;
    }

    public void setNumeroPasajeros(int numeroPasajeros) {
        this.numeroPasajeros = numeroPasajeros;
    }

    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }

    public double getCapacidadMaletero() {
        return capacidadMaletero;
    }

    public void setCapacidadMaletero(double capacidadMaletero) {
        this.capacidadMaletero = capacidadMaletero;
    }

    public boolean isTieneAireAcondicionado() {
        return tieneAireAcondicionado;
    }

    public void setTieneAireAcondicionado(boolean tieneAireAcondicionado) {
        this.tieneAireAcondicionado = tieneAireAcondicionado;
    }

    public boolean isTieneCamaraReversa() {
        return tieneCamaraReversa;
    }

    public void setTieneCamaraReversa(boolean tieneCamaraReversa) {
        this.tieneCamaraReversa = tieneCamaraReversa;
    }

    public boolean isTieneVelocidadCrucero() {
        return tieneVelocidadCrucero;
    }

    public void setTieneVelocidadCrucero(boolean tieneVelocidadCrucero) {
        this.tieneVelocidadCrucero = tieneVelocidadCrucero;
    }

    public int getNumeroBolsasAire() {
        return numeroBolsasAire;
    }

    public void setNumeroBolsasAire(int numeroBolsasAire) {
        this.numeroBolsasAire = numeroBolsasAire;
    }

    public boolean isTieneABS() {
        return tieneABS;
    }

    public void setTieneABS(boolean tieneABS) {
        this.tieneABS = tieneABS;
    }

    public boolean isTieneSensoresColision() {
        return tieneSensoresColision;
    }

    public void setTieneSensoresColision(boolean tieneSensoresColision) {
        this.tieneSensoresColision = tieneSensoresColision;
    }

    public boolean isTieneSensorTraficoCruzado() {
        return tieneSensorTraficoCruzado;
    }

    public void setTieneSensorTraficoCruzado(boolean tieneSensorTraficoCruzado) {
        this.tieneSensorTraficoCruzado = tieneSensorTraficoCruzado;
    }

    public boolean isTieneAsistenteCarril() {
        return tieneAsistenteCarril;
    }

    public void setTieneAsistenteCarril(boolean tieneAsistenteCarril) {
        this.tieneAsistenteCarril = tieneAsistenteCarril;
    }
}


