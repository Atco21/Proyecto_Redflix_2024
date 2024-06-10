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

public class Pelicula extends Contenido {


    //CONSTRUCTOR
    public Pelicula(String titulo, int duracion, String genero, int fechaEstreno, String desc) {

        super(titulo, duracion, genero, fechaEstreno, desc);
    
    }
    
    public String toString(){
        
        return super.toString();
        
    }
 
}
