package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class PaquetesDAO {

    static Scanner tec = new Scanner(System.in);



        public static void insertPaquetes(Connection con) {
            String sql = "INSERT INTO Paquetes (nombre, duracion, precio, descripcion) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {

                // Datos
                Object[][] paquetes = {
                    {"Estándar", "14", 3.99, "Paquete básico de dos semanas"},
                    {"Premium", "90", 9.99, "Paquete premium de tres meses"},
                    {"Familiar", "183", 18.99, "Paquete familiar de seis meses"},
                    {"Plus", "365", 40.99, "Paquete plus de un año"},
                    {"Ultra", "365", 49.99, "Paquete ultra de un año"}
                };

                for (Object[] paquete : paquetes) {
                    pstmt.setString(1, (String) paquete[0]);
                    pstmt.setString(2, (String) paquete[1]);
                    pstmt.setDouble(3, (Double) paquete[2]);
                    pstmt.setString(4, (String) paquete[3]);
                    pstmt.addBatch();
                }

                pstmt.executeBatch();
                System.out.println("Datos insertados en la tabla Paquetes.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static void mostrarPaquetes(Connection con) throws SQLException{

        
        String query = "SELECT * FROM paquetes";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigo");
            String nombre = resultSet.getString("nombre");
            String duracion = resultSet.getString("duracion");
            double precio = resultSet.getDouble("precio");
            String descripcion = resultSet.getString("descripcion");

            System.out.println("Codigo: " + codigo + " Nombre: " + nombre + " Duracion: " +duracion + " Precio: " + precio + " descripcion: " + descripcion);
        }


    }

    public  void agregarContenidoPaquete(Connection con) throws SQLException {
        System.out.print("Ingrese el ID del paquete: ");
        int idPaquete = tec.nextInt();
        tec.nextLine(); // Consumir la nueva línea pendiente
    
        System.out.print("Ingrese el ID del contenido a agregar: ");
        int idContenido = tec.nextInt();
        tec.nextLine(); // Consumir la nueva línea pendiente
    
        String updateQuery = "INSERT INTO paquete_contenido (idPaquete, idContenido) VALUES (?, ?)";
        try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
            updateStmt.setInt(1, idPaquete);
            updateStmt.setInt(2, idContenido);
    
            int rowsAffected = updateStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Contenido agregado al paquete exitosamente.");
            } else {
                System.out.println("Error al agregar el contenido al paquete.");
            }
        }
    }
    
    public  void eliminarContenidoPaquete(Connection con) throws SQLException {
        System.out.print("Ingrese el ID del paquete: ");
        int idPaquete = tec.nextInt();
        tec.nextLine(); // Consumir la nueva línea pendiente
    
        System.out.print("Ingrese el ID del contenido a eliminar: ");
        int idContenido = tec.nextInt();
        tec.nextLine(); // Consumir la nueva línea pendiente
    
        String deleteQuery = "DELETE FROM paquete_contenido WHERE idPaquete = ? AND idContenido = ?";
        try (PreparedStatement deleteStmt = con.prepareStatement(deleteQuery)) {
            deleteStmt.setInt(1, idPaquete);
            deleteStmt.setInt(2, idContenido);
    
            int rowsAffected = deleteStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Contenido eliminado del paquete exitosamente.");
            } else {
                System.out.println("Error al eliminar el contenido del paquete.");
            }
        }
    }
    

}
