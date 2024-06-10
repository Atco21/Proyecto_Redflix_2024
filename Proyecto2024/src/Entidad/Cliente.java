package Entidad;
/**
 * 
 * @author Alfred Thomas Comanescu
 * 
 * 
 */

public class Cliente extends Usuario {
    
    private String metodoPago;

    public Cliente(String dni, String correo, String contrasenia, String nombre, String apellidos, String alias, String direccion, String metodoPago) {
        super(dni, correo, contrasenia, nombre, apellidos, alias, direccion);
        this.metodoPago = metodoPago;
    }

    // GETTERS Y SETTERS
    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}