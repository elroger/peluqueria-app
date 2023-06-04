package com.peluqueria.model;

/**
 * Clase derivada que representa un cliente regular de la peluquería.
 */
public class ClienteRegular extends Cliente {
    private int puntos;

    /**
     * Constructor de la clase ClienteRegular.
     *
     * @param name  El nombre del cliente regular.
     * @param email El correo electrónico del cliente regular.
     * @param phone El número de teléfono del cliente regular.
     * @param puntos Los puntos acumulados por el cliente regular.
     */
    public ClienteRegular(String name, String email, String phone, int puntos) {
        super(name, email, phone);
        this.puntos = puntos;
    }

    /**
     * Obtiene los puntos acumulados por el cliente regular.
     *
     * @return Los puntos acumulados por el cliente regular.
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Establece los puntos acumulados por el cliente regular.
     *
     * @param puntos Los puntos acumulados por el cliente regular.
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
