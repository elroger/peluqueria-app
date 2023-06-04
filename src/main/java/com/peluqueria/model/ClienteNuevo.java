package com.peluqueria.model;

/**
 * Clase derivada que representa un cliente nuevo de la peluquería.
 */
public class ClienteNuevo extends Cliente {
    private String referencia;

    /**
     * Constructor de la clase ClienteNuevo.
     *
     * @param name       El nombre del cliente nuevo.
     * @param email      El correo electrónico del cliente nuevo.
     * @param phone      El número de teléfono del cliente nuevo.
     * @param referencia La referencia que indica cómo llegó el cliente nuevo.
     */
    public ClienteNuevo(String name, String email, String phone, String referencia) {
        super(name, email, phone);
        this.referencia = referencia;
    }

    /**
     * Obtiene la referencia del cliente nuevo.
     *
     * @return La referencia del cliente nuevo.
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Establece la referencia del cliente nuevo.
     *
     * @param referencia La referencia del cliente nuevo.
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
