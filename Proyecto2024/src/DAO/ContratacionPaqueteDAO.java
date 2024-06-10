package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidad.ContratacionPaquete;

public class ContratacionPaqueteDAO {

    public static void contratarPaquete(ContratacionPaquete contratacionPaquete, Connection con) throws SQLException {
        String query = "INSERT INTO ContratacionPaquete (usuarioId, paqueteId, fechaContratacion) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, contratacionPaquete.getDni());
            stmt.setInt(2, contratacionPaquete.getIdPaquete());
            stmt.setDate(3, java.sql.Date.valueOf(contratacionPaquete.getFechaContratacion()));
            stmt.executeUpdate();
        }
    }

    public void cargarArrayContrataciones (ArrayList<ContratacionPaquete> contrataLista, Connection con) throws SQLException {
        ArrayList<ContratacionPaquete> contratacionLista = new ArrayList<>();
        String query = "SELECT * FROM ContratacionPaquete";
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ContratacionPaquete contratacion = new ContratacionPaquete(
                        rs.getInt("id"),
                        rs.getString("dni"),
                        rs.getString("numCuenta"),
                        rs.getInt("idPaquete"),
                        rs.getString("fechaContratacion"),
                        rs.getDouble("precio")
                );
                contratacionLista.add(contratacion);
            }
        }
    
    }

    public static void eliminarContratacion(int id, Connection con) throws SQLException {
        String query = "DELETE FROM ContratacionPaquete WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public static void modificarContratacion(ContratacionPaquete contratacion, Connection con) throws SQLException {
        String query = "UPDATE ContratacionPaquete SET dni = ?, paqueteId = ?, fechaContratacion = ? WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, contratacion.getDni());
            stmt.setInt(2, contratacion.getId());
            stmt.setDate(3, java.sql.Date.valueOf(contratacion.getFechaContratacion()));
            stmt.setInt(4, contratacion.getId());
            stmt.executeUpdate();
        }
    }
}
