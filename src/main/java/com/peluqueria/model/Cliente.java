package com.peluqueria.model;

/**
 * Clase base que representa un cliente de la peluquería.
 */
public class Cliente {
    private String name;
    private String email;
    private String phone;

    /**
     * Constructor de la clase Cliente.
     *
     * @param name  El nombre del cliente.
     * @param email El correo electrónico del cliente.
     * @param phone El número de teléfono del cliente.
     */
    public Cliente(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param name El nombre del cliente.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     *
     * @return El correo electrónico del cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del cliente.
     *
     * @param email El correo electrónico del cliente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el número de teléfono del cliente.
     *
     * @return El número de teléfono del cliente.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Establece el número de teléfono del cliente.
     *
     * @param phone El número de teléfono del cliente.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}

