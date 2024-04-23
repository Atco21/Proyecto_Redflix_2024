/*
 * Autor: Alfred Thomas Comanescu
 */

public class Contenido {
    
    private String titulo;
    private int duracion; 
    private String genero;


    //CONSTRUCTOR
    public Contenido (String titulo, int duracion, String genero) {

        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
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
    
}
