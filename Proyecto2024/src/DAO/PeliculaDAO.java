/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Alfred Thomas Comanescu
 */
import Entidad.Pelicula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PeliculaDAO {
   

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

    public static void cargarPelicula(Pelicula pelicula, Connection con) throws SQLException {
        
        String sql = "INSERT INTO peliculas (titulo, duracion, genero, fechaEstreno, descripcion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, pelicula.getTitulo());
            stmt.setInt(2, pelicula.getDuracion());
            stmt.setString(3, pelicula.getGenero());
            stmt.setInt(4, pelicula.getFechaEstreno());
            stmt.setString(5, pelicula.getDesc());
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