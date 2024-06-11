/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;

import Entidad.Contenido;

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

    public void eliminarPelicula(String titulo, Connection con) throws SQLException {

        String sql = "DELETE FROM peliculas WHERE title = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            stmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void buscarPelicula(String titulo, Connection con) throws SQLException{

        String sql = "SELECT FROM peliculas where titulo = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1,titulo );
            stmt.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
            
    }
    
}
