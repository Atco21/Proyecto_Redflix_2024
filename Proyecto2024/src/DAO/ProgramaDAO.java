package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Entidad.Programa;

public class ProgramaDAO {

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
    
    public static void cargar(Programa programa, Connection con) throws SQLException {
        
        String sql = "INSERT INTO Programa (titulo, duracion, genero, fechaEstreno, desc) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, programa.getTitulo());
            stmt.setInt(2, programa.getDuracion());
            stmt.setString(3, programa.getGenero());
            stmt.setInt(4, programa.getFechaEstreno());
            stmt.setString(5, programa.getDesc());
            stmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }



    

}
