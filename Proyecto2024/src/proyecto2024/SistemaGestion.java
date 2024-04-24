
class SistemaGestionVisualizaciones {
    private String visualizacionesCliente1 = "";
    private String visualizacionesCliente2 = "";

    public void registrarVisualizacionCliente1(String contenido) {
        visualizacionesCliente1 += contenido + ", ";
    }

    public void registrarVisualizacionCliente2(String contenido) {
        visualizacionesCliente2 += contenido + ", ";
    }

    public String obtenerVisualizacionesCliente1() {
        return visualizacionesCliente1;
    }

    public String obtenerVisualizacionesCliente2() {
        return visualizacionesCliente2;
    }
}


class SistemaGestionRecomendaciones extends SistemaGestionVisualizaciones {
    public String generarRecomendacionesCliente1() {
        
        return obtenerVisualizacionesCliente1();
    }

    public String generarRecomendacionesCliente2() {
        
        return obtenerVisualizacionesCliente2();
    }
}


public class SistemaGestion {
    public static void main(String[] args) {
        
        SistemaGestionRecomendaciones sistemaRecomendaciones = new SistemaGestionRecomendaciones();

        
        sistemaRecomendaciones.registrarVisualizacionCliente1("Película A");
        sistemaRecomendaciones.registrarVisualizacionCliente1("Serie B");
        sistemaRecomendaciones.registrarVisualizacionCliente2("Película C");

        
        System.out.println("Visualizaciones del Cliente1: " + sistemaRecomendaciones.obtenerVisualizacionesCliente1());

        
        String recomendacionesCliente1 = sistemaRecomendaciones.generarRecomendacionesCliente1();
        System.out.println("Recomendaciones para el Cliente1: " + recomendacionesCliente1);

        String recomendacionesCliente2 = sistemaRecomendaciones.generarRecomendacionesCliente2();
        System.out.println("Recomendaciones para el Cliente2: " + recomendacionesCliente2);
    }
}