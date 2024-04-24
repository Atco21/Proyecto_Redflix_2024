/**
 * Representa la contratación de un paquete con un nombre específico.
 */
public class ContratacionPaquete extends Contratacion {
    private String nombrePaquete;

    /**
     * Constructor de la clase ContratacionPaquete.
     *
     * @param codigo          Código único del paquete.
     * @param duracion        Duración en minutos del paquete.
     * @param tituloPelicula  Título del paquete.
     * @param nombrePaquete   Nombre del paquete contratado.
     */
    public ContratacionPaquete(int codigo, int duracion, String tituloPelicula, String nombrePaquete) {
        super(codigo, duracion, tituloPelicula);
        this.nombrePaquete = nombrePaquete;
    }

    /**
     * Obtiene el nombre del paquete contratado.
     *
     * @return El nombre del paquete contratado.
     */
    public String getNombrePaquete() {
        return nombrePaquete;
    }

    /**
     * Establece el nombre del paquete contratado.
     *
     * @param nombrePaquete El nuevo nombre del paquete contratado.
     */
    public void setNombrePaquete(String nombrePaquete) {
        this.nombrePaquete = nombrePaquete;
    }

    /**
     * Muestra los detalles de la contratación del paquete.
     * Incluye el nombre del paquete en los detalles.
     */
    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de Contratación de Paquete:");
        System.out.println("ID: " + codigo);
        System.out.println("Duración: " + duracion + " minutos");
        System.out.println("Título: " + tituloPelicula);
        System.out.println("Nombre del Paquete: " + nombrePaquete);
    }
}