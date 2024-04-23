/*
 * Autor: Alfred Thomas Comanescu
 */

import java.util.*;

public class TestContenido {

    //MAIN
    public static void main(String[] args) {

            ArrayList <Pelicula> peliculaLista = new ArrayList<>();
            ArrayList <Serie> serieLista = new ArrayList<>();


            String nombre="";
            int duracion=0, genero=0;        
            boolean check = true;

            Scanner tec = new Scanner(System.in);
   
            while (check == true) {
                
            

            byte opc; 

            menu();
            opc  = tec.nextByte();

            switch (opc) {
                case 1:
                
                    añadirContenido(nombre, duracion, genero, peliculaLista, serieLista);
                    break;

                case 2:

                    System.out.println("\n");
                    mostrarContenido(peliculaLista, serieLista);

                    break;

                case 3: 

                    eliminarContenido(peliculaLista, serieLista);

                    break;

                case 0:

                    System.out.println("Saliendo del programa...");
                    check =false;

                    break;
                default:

                    System.out.println("Opcion incorrecta: ");

                    break;
            }

            


            
        //Pelicula pelicula = new Pelicula("Fast & Furious 8", 149, "Acción");
        //Series serie = new Series("Stranger Things", 50, 4, "Misterio");

        }

    }


    public static void añadirContenido(String nombre, int duracion,int género, ArrayList <Pelicula> peliculaLista,  ArrayList <Serie> serieLista){
        boolean acceso = true; 
        byte opc; 
        Scanner tec = new Scanner(System.in);

        System.out.println("Que tipo de contenido deseas añadir: ");
        System.out.println("1. Añadir película");
        System.out.println("2. Añadir serie");
        System.out.println("0. Salir");
        opc = tec.nextByte();

        while (acceso == true) {
            
        
            switch (opc) {

                case 0:
                    System.out.println("Volviendo al menu principal...");
                    acceso = false; 
                    break;

                case 1:
                    añadirPelicula(peliculaLista);
                    acceso = false; 

                    break;
                case 2:
                    añadirSerie(serieLista);
                    acceso = false; 
                    break;

                default:
            
                    System.out.println("Opción invalida. Intentento de nuevo");
                    
                    break;
            }
        
        }

    }



    public static void menu(){


        System.out.println("1. Añadir contenido ");
        System.out.println("2. Visualizar contenido añadido");
        System.out.println("3. Eliminar contenido");
        System.out.println("0. Salir");

       
        
    }



    public static void añadirPelicula(ArrayList <Pelicula> peliculaLista){
        Scanner tec = new Scanner(System.in);

        String nombre;
        int duracion;
        Byte genero;
        String generoString;

        System.out.println("Dame el nombre de la pelicula: ");
        nombre = tec.nextLine();

        System.out.println("Dame la duracion de la pelicula: (en minutos) ");
        duracion = tec.nextInt();

        System.out.println("Dame el genero:");
        System.out.println("\n 1. Terror\n 2. Ciencia Ficcion\n 3. Acción\n 4. Suspense\n 5. Comedia");
        genero = tec.nextByte();
        

        switch (genero) {
            case 1:
                generoString = "Terror";
                break;

            case 2:
                generoString = "Ciencia Ficcion";
                break;     
            case 3:
            generoString = "Accion";
                break;
            case 4:
            generoString = "Suspense";
                break;
            case 5: 

            generoString = "Comedia";
                break;
            default:
               generoString = "Desconocido";
                break;
        }
        

        Pelicula nuevaPelicula = new Pelicula(nombre, duracion, generoString);

        peliculaLista.add(nuevaPelicula);
        

       


    }


    public static void  añadirSerie(ArrayList <Serie> serieLista){
        Scanner tec = new Scanner(System.in);

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


        System.out.println("Numero de temporadas: ");
        temporadas=tec.nextByte();
        
        

        switch (genero) {
            case 1:
                generoString = "Terror";
                break;

            case 2:
                generoString = "Ciencia Ficcion";
                break;     
            case 3:
            generoString = "Accion";
                break;
            case 4:
            generoString = "Suspense";
                break;
            case 5: 

            generoString = "Comedia";
                break;
            default:
               generoString = "Desconocido";
                break;
        }
        

        Serie nuevaSerie = new Serie(nombre, duracion, temporadas, generoString);

        serieLista.add(nuevaSerie);
        

    }



    public static void mostrarContenido(ArrayList <Pelicula> peliculaLista, ArrayList <Serie> serieLista){


        System.out.println("Las películas: ");
        System.out.println();

        for (Pelicula pelicula : peliculaLista) {
            System.out.println("Título: " +pelicula.getTitulo());

            System.out.println("Duración: "+pelicula.getDuracion());

            System.out.println("Género: " + pelicula.getGenero());
            System.out.println();
        }

        System.out.println("Las series: ");
        System.out.println();

        for (Serie serie : serieLista) {
            System.out.println("Título: " +serie.getTitulo());

            System.out.println("Duración media por capítulo: "+serie.getDuracion());

            System.out.println("Temporadas: " + serie.getTemporadas());
            
            System.out.println("Género: " + serie.getGenero());
            System.out.println();
        }




    }


    public static void eliminarContenido(ArrayList<Pelicula> peliculaLista, ArrayList<Serie> serieLista) {


        Scanner tec = new Scanner(System.in);
        

        System.out.println();
        System.out.println("1. Eliminar pelicula\n2. Eliminar serie");
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
                System.out.println("Dame el nombre de la pelicula que quieres eliminar: ");
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
            
            default:

                System.out.println("Opción invalida.");

                break;
        }
    }
}


    
