


import java.io.*;
import java.sql.*;
import java.util.*;

import Entidad.Contenido;
import Entidad.ContratacionPaquete;
import Entidad.Pelicula;
import Entidad.Programa;
import Entidad.Serie;
import Entidad.Usuario;
import Utils.Conexion_DB;
import DAO.UsuarioDAO;
import DAO.SerieDAO;
import DAO.ContenidoDAO;
import DAO.PaquetesDAO;
import DAO.PeliculaDAO;
import DAO.ProgramaDAO; 




/**
 * @author Alfred 
 * @author Miriam 
 * 
 * 
 */
public class Redflix {

   static Scanner tec = new Scanner(System.in);

    public static void main(String[] args){


        ArrayList<Pelicula> peliculaLista = new ArrayList<>();
        ArrayList<Serie> serieLista = new ArrayList<>();
        ArrayList<Programa> programasTVLista = new ArrayList<>();
        ArrayList<Contenido> contenidoLista = new ArrayList<>();
            
        // Conexión a la base de datos
        Conexion_DB _conexion_DB = new Conexion_DB();
        try {
           Connection con = _conexion_DB.AbrirConexion(); // Asignación de la conexión
        
        
            cargarArrayPeliculas(peliculaLista);
            cargarArraySeries(serieLista);
            cargarArrayProgramasTV(programasTVLista);
            boolean check = true;
            boolean finish = true;
            
            int sel = menuTipoUsuario();
            do{
            switch (sel) {
                case 1:
                    System.out.println();
                    System.out.println("************* HOLA ADMIN *************");
                    System.out.println("¿Que deseas hacer? ");

                    
                        menuAdmin();
                        byte opc = tec.nextByte();
                        tec.nextLine(); 

                        switch (opc) {
                            case 1:
                                menuContenidoAdmin( peliculaLista, serieLista, programasTVLista, con, contenidoLista);
                                break;
                            case 2:
                                menuUsuarioAdmin(con);
                                break;

                            case 3:
                                menuPaquetesAdmin(con);
                                break;
                            case 0: 
                                _conexion_DB.CerrarConexion(con);
                                finish = false;
                                break;
                            default:

                                System.out.println("Opcion invalida. intentenlo de nuevo");
                                break;
                        }
                        
                    
                    break;

                case 2:

                    System.out.println();
                    menuUsuario();
                    int opc2 = tec.nextInt();
                    tec.nextLine(); // Consumir la nueva línea pendiente


                    switch (opc2) {
                        case 1:
                            System.out.println();
                            System.out.println("******* INICIO DE SESIÓN *******");
                            try {
                                UsuarioDAO usuarioDAO = new UsuarioDAO();
                                boolean inicio = usuarioDAO.inicioSesion(con);

                                if (inicio) {
                                    System.out.println("Desea contratar un paquete? (s/n)");
                                    String respuesta = tec.nextLine();
                                    if (respuesta.equalsIgnoreCase("s")) {
                                        System.out.print("Ingrese su DNI: ");
                                        String dni = tec.nextLine();
                                        System.out.print("Ingrese su número de cuenta: ");
                                        String numCuenta = tec.nextLine();
                                        System.out.print("Ingrese los ID de los paquetes a contratar separados por coma: ");
                                    
                                        PaquetesDAO.mostrarPaquetes(con);

                                        byte idPaquetes = tec.nextByte();

                                        usuarioDAO.contratarPaquete(dni, numCuenta, idPaquetes, con);
                                    }
                                }

                                

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            
                            System.out.println();
                            System.out.println("******* REGISTRARSE *******");
                            System.out.print("Ingrese su DNI: ");
                            String dni = tec.nextLine();

                            System.out.print("Ingrese su correo electrónico: ");
                            String correo = tec.nextLine();

                            System.out.print("Ingrese su contraseña: ");
                            String contrasenia = tec.nextLine();

                            System.out.print("Ingrese su nombre: ");
                            String nombre = tec.nextLine();

                            System.out.print("Ingrese sus apellidos: ");
                            String apellidos = tec.nextLine();

                            System.out.print("Ingrese su alias: ");
                            String alias = tec.nextLine();

                            System.out.print("Ingrese su dirección: ");
                            String direccion = tec.nextLine();

                            System.out.println("Si vienes de parte de otro usuario dame su dni (Si no dejalo en blanco)");
                            String referido_por = tec.nextLine();

                            Usuario nuevoUsuario = new Usuario(dni, correo, contrasenia, nombre, apellidos, alias, direccion, referido_por);

                            UsuarioDAO usuarioDAO = new UsuarioDAO();
                            usuarioDAO.registrarUsuario(nuevoUsuario, con);

                            System.out.println("Usuario registrado exitosamente.");
                            break;  
                        
                        case 0:
                            finish = false;
                            break;
                        default:
                            System.out.println("Opción inválida... Inténtelo de nuevo");
                            break;

                    }

                    break;

                default:
                    System.out.println("Opción inválida... Inténtelo de nuevo");
                    break;
            }
            

        }while(finish);


                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
 
    }

    


    private static void menuPaquetesAdmin(Connection con) {
        while (true) {
            System.out.println("\nMenú de Gestión de Paquetes:");
            System.out.println("1. Ver paquetes");
            System.out.println("2. Añadir contenido  a paquete");
            System.out.println("3. Eliminar contenido de paquete");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elija una opción: ");
            
            int opcion = tec.nextInt();
            tec.nextLine(); // Consumir la nueva línea pendiente
            
            PaquetesDAO pdao = new PaquetesDAO();
            try {
                switch (opcion) {
                    case 1:
                        pdao.mostrarPaquetes(con);
                        break;
                    case 2:
                        pdao.agregarContenidoPaquete(con);
                        break;
                    case 3:
                        pdao.eliminarContenidoPaquete(con);
                        break;
                    case 0:
                        return; // Volver al menú principal
                    default:
                        System.out.println("Opción inválida. Inténtelo de nuevo.");
                        break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void menuUsuarioAdmin(Connection con) throws SQLException{
       
        System.out.println("Menú Gestion de Usuarios");
        System.out.println("¿Que deseas hacer?");
        System.out.println("1. Buscar Usuario");
        System.out.println("2. Eliminar Usuario");

        UsuarioDAO u = new UsuarioDAO();

        byte opc = tec.nextByte();

        switch (opc) {
            case 1:
                u.buscarUsuario(con);
                break;
            case 2:
                System.out.println("Dame el DNI de la persona que quieres eliminar: ");
                String dni = tec.nextLine();
                u.eliminarUsuario(dni, con);
                break; 
            default:
                System.out.println("Opcion invalida.");
                break;
        }


    }


    private static void menuContenidoAdmin(ArrayList<Pelicula> peliculaLista,  ArrayList<Serie> serieLista,  ArrayList<Programa> programasTVLista, Connection con, ArrayList<Contenido> contenidoLista) throws SQLException{


        System.out.println("Menú Gestion de Contenido: ");

        contAdmin();
        int opc = tec.nextInt();    
        switch (opc) {
            case 1:
                 
            String nombre;
            int duracion;
            Byte genero;
            String generoString = "Desconocido";
            
                tec.nextLine();
                System.out.println("Dame el nombre del contenido:");
                nombre = tec.nextLine();
            
                System.out.println("Dame la duración del contenido (en minutos):");
                duracion = tec.nextInt();
            
                System.out.println("Dame el género:");
                System.out.println("\n1. Terror\n2. Ciencia Ficción\n3. Acción\n4. Suspense\n5. Comedia\n6. Desconocido");
                genero = tec.nextByte();
                generoString= obtenerGenero(genero);
    
                System.out.println("Fecha de estreno: ");
                int fechaEstreno = tec.nextInt();
                tec.nextLine();
    
                System.out.println("Pequeña descripción: ");
                String desc = tec.nextLine();
            
                Contenido contenido = new Contenido(nombre, duracion, generoString, fechaEstreno, desc);
                ContenidoDAO.aniadirContenido(contenido, con); 

                añadirTipoContenido(peliculaLista, serieLista, programasTVLista, con, contenido);
                break;

                

            case 2:
                mostrarContenido(contenidoLista);
                break;

            case 3:
                eliminarContenido(contenidoLista, con);
                break;

            case 0:
                System.out.println("Volviendo al menu del programa...");
                break;

            default:
                System.out.println("Opción incorrecta: ");
                break;
        }
    }
    private static void menuAdmin() {
        System.out.println("1. Sistema de contenido");
        System.out.println("2. Sistema de Usuarios");
        System.out.println("3. Sistema de Contrataciones y Paquetes");
        System.out.println("0. Salir");
        System.out.println("Elige una opción:");
    }
    private static void contAdmin(){

        System.out.println("1. Añadir Contenido");
        System.out.println("2. Mostrar Contenido");
        System.out.println("3. Eliminar Contenido");
        System.out.println("Elige una opción: ");

    
    }

    private static int menuTipoUsuario(){

        System.out.println("¿Que tipo de usuario eres?");
        System.out.println("1. Administrador");
        System.out.println("2. Usuario");

        int opc= tec.nextInt();

        return opc;

    }
    private static void menuUsuario(){
        System.out.println("1. Iniciar sesion");
        System.out.println("2. Registrarse");
        System.out.println("0. Volver");
    }

    private static void añadirTipoContenido(ArrayList<Pelicula> peliculaLista, ArrayList<Serie> serieLista, ArrayList<Programa> programasTVLista, Connection con, Contenido contenido) throws SQLException{
       
        byte opc;
    

        System.out.println("¿Qué tipo de contenido acabas de añadir?");
        System.out.println("1. película");
        System.out.println("2. serie");
        System.out.println("3. Programa TV");
        System.out.println("0. Salir");
        opc = tec.nextByte();

        switch (opc) {
            case 0:
                System.out.println("Volviendo al menú principal...");
                break;

            case 1:
                añadirPelicula(contenido, peliculaLista, con);

                break;

            case 2:
                añadirSerie(contenido, serieLista, con);
                break;

            case 3:
                añadirProgramaTV(contenido, programasTVLista, con);
                break;

            default:
                System.out.println("Opción inválida. Inténtalo de nuevo.");
                break;
        }
    }
    
    
    
    
    public static void añadirPelicula(Contenido contenido, ArrayList<Pelicula> peliculaLista, Connection con) {
        System.out.println("Dame el nombre de la película que quieres añadir:");
        String nombre = tec.nextLine();
    
        int contenidoId = ContenidoDAO.obtenerContenidoIDPorNombre(nombre, con);
        if (contenidoId == -1) {
            System.out.println("No se encontró contenido con ese nombre. Por favor, añade primero el contenido.");
            return;
        }
    
        System.out.println("Dame la duración del contenido (en minutos):");
        int duracion = tec.nextInt();
        tec.nextLine(); // Consumir la nueva línea pendiente
    
        System.out.println("Dame el género:");
        System.out.println("\n1. Terror\n2. Ciencia Ficción\n3. Acción\n4. Suspense\n5. Comedia\n6. Desconocido");
        byte genero = tec.nextByte();
        tec.nextLine(); // Consumir la nueva línea pendiente
        String generoString = obtenerGenero(genero);
    
        System.out.println("Fecha de estreno: ");
        int fechaEstreno = tec.nextInt();
        tec.nextLine(); // Consumir la nueva línea pendiente
    
        System.out.println("Pequeña descripción: ");
        String descripcion = tec.nextLine();
    
        Pelicula nuevaPelicula = new Pelicula(nombre, duracion, generoString, fechaEstreno, descripcion);
        peliculaLista.add(nuevaPelicula);
        PeliculaDAO.aniadirPelicula(nuevaPelicula, contenidoId, con);
    
        System.out.println("Película añadida correctamente.");
    }
    public static void añadirSerie(Contenido contenido, ArrayList<Serie> serieLista, Connection con) throws SQLException {

        System.out.println("Dame el nombre de la serie que quieres añadir:");
        String nombre = tec.nextLine();
    
        int contenidoId = ContenidoDAO.obtenerContenidoIDPorNombre(nombre, con);
        if (contenidoId == -1) {
            System.out.println("No se encontró contenido con ese nombre. Por favor, añade primero el contenido.");
            return;
        }

        System.out.println("Añadiendo detalles específicos para Serie.");
        System.out.println("Número de temporadas: ");
        int temporadas = tec.nextInt();
        tec.nextLine();
    
        System.out.println("¿En qué estado se encuentra? ");
        System.out.println("1. En emisión ");
        System.out.println("2. Finalizada");
        int opc = tec.nextInt();
        tec.nextLine(); // Consumir la nueva línea pendiente
        String estado = obtenerEstadoSerie(opc);
    
        Serie nuevaSerie = new Serie(contenido, temporadas, estado);
        serieLista.add(nuevaSerie);
        SerieDAO.cargarSerieDB(nuevaSerie, con);
        cargarFicheroSeries(serieLista);
        System.out.println("Serie añadida correctamente.");
    }
    public static void añadirProgramaTV(Contenido contenido, ArrayList<Programa> programasTVLista, Connection con) throws SQLException {

        System.out.println("Dame el nombre del prograna que quieres añadir:");
        String nombre = tec.nextLine();
    
        int contenidoId = ContenidoDAO.obtenerContenidoIDPorNombre(nombre, con);
        if (contenidoId == -1) {
            System.out.println("No se encontró contenido con ese nombre. Por favor, añade primero el contenido.");
            return;
        }

        System.out.println("Añadiendo detalles específicos para Programa TV.");
        System.out.println("Número de temporadas: ");
        int temporadas = tec.nextInt();
        tec.nextLine();
    
        Programa nuevoPrograma = new Programa(contenido, temporadas);
        programasTVLista.add(nuevoPrograma);
        ProgramaDAO.cargar(nuevoPrograma, con);
        cargarFicheroProgramasTV(programasTVLista);
        System.out.println("Programa TV añadido correctamente.");
    }


    public static void mostrarContenido(ArrayList<Contenido> contenidoLista){
        System.out.println("Contenido que tenemos en el programa: ");        
        for (Contenido contenido : contenidoLista) {
            contenido.toString();
        }

    }
    public static void eliminarContenido(ArrayList<Contenido> contenidoLista, Connection con)throws SQLException{

        boolean check = false;
        
        System.out.println();
        System.out.println("Eliminar contenido: ");
        System.out.println();


        System.out.println("Dame el nombre del contenido que quieres eliminar");
        String nombre = tec.nextLine();

        for (Contenido contenido : contenidoLista) {

            if (contenido.getTitulo().equalsIgnoreCase(nombre)) {

                contenidoLista.remove(contenido);

                System.out.println("Contenido eliminado: " + contenido.getTitulo());
                
                ContenidoDAO.eliminarContenido(contenido, con, nombre);

                check = true;


            
            }
        }
            if(check == false){
                System.out.println("Contenido inexistente. No se ha podido borrar.");
            }else{
                System.out.println("Contenido borrado con exito");
            }

        
    }

   


    public static void cargarFicheroPeliculas(ArrayList <Pelicula> peliculaLista){
        
        File f = new File("/Ficheros/Peliculas.obj");
 
         try{
             FileOutputStream fs = new FileOutputStream(f, true);
             ObjectOutputStream oos = new ObjectOutputStream(fs);
 
            
             for (Pelicula pelicula : peliculaLista ) {
                oos.writeObject(pelicula);
            }
            
 
             if(oos != null){
                 oos.close();
                 fs.close();
 
             }
     
         }catch(Exception e){
             e.printStackTrace();
         }
         
    }
    public static void cargarFicheroSeries(ArrayList <Serie> SerieLista){
         
         File f = new File("/Ficheros/Series.obj");
  
          try{
              FileOutputStream fs = new FileOutputStream(f, true);
              ObjectOutputStream oos = new ObjectOutputStream(fs);
  
              for (Serie serie : SerieLista ) {
                  oos.writeObject(serie);
              }
  
              if(oos != null){
                  oos.close();
                  fs.close();
  
              }
      
          }catch(Exception e){
              e.printStackTrace();
          }
          
    }
    public static void cargarFicheroProgramasTV(ArrayList <Programa> programasTVLista){
         
         File f = new File("/Ficheros/Programas.obj");
  
          try{
              FileOutputStream fs = new FileOutputStream(f, true);
              ObjectOutputStream oos = new ObjectOutputStream(fs);
  
              for (Contenido contenido : programasTVLista ) {
                  oos.writeObject(contenido);
              }
  
              if(oos != null){
                  oos.close();
                  fs.close();
  
              }
      
          }catch(Exception e){
              e.printStackTrace();
          }
          
    }
    public static void cargarFicheroContenido(ArrayList <Contenido> contenidoLista){

        File f = new File("/Ficheros/Contenido.obj");
  
          try{
              FileOutputStream fs = new FileOutputStream(f, true);
              ObjectOutputStream oos = new ObjectOutputStream(fs);
  
              for (Contenido contenido : contenidoLista ) {
                  oos.writeObject(contenido);
              }
  
              if(oos != null){
                  oos.close();
                  fs.close();
  
              }
      
          }catch(Exception e){
              e.printStackTrace();
          }

    }

    public static void cargarArrayPeliculas(ArrayList<Pelicula> peliculaLista) {
        File f = new File("/Ficheros/Peliculas.obj");
        if (f.exists() && f.length() > 0) { // Verificar si el archivo existe y no está vacío
            try (FileInputStream fs = new FileInputStream(f);
                 ObjectInputStream ois = new ObjectInputStream(fs)) {
    
                boolean continuar = true;
                while (continuar) {
                    try {
                        Pelicula p = (Pelicula) ois.readObject();
                        peliculaLista.add(p);
                    } catch (EOFException e) {
                        continuar = false; // Fin del archivo
                    }
                }
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El archivo Peliculas.obj no existe o está vacío.");
        }
    }
    public static void cargarArraySeries(ArrayList<Serie> serieLista) {
        File f = new File("/Ficheros/Series.obj");
        if (f.exists() && f.length() > 0) { // Verificar si el archivo existe y no está vacío
            try (FileInputStream fs = new FileInputStream(f);
                 ObjectInputStream ois = new ObjectInputStream(fs)) {
    
                boolean continuar = true;
                while (continuar) {
                    try {
                        Serie s = (Serie) ois.readObject();
                        serieLista.add(s);
                    } catch (EOFException e) {
                        continuar = false; // Fin del archivo
                    }
                }
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El archivo Series.obj no existe o está vacío.");
        }
    }
    public static void cargarArrayProgramasTV(ArrayList<Programa> programasTVLista) {
        File f = new File("/Ficheros/ProgramasTV.obj");
        if (f.exists() && f.length() > 0) { // Verificar si el archivo existe y no está vacío
            try (FileInputStream fs = new FileInputStream(f);
                 ObjectInputStream ois = new ObjectInputStream(fs)) {
    
                boolean continuar = true;
                while (continuar) {
                    try {
                        Programa pTv = (Programa) ois.readObject();
                        programasTVLista.add(pTv);
                    } catch (EOFException e) {
                        continuar = false; // Fin del archivo
                    }
                }
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El archivo ProgramasTV.obj no existe o está vacío.");
        }
    }
    public static void cargarArrayContenido(ArrayList<Contenido> contenidoLista) {
        File f = new File("/Ficheros/Cotenido.obj");
        if (f.exists() && f.length() > 0) { // Verificar si el archivo existe y no está vacío
            try (FileInputStream fs = new FileInputStream(f);
                 ObjectInputStream ois = new ObjectInputStream(fs)) {
    
                boolean continuar = true;
                while (continuar) {
                    try {
                        Contenido c = (Contenido) ois.readObject();
                        contenidoLista.add(c);
                    } catch (EOFException e) {
                        continuar = false; // Fin del archivo
                    }
                }
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El archivo Peliculas.obj no existe o está vacío.");
        }
    }




    private static String obtenerGenero(byte genero) {
        String g;
        switch (genero) {
            case 1:
                g = "Terror";
                break;
            case 2:
                g= "Ciencia Ficción";
                break;

            case 3:
                g= "Acción";
                break;

            case 4:
                g= "Suspense";
                break;

            case 5:
                g= "Comedia";
                break;

            default:
                g= "Desconocido";
                break;

        }
        return g;
    }
    private static String obtenerEstadoSerie(int opc) {
        switch (opc) {
            case 1:
                return "En emisión";
            case 2:
                return "Finalizada";
            default:
                return "Desconocido";
        }
    }


}

