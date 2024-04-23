/*
 * Autor: Alfred Thomas Comanescu
 */


public class Serie extends Contenido {

    private int temporadas;

    //CONSTRUCTOR
    public Serie(String titulo, int duracion, int temporadas, String genero){

        super(titulo, duracion, genero);
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
