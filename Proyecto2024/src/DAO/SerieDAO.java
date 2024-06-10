package DAO;

import Entidad.Serie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SerieDAO {


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


    public static void eliminarSerieDB(String titulo, Connection con) throws SQLException {
        PreparedStatement stmt = null;

        String sql = "DELETE FROM series WHERE title = ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, titulo);

            int cambios = stmt.executeUpdate();
            if (cambios > 0) {
                System.out.println("Serie eliminada con éxito: " + titulo);
            } else {
                System.out.println("Serie no encontrada: " + titulo);
            }

        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            if (stmt != null) stmt.close();
        }
    }

    public static void cargarSerieDB(Serie serie, Connection con) throws SQLException {
        PreparedStatement stmt = null;

        String sql = "INSERT INTO series (nombre, fechaEstreno, estado, temporadas, descripcion) VALUES (?, ?, ?, ?, ?)";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, serie.getTitulo());
            stmt.setInt(2, serie.getFechaEstreno());
            stmt.setString(3, serie.getEstado());
            stmt.setInt(4, serie.getTemporadas());
            stmt.setString(5, serie.getDesc());

            stmt.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            if (stmt != null) stmt.close();
        }

    }

    

    

}
