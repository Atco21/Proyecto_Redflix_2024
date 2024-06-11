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

public class Serie extends Contenido{

    private int temporadas;
    private String estado;
    

    //CONSTRUCTOR
    public Serie(String titulo, int duracion, int temporadas, String genero, int fechaEstreno, String desc, String estado){

        super(titulo, duracion, genero, fechaEstreno, desc);
        this.temporadas = temporadas; 
        this.estado = estado;
        
    }

    public Serie(Contenido contenido, int temporadas, String estado) {
        super(contenido.getTitulo(), contenido.getDuracion(), contenido.getGenero(), contenido.getFechaEstreno(), contenido.getDesc());
        this.temporadas = temporadas;
        this.estado = estado;
    }
    // GETTERS Y SETTERS
    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public String getEstado(){
        return estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    @Override
    public String toString(){
        return  super.toString() + "\n Temporadas: " + temporadas;
    }

}

