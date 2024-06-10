package Entidad;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author miriam
 */
// Clase para representar la contratación
public class Contratacion {
    protected int codigo;
    protected String duracion; // Representa la duración del paquete en meses y semanas

    // Constructor de la clase Contrataciones
    public Contratacion(int codigo, String duracion) {
        this.codigo = codigo;
        this.duracion = duracion;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    // Método para mostrar detalles de la contratación
    public void mostrarDetalles() {
        System.out.println("ID: " + codigo);
        System.out.println("Duración: " + duracion);
    }

    // Método toString
    @Override
    public String toString() {
        return "Contratacion{" +
                "codigo=" + codigo +
                ", duracion='" + duracion + '\'' +
                '}';
    }
}