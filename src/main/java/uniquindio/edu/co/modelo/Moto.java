package uniquindio.edu.co.modelo;

public class Moto extends Vehiculo {
    private String tiempoCarga;
    private String autonomiaElectrica;
    private boolean esEnchufable;
    private boolean esLigero;

    public Moto(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, String tiempoCarga, String autonomiaElectrica) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.autonomiaElectrica = autonomiaElectrica;
        this.tiempoCarga = tiempoCarga;
    }

    public Moto(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.esEnchufable = true;
    }

    public Moto(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica, boolean esLigero) {
        super(placa, marca, esNuevo, modelo, cambios, velocidadMaxima, cilindraje, tipoCombustible, transmisionAutomatica, revisionTecnomecanica);
        this.esEnchufable = false;
        this.esLigero = esLigero;
    }

    public boolean isEsLigero() {
        return esLigero;
    }

    public void setEsLigero(boolean esLigero) {
        this.esLigero = esLigero;
    }

    public boolean isEsEnchufable() {
        return esEnchufable;
    }

    public void setEsEnchufable(boolean esEnchufable) {
        this.esEnchufable = esEnchufable;
    }

    public String getAutonomiaElectrica() {
        return autonomiaElectrica;
    }

    public void setAutonomiaElectrica(String autonomiaElectrica) {
        this.autonomiaElectrica = autonomiaElectrica;
    }

    public String getTiempoCarga() {
        return tiempoCarga;
    }

    public void setTiempoCarga(String tiempoCarga) {
        this.tiempoCarga = tiempoCarga;
    }
}


