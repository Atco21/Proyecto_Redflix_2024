


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
            
        // Conexión a la base de datos
        Conexion_DB _conexion_DB = new Conexion_DB();
        try {
           Connection con = _conexion_DB.AbrirConexion(); // Asignación de la conexión
        
        
            cargarArrayPeliculas(peliculaLista);
            cargarArraySeries(serieLista);
            cargarArrayProgramasTV(programasTVLista);
            boolean check = true;
            
            int sel = menuTipoUsuario();

            switch (sel) {
                case 1:
                    System.out.println();
                    System.out.println("************* HOLA ADMIN *************");
                    System.out.println("¿Que deseas hacer? ");

                    do {
                        menuAdmin();
                        byte opc = tec.nextByte();
                        tec.nextLine(); 

                        switch (opc) {
                            case 1:
                                menuContenidoAdmin( peliculaLista, serieLista, programasTVLista, con);
                                break;
                            case 2:
                                menuUsuarioAdmin(con);
                                break;

                            case 3:
                                menuPaquetesAdmin(con);
                                break;
                            case 0: 
                                _conexion_DB.CerrarConexion(con);
                                break;
                            default:
                                break;
                        }
                        
                    } while (check);
                    break;

                case 2:

                    System.out.println();
                    menuUsuario();
                    int opc = tec.nextInt();
                    tec.nextLine(); // Consumir la nueva línea pendiente


                    switch (opc) {
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
                        default:
                            System.out.println("Opción inválida... Inténtelo de nuevo");
                            break;
                    }

                    break;

                default:
                    System.out.println("Opción inválida... Inténtelo de nuevo");
                    break;
            }

            
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
            System.out.println("2. Añadir película a paquete");
            System.out.println("3. Eliminar película de paquete");
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


    private static void menuContenidoAdmin(ArrayList<Pelicula> peliculaLista,  ArrayList<Serie> serieLista,  ArrayList<Programa> programasTVLista, Connection con) throws SQLException{


        System.out.println("Menú Gestion de Contenido: ");

        contAdmin();
        int opc = tec.nextInt();    
        switch (opc) {
            case 1:
                añadirContenido(peliculaLista, serieLista, programasTVLista, con);
                break;

            case 2:
                mostrarContenido(peliculaLista, serieLista, programasTVLista);
                break;

            case 3:
                eliminarContenido(peliculaLista, serieLista, programasTVLista);
                break;

            case 0:
                System.out.println("Volviendo al menu del programa...");
                break;

            default:
                System.out.println("Opción incorrecta: ");
                break;
        }
    }
    private static int menuTipoUsuario(){

        System.out.println("¿Que tipo de usuario eres?");
        System.out.println("1. Administrador");
        System.out.println("2. Usuario");

        int opc= tec.nextInt();

        return opc;

    }
    private static void menuAdmin() {
        System.out.println("1. Sistema de contenido");
        System.out.println("2. Sistema de Usuarios");
        System.out.println("3. Sistema de Contrataciones y Paquetes");
        System.out.println("0. Salir");
        System.out.println("Elige una opción:");
    }
    private static void menuUsuario(){
        System.out.println("1. Iniciar sesion");
        System.out.println("2. Registrarse");
    }
    private static void contAdmin(){

        System.out.println("1. Aniadir contenido");
        System.out.println("2. Mostrar Contenido");
        System.out.println("3. Eliminar Contenido");
        System.out.println("Elige una opción: ");
    }


    public static void añadirContenido(ArrayList<Pelicula> peliculaLista, ArrayList<Serie> serieLista, ArrayList<Programa> programasTVLista, Connection con) throws SQLException{
       
        byte opc;

        System.out.println("¿Qué tipo de contenido deseas añadir?");
        System.out.println("1. Añadir película");
        System.out.println("2. Añadir serie");
        System.out.println("3. Añadir Programa TV");
        System.out.println("0. Salir");
        opc = tec.nextByte();

        switch (opc) {
            case 0:
                System.out.println("Volviendo al menú principal...");
                break;

            case 1:
                añadirPelicula(peliculaLista, con);
                break;

            case 2:
                añadirSerie(serieLista, con);
                break;

            case 3:
                añadirProgramaTV(programasTVLista, con);
                break;

            default:
                System.out.println("Opción inválida. Inténtalo de nuevo.");
                break;
        }
    }
    public static void mostrarContenido(ArrayList<Pelicula> peliculaLista, ArrayList<Serie> serieLista, ArrayList<Programa> programasTVLista) throws SQLException{
        System.out.println("Películas: ");
        for (Pelicula pelicula : peliculaLista) {
            System.out.println(pelicula);
        }

        System.out.println();

        System.out.println("Series: ");
        for (Serie serie : serieLista) {
            System.out.println(serie);
        }

        System.out.println();

        System.out.println("Programas: ");
        for (Programa programaTV : programasTVLista) {
            System.out.println(programaTV);
        }
    }
    public static void eliminarContenido(ArrayList<Pelicula> peliculaLista, ArrayList<Serie> serieLista,ArrayList <Programa> programasTVLista) throws SQLException{

        
        System.out.println();
        System.out.println("1. Eliminar Pelicula\n2. Eliminar Serie\n3. Eliminar Programa");
        byte opc = tec.nextByte();
        tec.nextLine();

        System.out.println();
        boolean check = false;

        switch (opc) {

            case 1:
                
                System.out.println("Dame el nombre de la pelicula que quieres eliminar: ");
                String nombre = tec.nextLine();

                for (Pelicula pelicula : peliculaLista) {

                    if (pelicula.getTitulo().equalsIgnoreCase(nombre)) {

                        peliculaLista.remove(pelicula);

                        System.out.println("Película eliminada: " + pelicula.getTitulo());
            
                        check = true;
                    
                    }

                    if(check == false){
                        System.out.println("Película inexistente. No se ha podido borrar.");
                    }else{
                        System.out.println("Película borrada con existo");
                    }
                }
            break;


            case 2:
                System.out.println();
                System.out.println("Dame el nombre de la serie que quieres eliminar: ");
                String nombre2=tec.nextLine();

                for (Serie serie : serieLista) {

                    if (serie.getTitulo().equalsIgnoreCase(nombre2)) {

                        serieLista.remove(serie);

                        check = true;

                        System.out.println("Serie eliminada: " + serie.getTitulo());
                    }

                        if(check == false){
                            System.out.println("Serie inexistente. No se ha podido borrar.");
                        }else{
                            System.out.println("Serie borrada con existo");
                        }
                    }
            break;


            case 3:
                System.out.println();
                System.out.println("Dame el nombre del Programa que quieres eliminar: ");
                String nombre3=tec.nextLine();

                for (Programa p : programasTVLista) {

                    if (p.getTitulo().equalsIgnoreCase(nombre3)) {
                        programasTVLista.remove(p);
                        check = true;
                        System.out.println("Programa eliminado: " + p.getTitulo());
                        
                    }

                        if(check == false){
                            System.out.println("Programa inexistente. No se ha podido borrar.");
                        }else{
                            System.out.println("Programa borrado con exito");
                        }
                    }
                
            break;
            

            default:

                System.out.println("Opción invalida.");

            break;
        }
    }
    
    
    
    public static void añadirPelicula(ArrayList<Pelicula> peliculaLista, Connection con) throws SQLException {

        boolean c = true;

        tec.nextLine();
        String nombre;
        int duracion;
        Byte genero;
        String generoString = "Desconocido";
        while (c) {
        
            System.out.println("Dame el nombre de la película:");
            nombre = tec.nextLine();
        
            if (nombre.isEmpty()) {
                System.out.println("Nombre inválido.");
                c = false;
            }
        
            System.out.println("Dame la duración de la película (en minutos):");
            duracion = tec.nextInt();
        
            if (duracion <= 0) {
                System.out.println("Duración inválida.");
                c = false;
            }
        
            System.out.println("Dame el género:");
            System.out.println("\n1. Terror\n2. Ciencia Ficción\n3. Acción\n4. Suspense\n5. Comedia\n6. Desconocido");
            genero = tec.nextByte();
            generoString= obtenerGenero(genero);

            System.out.println("Fecha de estreno: ");
            int fechaEstreno = tec.nextInt();
            tec.nextLine();

            System.out.println("Pequeña descripción: ");
            String desc = tec.nextLine();
        
        Pelicula nuevaPelicula = new Pelicula(nombre, duracion, generoString, fechaEstreno, desc);
        peliculaLista.add(nuevaPelicula);
        PeliculaDAO.cargarPelicula(nuevaPelicula, con);
        cargarFicheroPeliculas(nuevaPelicula);
        System.out.println("Película añadida correctamente.");

        c = false;
        }
    }
    public static void añadirSerie(ArrayList <Serie> serieLista, Connection con)throws SQLException{

        String nombre;
        int duracion;
        Byte genero;
        byte temporadas;
        String generoString;


        System.out.println("Dame el nombre de la serie: ");
        nombre = tec.nextLine();


        System.out.println("Dame la duracion media por capitulo de la serie: (en minutos) ");
        duracion = tec.nextInt();


        System.out.println("Dame el genero:");
        System.out.println("\n1. Terror\n2. Ciencia Ficcion\n3. Acción\n4. Suspense\n5. Comedia");
        genero = tec.nextByte();
        generoString = obtenerGenero(genero);


        System.out.println("Numero de temporadas: ");
        temporadas=tec.nextByte();
        

        System.out.println("Fecha de estreno: ");
        int fechaEstreno = tec.nextInt();


        System.out.println("Dame una breve descripción sobre la serie: ");
        String desc = tec.nextLine();


        System.out.println("¿En que estado se encuentra? ");
        System.out.println("1. En emisión ");
        System.out.println("2. Finalizadas");
        int opc = tec.nextInt();
        String estado = obtenerEstadoSerie(opc);
        

        Serie nuevaSerie = new Serie(nombre, duracion, temporadas, generoString, fechaEstreno, desc, estado);
        serieLista.add(nuevaSerie);
        SerieDAO.cargarSerieDB(nuevaSerie, con);
    }
    public static void añadirProgramaTV(ArrayList<Programa> programasTVLista, Connection con) throws SQLException{

        String nombre;
        int duracion;
        int temporadas;
        Byte genero;
        String generoString = "Desconocido";
        String mensaje = null;
        Boolean cont=true;


        while(cont){
            System.out.println("Dame el nombre del programa de TV: ");
            nombre = tec.nextLine();
            tec.next();
            if(nombre == null){ 
                mensaje = "Nombre invalido";
                cont = false;
            }else{
                System.out.println("Dame la duracion del programaTV: (en minutos) ");
                    duracion = tec.nextInt();
                if(duracion > 0){

                    System.out.println("Dame el genero:");
                    System.out.println("\n 1. Documentales\n 2. Reality Shows\n 3. Entrevistas y talkshows\n 4. Comedia");
                    genero = tec.nextByte();
                    
                    switch (genero) { 
                        case 1:
                            generoString = "Documentales";
                            break;
        
                        case 2:
                            generoString = "Reality Shows";
                            break;     
                        case 3:
                            generoString = "Entrevistas y talkshows";
                            break;
                        case 4:
                            generoString = "Comedia";
                            break;

                        default:
                            mensaje = "Genero invalido";
                            cont = false;
                    }

                    System.out.println("Cuantas temporadas tiene el programa de TV");
                    temporadas = tec.nextInt();

                    System.out.println("Cuanto se lanzo el programa: ");
                    int fechaEstreno = tec.nextInt();

                    System.out.println("Dame una breve descripción del programa: ");
                    String desc = tec.nextLine();

                    Programa pTv = new Programa(nombre, duracion, generoString, fechaEstreno, desc, temporadas);
                    programasTVLista.add(pTv);
                    ProgramaDAO.cargar(pTv, con);
                    

        
            }

        }
        if(!cont){
            System.out.println(mensaje);
        }
        }
    }


   


    public static void cargarFicheroPeliculas(Pelicula p){
        
        File f = new File("Peliculas.obj");
 
         try{
             FileOutputStream fs = new FileOutputStream("Peliculas.obj", true);
             ObjectOutputStream oos = new ObjectOutputStream(fs);
 
            
                oos.writeObject(p);
            
 
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
  
              for (Contenido contenido : SerieLista ) {
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
 
     public static void cargarFicheroProgramasTV(ArrayList <Programa> programasTVLista){
         
         File f = new File("//Ficheros//Series.obj");
  
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



     public static void cargarArrayPeliculas(ArrayList<Pelicula> peliculaLista) {
        File f = new File("Ficheros//Peliculas.obj");
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
        File f = new File("Ficheros//Series.obj");
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
        File f = new File("Ficheros//ProgramasTV.obj");
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

    private static String obtenerGenero(byte genero) {
        String g;
        switch (genero) {
            case 1:
                g = "Terror";
            case 2:
                g= "Ciencia Ficción";
            case 3:
                g= "Acción";
            case 4:
                g= "Suspense";
            case 5:
                g= "Comedia";
            default:
                g= "Desconocido";
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

