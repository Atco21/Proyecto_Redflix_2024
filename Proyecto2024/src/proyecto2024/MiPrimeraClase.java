/**
 * Crea una clase de ejemplo para un proyecto y un programa de prueba que la utilice.
 * Contiene clases para representar películas y tipos específicos de contratación.
 * 
 * @author Miriam Guisado Pérez
 * @date 20/12/2023
 */

import java.util.*;

/**
 * Representa una película con un código, duración y título.
 */
class Pelicula {

    /**
     * Código único de la película.
     */
    public int codigo;

    /**
     * Duración en minutos de la película.
     */
    public int duracion;

    /**
     * Título de la película.
     */
    public String tituloPelicula;

    /**
     * Constructor de la clase Pelicula.
     * 
     * @param codigo Código único de la película.
     * @param duracion Duración en minutos de la película.
     * @param tituloPelicula Título de la película.
     */
    public Pelicula(int codigo, int duracion, String tituloPelicula) {
        this.codigo = codigo;
        this.duracion = duracion;
        this.tituloPelicula = tituloPelicula;
    }

    /**
     * Obtiene el código de la película.
     * 
     * @return El código único de la película.
     */
    public int getCodigo() {
        return this.codigo;
    }

    /**
     * Obtiene la duración de la película en minutos.
     * 
     * @return La duración de la película en minutos.
     */
    public int getDuracion() {
        return this.duracion;
    }

    /**
     * Obtiene el título de la película.
     * 
     * @return El título de la película.
     */
    public String getTituloPelicula() {
        return this.tituloPelicula;
    }

    /**
     * Muestra los detalles de la película en la consola.
     */
    public void mostrarDetalles() {
        System.out.println("ID: " + this.codigo);
        System.out.println("Duración: " + this.duracion + " minutos");
        System.out.println("Título: " + this.tituloPelicula);
    }
}

/**
 * Representa la contratación de una película con un precio asociado.
 */
class ContratacionPelicula extends Pelicula {

    /**
     * Precio de la película contratada.
     */
    private double precioPelicula;

    /**
     * Constructor de la clase ContratacionPelicula.
     * 
     * @param codigo Código único de la película.
     * @param duracion Duración en minutos de la película.
     * @param tituloPelicula Título de la película.
     * @param precioPelicula Precio de la película contratada.
     */
    public ContratacionPelicula(int codigo, int duracion, String tituloPelicula, double precioPelicula) {
        super(codigo, duracion, tituloPelicula);
        this.precioPelicula = precioPelicula;
    }

    /**
     * Obtiene el precio de la película contratada.
     * 
     * @return El precio de la película contratada.
     */
    public double getPrecioPelicula() {
        return this.precioPelicula;
    }

    /**
     * Muestra los detalles de la contratación de la película en la consola.
     */
    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de Contratación de Película:");
        System.out.println("ID: " + this.codigo);
        System.out.println("Duración: " + this.duracion + " minutos");
        System.out.println("Título: " + this.tituloPelicula);
        System.out.println("Precio de la Película: " + this.precioPelicula + " euros");
    }
}

/**
 * Representa la contratación de una película como parte de un paquete con un nombre específico.
 */
class ContratacionPaquete extends Pelicula {

    /**
     * Nombre del paquete de contratación.
     */
    private String nombrePaquete;

    /**
     * Constructor de la clase ContratacionPaquete.
     * 
     * @param codigo Código único de la película.
     * @param duracion Duración en minutos de la película.
     * @param tituloPelicula Título de la película.
     * @param nombrePaquete Nombre del paquete de contratación.
     */
    public ContratacionPaquete(int codigo, int duracion, String tituloPelicula, String nombrePaquete) {
        super(codigo, duracion, tituloPelicula);
        this.nombrePaquete = nombrePaquete;
    }

    /**
     * Obtiene el nombre del paquete de contratación.
     * 
     * @return El nombre del paquete de contratación.
     */
    public String getNombrePaquete() {
        return nombrePaquete;
    }

    /**
     * Muestra los detalles de la contratación del paquete en la consola.
     */
    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de Contratación de Paquete:");
        System.out.println("ID: " + this.codigo);
        System.out.println("Duración: " + this.duracion + " minutos");
        System.out.println("Título: " + this.tituloPelicula);
        System.out.println("Nombre del Paquete: " + this.nombrePaquete);
    }
}

/**
 * Clase de prueba que utiliza las clases de contratación de películas.
 */
public class MiPrimeraClase {

    /**
     * Método principal que prueba la funcionalidad de las clases de contratación.
     * 
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Contratación de película
        ContratacionPelicula contratacionPelicula1 = new ContratacionPelicula(1, 130, "Shrek", 50.5);

        // Contratación de paquete
        ContratacionPaquete contratacionPaquete1 = new ContratacionPaquete(2, 30, "Shrek", "Paquete Premium");

        // Mostrar detalles de las contrataciones
        contratacionPelicula1.mostrarDetalles();
        System.out.println(); // Salto de línea
        contratacionPaquete1.mostrarDetalles();
    }
}
