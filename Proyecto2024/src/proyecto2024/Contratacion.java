/**
 * Esta clase representa una abstracción general de una contratación, que puede ser una película o un paquete.
 * Define los atributos comunes y métodos abstractos para mostrar detalles de la contratación.
 *
 * @autor Miriam Guisado Pérez
 * @fecha 25/02/24
 */
public abstract class Contratacion {
    protected int codigo;
    protected int duracion;
    protected String tituloPelicula;

    /**
     * Constructor de la clase Contratacion.
     *
     * @param codigo          Código único de la contratación.
     * @param duracion        Duración en minutos de la contratación.
     * @param tituloPelicula  Título de la película o paquete contratado.
     */
    public Contratacion(int codigo, int duracion, String tituloPelicula) {
        this.codigo = codigo;
        this.duracion = duracion;
        this.tituloPelicula = tituloPelicula;
    }

    /**
     * Obtiene el código de la contratación.
     *
     * @return El código único de la contratación.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Establece el código de la contratación.
     *
     * @param codigo El nuevo código único de la contratación.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene la duración de la contratación en minutos.
     *
     * @return La duración de la contratación en minutos.
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Establece la duración de la contratación en minutos.
     *
     * @param duracion La nueva duración de la contratación en minutos.
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * Obtiene el título de la película o paquete contratado.
     *
     * @return El título de la película o paquete contratado.
     */
    public String getTituloPelicula() {
        return tituloPelicula;
    }

    /**
     * Establece el título de la película o paquete contratado.
     *
     * @param tituloPelicula El nuevo título de la película o paquete contratado.
     */
    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    /**
     * Método abstracto para mostrar detalles específicos de la contratación.
     * Debe ser implementado por las subclases.
     */
    public abstract void mostrarDetalles();
}