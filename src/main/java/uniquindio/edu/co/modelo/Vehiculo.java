package uniquindio.edu.co.modelo;

public abstract class Vehiculo {
    private String placa;
    private String marca;
    private boolean esNuevo;
    private String modelo;
    private int cambios;
    private double velocidadMaxima;
    private double cilindraje;
    private String tipoCombustible;
    private boolean transmisionAutomatica;
    private boolean revisionTecnomecanica;
    private boolean disponible;

    public Vehiculo(String placa, String marca, boolean esNuevo, String modelo, int cambios, double velocidadMaxima, double cilindraje, String tipoCombustible, boolean transmisionAutomatica, boolean revisionTecnomecanica) {
        this.placa = placa;
        this.marca = marca;
        this.esNuevo = esNuevo;
        this.modelo = modelo;
        this.cambios = cambios;
        this.velocidadMaxima = velocidadMaxima;
        this.cilindraje = cilindraje;
        this.tipoCombustible = tipoCombustible;
        this.transmisionAutomatica = transmisionAutomatica;
        this.revisionTecnomecanica = revisionTecnomecanica;
        this.disponible = true;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean isEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(boolean esNuevo) {
        this.esNuevo = esNuevo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCambios() {
        return cambios;
    }

    public void setCambios(int cambios) {
        this.cambios = cambios;
    }

    public double getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(double velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public double getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(double cilindraje) {
        this.cilindraje = cilindraje;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public boolean isTransmisionAutomatica() {
        return transmisionAutomatica;
    }

    public void setTransmisionAutomatica(boolean transmisionAutomatica) {
        this.transmisionAutomatica = transmisionAutomatica;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public boolean isRevisionTecnomecanica() {
        return revisionTecnomecanica;
    }

    public void setRevisionTecnomecanica(boolean revisionTecnomecanica) {
        this.revisionTecnomecanica = revisionTecnomecanica;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
