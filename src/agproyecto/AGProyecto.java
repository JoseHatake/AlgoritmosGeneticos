/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agproyecto;

import graficar.Graficar;
import poblacion.Generacion;
import poblacion.Individuo;
import poblacion.Poblacion;

/**
 *
 * @author Hatake
 */
public class AGProyecto {
	private static Generacion generacion;
	private static Graficar grafica;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        AGProyecto pr = new AGProyecto();
    }

    public AGProyecto() throws CloneNotSupportedException{
        pactica3();
    }

    public static void pactica3() throws CloneNotSupportedException{
    	generacion = new Generacion(6,10);
    	grafica = new Graficar();
    	grafica.setNombreFrame("Generación 1");
    	grafica.setTitutlo("Probabilidad de la primera generación");
    	grafica.setEtiquetaX("Individuos");
    	grafica.setEtiquetaY("Probabilidad");
        generacion.imprimirDatosDeGeneracion();
        generacion.imprimirDatosDeCruza();
        generacion.imprimirDatosDeMutacion();
    	grafica.dibujaGrafica(generacion.get(2).datosPorPoblacion(),"probabilidad");
    }
}
