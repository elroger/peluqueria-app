package com.peluqueria.model;

/**
 * Clase que representa una cita en la peluquería.
 */
public class Cita {
    private String name;
    private String email;
    private String phone;
    private String date;
    private String time;
    private String service;
    private String hairdresser;

    /**
     * Constructor de la clase Cita.
     *
     * @param name        El nombre de la persona que realiza la cita.
     * @param email       El correo electrónico de la persona que realiza la cita.
     * @param phone       El número de teléfono de la persona que realiza la cita.
     * @param date        La fecha de la cita.
     * @param time        La hora de la cita.
     * @param service     El servicio solicitado.
     * @param hairdresser El nombre del peluquero asignado.
     */
    public Cita(String name, String email, String phone, String date, String time, String service, String hairdresser) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.service = service;
        this.hairdresser = hairdresser;
    }

    /**
     * Obtiene el nombre de la cita.
     *
     * @return El nombre de la cita.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la cita.
     *
     * @param name El nombre de la cita.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el correo electrónico de la cita.
     *
     * @return El correo electrónico de la cita.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico de la cita.
     *
     * @param email El correo electrónico de la cita.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el número de teléfono de la cita.
     *
     * @return El número de teléfono de la cita.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Establece el número de teléfono de la cita.
     *
     * @param phone El número de teléfono de la cita.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Obtiene la fecha de la cita.
     *
     * @return La fecha de la cita.
     */
    public String getDate() {
        return date;
    }

    /**
     * Establece la fecha de la cita.
     *
     * @param date La fecha de la cita.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Obtiene la hora de la cita.
     *
     * @return La hora de la cita.
     */
    public String getTime() {
        return time;
    }

    /**
     * Establece la hora de la cita.
     *
     * @param time La hora de la cita.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Obtiene el servicio solicitado en la cita.
     *
     * @return El servicio solicitado.
     */
    public String getService() {
        return service;
    }

    /**
     * Establece el servicio solicitado en la cita.
     *
     * @param service El servicio solicitado.
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * Obtiene el nombre del peluquero asignado a la cita.
     *
     * @return El nombre del peluquero asignado.
     */
    public String getHairdresser() {
        return hairdresser;
    }

    /**
     * Establece el nombre del peluquero asignado a la cita.
     *
     * @param hairdresser El nombre del peluquero asignado.
     */
    public void setHairdresser(String hairdresser) {
        this.hairdresser = hairdresser;
    }
}
