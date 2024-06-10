package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import Entidad.Usuario;


/**
 * 
 * @author Alfred Thomas Comanescu
 * 
 */

 public class UsuarioDAO {

    static Scanner tec = new Scanner(System.in);
   
    public boolean registrarUsuario(Usuario u,Connection con) throws SQLException {
        boolean res = false;

        
        String referidoPor = tec.nextLine();

        String sql = "INSERT INTO usuarios (dni, nombre, direccion, correo, contrasenia, referido_por) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, u.getDni());
            stmt.setString(2, u.getNombre());
            stmt.setString(3, u.getApellidos());
            stmt.setString(4, u.getCorreo());
            stmt.setString(5, u.getContrasenia());
            stmt.setString(6, referidoPor.isEmpty() ? null : referidoPor);

            stmt.executeUpdate();
            System.out.println("Usuario registrado exitosamente.");
            res = true;
            
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return res;
    }

    public void contratarPaquete(String dni, String numCuenta, int idPaquete, Connection con) throws SQLException {

    
        String sqlCliente = "INSERT INTO clientes (dni, num_cuenta, estado) VALUES (?, ?, 'activo')";
        String sqlClientePaquete = "INSERT INTO cliente_paquete (dni, id_paquete, fechaExpiracion) VALUES (?, ?, ?)";
        String sqlValidezPaquete = "SELECT validez FROM paquetes WHERE id_paquete=?";

        try (PreparedStatement stmtCliente = con.prepareStatement(sqlCliente);
             PreparedStatement stmtClientePaquete = con.prepareStatement(sqlClientePaquete); 
             PreparedStatement stmtValidezPaquete = con.prepareStatement(sqlValidezPaquete)) {

                ResultSet rs = stmtValidezPaquete.executeQuery();

                if (rs.next()) {
                    int validezDias = rs.getInt("validez");
    
                    
                    LocalDate fechaContratacion = LocalDate.now();
                    LocalDate fechaExpiracion = fechaContratacion.plusDays(validezDias);
    
                
                    LocalDate fechaHoy = LocalDate.now();
                    int diasRestantes = (int) fechaExpiracion.until(fechaHoy).getDays();
    
                 
                    String diasRestantesString = String.valueOf(diasRestantes);
    
                  
                    stmtClientePaquete.setString(1, dni);
                    stmtClientePaquete.setInt(2, idPaquete);
                    stmtClientePaquete.setString(3, diasRestantesString);
                    stmtClientePaquete.executeUpdate();

                    System.out.println("Paquetes contratados exitosamente.");
                } else {
                    System.out.println("No se pudo contratar el paquete.");
                }
        
          

        } catch (SQLException e) {
            e.printStackTrace();

        }

    
    }

    public boolean inicioSesion(Connection con) throws SQLException {

        Boolean res = false; 

        System.out.print("Ingrese su correo: ");
        String correo = tec.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasenia = tec.nextLine();

        String sql = "SELECT correo, contrasenia FROM usuarios WHERE correo = ? AND contrasenia = ?";
        ResultSet rs = null;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, correo);
            stmt.setString(2, contrasenia);

            rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Inicio de sesión exitoso.");   
                res = true; 

            } else {
                System.out.println("Correo o contraseña incorrectos.");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;

    }


    public void buscarUsuario(Connection con) throws SQLException{

        String sql = "Select * from usuarios where DNI=?";
        ResultSet rs = null;

        System.out.println("Dame el DNI del Usuario que quieres buscar: ");
        String dni = tec.nextLine();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, dni);
            rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Usuario encontrado");
                System.out.println("Nombre: " + rs.getString("nombre"));   
                System.out.println("Nombre: " + rs.getString("direccion"));
                System.out.println("Fecha de registro: " + rs.getString("fecha_alta"));
                System.out.println("Correo: " + rs.getString("correo"));

            } else {
                System.out.println("Usuario no encontrado");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void eliminarUsuario(String dni, Connection con) throws SQLException{

        PreparedStatement stmt = null;

        String sql = "DELETE FROM usuarios WHERE dni = ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, dni);

            int cambios = stmt.executeUpdate();
            if (cambios > 0) {
                System.out.println("Usuario eliminado con exito");
            } else {
                System.out.println("Usuario no encontrado");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) stmt.close();
        }
    }


}


