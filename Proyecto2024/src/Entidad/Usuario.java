package Entidad;

/**
 * 
 * @author Alfred Thomas Comanescu
 * 
 * 
 */

public class Usuario {
    private String correo;
    private String contrasenia;
    private String dni;
    private String nombre;
    private String apellidos;
    private String alias;
    private String direccion;
    private String referido_por;


   

    public Usuario(String dni, String correo, String contrasenia, String nombre, String apellidos, String alias, String direccion) {
        this.dni = dni; 
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.nombre = nombre; 
        this.apellidos = apellidos; 
        this.alias = alias;
    }

    public Usuario(String dni, String correo, String contrasenia, String nombre, String apellidos, String alias, String direccion, String referido_por) {
        this.dni = dni; 
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.nombre = nombre; 
        this.apellidos = apellidos; 
        this.alias = alias;
        this.referido_por = referido_por;
    }

    // GETTERS Y SETTERS
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReferido_por() {
        return referido_por;
    }

    public void setReferido_por(String referido_por) {
        this.referido_por = referido_por;
    }
}