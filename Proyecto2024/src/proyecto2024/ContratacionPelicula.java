/**
 * Representa la contratación de una película con un precio asociado.
 */
public class ContratacionPelicula extends Contratacion {
    private double precioPelicula;

    /**
     * Constructor de la clase ContratacionPelicula.
     *
     * @param codigo          Código único de la película.
     * @param duracion        Duración en minutos de la película.
     * @param tituloPelicula  Título de la película.
     * @param precioPelicula  Precio de la película contratada.
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
        return precioPelicula;
    }

    /**
     * Establece el precio de la película contratada.
     *
     * @param precioPelicula El nuevo precio de la película contratada.
     */
    public void setPrecioPelicula(double precioPelicula) {
        this.precioPelicula = precioPelicula;
    }

    /**
     * Muestra los detalles de la contratación de la película.
     * Incluye el precio de la película en los detalles.
     */
    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de Contratación de Película:");
        System.out.println("ID: " + codigo);
        System.out.println("Duración: " + duracion + " minutos");
        System.out.println("Título: " + tituloPelicula);
        System.out.println("Precio de la Película: " + precioPelicula);
    }

     
}
