/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

/**
 * 
 * @author Alfred Thomas Comanescu
 * 
 * 
 */
import java.io.Serializable;

public class Contenido implements Serializable{

    private String titulo;
    private int duracion; 
    private String genero;
    private int fechaEstreno;
    private String desc;


    

    //CONSTRUCTOR
    public Contenido (String titulo, int duracion, String genero, int fechaEstreno, String desc) {

        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.fechaEstreno = fechaEstreno;
        this.desc = desc;

    }

    //METODOS
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero){
        this.genero=genero;
    }

    public String toString(){
        return "Título: " + titulo + "\n Duración: "+duracion + " minutos \n Género: "+genero;
    }

    public int getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(int fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    
}
