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


    public void eliminarPelicula(String titulo, Connection con) throws SQLException {

        String sql = "DELETE FROM peliculas WHERE titulo = ?";
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


    public static void aniadirPelicula(Pelicula pelicula, int contenidoId, Connection con) {
        String sql = "INSERT INTO Peliculas (id_contenido, nombre, duracion, genero, fechaEstreno, descripcion) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, contenidoId);
            ps.setString(2, pelicula.getTitulo());
            ps.setInt(3, pelicula.getDuracion());
            ps.setString(4, pelicula.getGenero());
            ps.setInt(5, pelicula.getFechaEstreno());
            ps.setString(6, pelicula.getDesc());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}