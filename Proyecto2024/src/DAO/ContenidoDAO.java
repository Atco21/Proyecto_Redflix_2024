/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;

import Entidad.Contenido;
import Entidad.Pelicula;
import Utils.Conexion_DB;

/**
 *
 * @author Alfred
 */
public class ContenidoDAO {
        
    
    public static void printSQLException(SQLException ex) {

        ex.printStackTrace(System.err);
        System.err.println("SQLState: " + ex.getSQLState());
        System.err.println("Error Code: " + ex.getErrorCode());
        System.err.println("Message: " + ex.getMessage());
        Throwable t = ex.getCause();
        while (t != null) {
            System.out.println("Cause: " + t);
            t = t.getCause();
        }
    }

    public static void aniadirContenido(Contenido contenido, Connection con) throws SQLException {
        
        String sql = "INSERT INTO contenido (nombre, duracion, genero, fecha_estreno, descripcion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, contenido.getTitulo());
            stmt.setInt(2, contenido.getDuracion());
            stmt.setString(3, contenido.getGenero());
            stmt.setInt(4, contenido.getFechaEstreno());
            stmt.setString(5, contenido.getDesc());
            stmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void buscarConetnido(String titulo, Connection con) throws SQLException{

        String sql = "SELECT FROM peliculas where titulo = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1,titulo );
            stmt.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
            
    }


    

    public static int obtenerContenidoIDPorNombre(String nombre, Connection con) {
        String sql = "SELECT id FROM Contenido WHERE nombre = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retorna -1 si no se encuentra el contenido
    }


    public static void eliminarContenido(Contenido contenido, Connection con, String titulo) throws SQLException {
        
        String sql = "DELETE FROM Contenido WHERE titulo = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            stmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
