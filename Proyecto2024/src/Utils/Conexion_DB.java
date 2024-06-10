package Utils;

/**
 *
 * @author Alfred Thomas Comanescu
 */

 import java.sql.Connection;
 import java.sql.SQLException;
 
 
 public class Conexion_DB {
     Connection con=null;
     public Connection AbrirConexion() throws Exception{
         // instacia una conexión
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Cargar el driver
            String urlOdbc = "jdbc:mysql://localhost:3306/Redflix";
            con=(java.sql.DriverManager.getConnection(urlOdbc,"root","root"));  //crea conexión
            return con;
          } catch(Exception e){//SQLException y ClassNotFoundException
             e.printStackTrace();
             throw new Exception("Ha sido imposible establecer la conexion"+e.getMessage());
          }          
     }
     
        
     public  void CerrarConexion(Connection con) throws Exception{
         
         try {
              if (con!= null) con.close();
         } catch (SQLException e) {
             e.printStackTrace();
             throw new Exception("Ha sido imposible cerrar la conexion"+e.getMessage());
         }    
         }    
     
 
 }
 