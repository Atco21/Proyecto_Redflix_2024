package Entidad;

/*
 * Autor: Alfred Thomas Comanescu
 */



 public class Programa extends Contenido{
 
     private int temporadas;
 
     //CONSTRUCTOR
     public Programa(String titulo, int duracion, String genero, int fechaEstreno, String desc, int temporadas){
 
         super(titulo, duracion, genero, fechaEstreno,  desc);
         this.temporadas = temporadas; 
     }
 
     //METODOS
     public int getTemporadas() {
         return temporadas;
     }
 
     public void setTemporadas(int temporadas) {
         this.temporadas = temporadas;
     }
 
 
 
     public String toString(){
 
         return  super.toString() + "\n Temporadas: " + temporadas;
 
     }
 
 
 
 }
 