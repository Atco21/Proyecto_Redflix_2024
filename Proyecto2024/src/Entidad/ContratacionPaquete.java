package Entidad;




public class ContratacionPaquete {
    private int id;
    private String dni;
    private String numCuenta;
    private int idPaquete;
    private String fechaContratacion;
    private double precio;

    // Constructor
    public ContratacionPaquete(int id, String dni, String numCuenta, int idPaquete, String fechaContratacion, double precio) {
        this.id = id;
        this.dni = dni;
        this.numCuenta = numCuenta;
        this.idPaquete = idPaquete;
        this.fechaContratacion = fechaContratacion;
        this.precio = precio;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
