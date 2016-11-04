/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agproyecto;

import graficar.Graficar;
import poblacion.Generacion;

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
        pactica4();
    }

    public static void pactica3() throws CloneNotSupportedException{
    	generacion = new Generacion(8,10);
    	grafica = new Graficar();
    	grafica.setNombreFrame("Generación 1");
    	grafica.setTitutlo("Probabilidad de la primera generación");
    	grafica.setEtiquetaX("Individuos");
    	grafica.setEtiquetaY("Probabilidad");
        generacion.imprimirDatosDeGeneracion(0);
        generacion.imprimirDatosDeCruza(0);
        generacion.imprimirDatosDeMutacion(0);
        grafica.addSerie(generacion.get(2).datosPorPoblacion(),"probabilidad");
    	grafica.dibujaGrafica();
    }
    
    public static void pactica4() throws CloneNotSupportedException{
        Integer numGen = 10;
    	generacion = new Generacion(6,6);
    	grafica = new Graficar();
    	grafica.setNombreFrame("Generaciones");
    	grafica.setTitutlo("Probabilidad del fitness de generación");
    	grafica.setEtiquetaX("Generaciones");
    	grafica.setEtiquetaY("Probabilidad");
        for(int x = 0; x < numGen; x++){
            System.out.println("\nGeneración " + x + "\n");
            generacion.imprimirDatosDeGeneracion(x*2);
            generacion.imprimirDatosDeCruza(x*2);
            generacion.imprimirDatosDeMutacion(x*2);
        }
        grafica.addSerie(generacion.maximos(numGen),"Máximos");
        grafica.addSerie(generacion.minimos(numGen),"Mínimos");
    	grafica.dibujaGrafica();
    }
}
