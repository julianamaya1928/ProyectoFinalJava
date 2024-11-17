package uniquindio.edu.co.modelo;

public class Deportivo extends Vehiculo {
    private int numeroPasajeros;
    private int numeroPuertas;
    private int numeroBolsasAire;
    private int caballosDeFuerza;
    private double tiempoCeroACien;
    private String tiempoCarga;
    private String autonomiaElectrica;
    private boolean esEnchufable;
    private boolean esLigero;

    public Deportivo(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, int numeroPasajeros, int numeroPuertas, int numeroBolsasAire, int caballosDeFuerza, double tiempoCeroACien, String tiempoCarga, String autonomiaElectrica) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.numeroPasajeros = numeroPasajeros;
        this.numeroPuertas = numeroPuertas;
        this.numeroBolsasAire = numeroBolsasAire;
        this.caballosDeFuerza = caballosDeFuerza;
        this.tiempoCeroACien = tiempoCeroACien;
        this.tiempoCarga = tiempoCarga;
        this.autonomiaElectrica = autonomiaElectrica;
    }

    public Deportivo(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, double tiempoCeroACien, int caballosDeFuerza, int numeroBolsasAire, int numeroPuertas, int numeroPasajeros) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.tiempoCeroACien = tiempoCeroACien;
        this.caballosDeFuerza = caballosDeFuerza;
        this.numeroBolsasAire = numeroBolsasAire;
        this.numeroPuertas = numeroPuertas;
        this.numeroPasajeros = numeroPasajeros;
        this.esEnchufable = true;
    }

    public Deportivo(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, int numeroPasajeros, int numeroPuertas, int numeroBolsasAire, int caballosDeFuerza, double tiempoCeroACien, boolean esLigero) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.numeroPasajeros = numeroPasajeros;
        this.numeroPuertas = numeroPuertas;
        this.numeroBolsasAire = numeroBolsasAire;
        this.caballosDeFuerza = caballosDeFuerza;
        this.tiempoCeroACien = tiempoCeroACien;
        this.esEnchufable = false;
        this.esLigero = esLigero;
    }
}

