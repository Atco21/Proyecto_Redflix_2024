/**
 * Clase principal que prueba la funcionalidad de las clases de contratación.
 * Utiliza un ArrayList para almacenar objetos de contratación (películas o paquetes).
 */

import java.util.ArrayList;
public class Entrega2Proyecto {
    public static void main(String[] args) {
       
        // Crear un ArrayList para almacenar objetos de contratación (películas o paquetes)
        ArrayList<Contratacion> contrataciones = new ArrayList<>();

        // Crear una contratación de película y agregarla al ArrayList
        ContratacionPelicula contratacionPelicula1 = new ContratacionPelicula(1, 130, "Shrek", 50.5);
       

        // Crear una contratación de paquete y agregarla al ArrayList
        ContratacionPaquete contratacionPaquete1 = new ContratacionPaquete(2, 30, "Shrek", "Paquete Premium");
        

        // Mostrar detalles de todas las contrataciones almacenadas en el ArrayList
        for (Contratacion contratacion : contrataciones) {
            contratacion.mostrarDetalles();
            System.out.println(); // Salto de línea entre cada detalle de contratación
        }
    }
}

