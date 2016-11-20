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
        pactica7();
    }

    public static void pactica3() throws CloneNotSupportedException{
        Double porcientoAMutar = 30.0;
    	generacion = new Generacion(8,10);
    	grafica = new Graficar();
    	grafica.setNombreFrame("Generación 1");
    	grafica.setTitutlo("Probabilidad de la primera generación");
    	grafica.setEtiquetaX("Individuos");
    	grafica.setEtiquetaY("Probabilidad");
        generacion.imprimirDatosDeGeneracion(0);
        generacion.cruzaUnPunto(0);
        generacion.imprimirDatosDeCruza(0);
        generacion.mutaPorPorcentaje(0, porcientoAMutar);
        generacion.imprimirDatosDeMutacion(0,porcientoAMutar);
        grafica.addSerie(generacion.get(2).datosPorPoblacion(),"probabilidad");
    	grafica.dibujaGrafica();
    }
    
    public static void pactica4() throws CloneNotSupportedException{
        Integer numGen = 50;//10,20,30,40,50
        Double porcientoAMutar = 30.0;
    	generacion = new Generacion(5,4);
    	grafica = new Graficar();
    	grafica.setNombreFrame("Generaciones");
    	grafica.setTitutlo("Probabilidad del fitness de generación");
    	grafica.setEtiquetaX("Generaciones");
    	grafica.setEtiquetaY("Fitness");
        for(int x = 0; x < numGen; x++){
            System.out.println("\nGeneración " + x + "\n");
            generacion.imprimirDatosDeGeneracion(x*2);
            generacion.cruzaUnPunto(x*2);
            generacion.imprimirDatosDeCruza(x*2);
            generacion.mutaPorPorcentaje(x*2, porcientoAMutar);
            generacion.imprimirDatosDeMutacion(x*2,porcientoAMutar);
            //generacion.get((x+1)*2).seleccionarPorRuletaConRanking(S);
            //generacion.get((x+1)*2).seleccionarPorTorneoP(torneo);
        }
        grafica.addSerie(generacion.maximos(numGen),"Máximos");
        grafica.addSerie(generacion.minimos(numGen),"Mínimos");
    	grafica.dibujaGrafica();
    }
    
    public static void pactica5() throws CloneNotSupportedException{
        Integer numGen = 100;//10,30,50,100
        Double porcientoAMutar = 10.0;
        Double S = 1.1;
        Double torneo = 70.0;
    	generacion = new Generacion(4,16);
    	grafica = new Graficar();
    	grafica.setNombreFrame("Generaciones");
    	grafica.setTitutlo("Probabilidad del fitness de generación");
    	grafica.setEtiquetaX("Generaciones");
    	grafica.setEtiquetaY("Fitness");
        for(int x = 0; x < numGen; x++){
            System.out.println("\nGeneración " + x + "\n");
            generacion.imprimirDatosDeGeneracion(x*2);
            generacion.cruzaUnPunto(x*2);
            generacion.imprimirDatosDeCruza(x*2);
            generacion.mutaPorPorcentaje(x*2, porcientoAMutar);
            generacion.imprimirDatosDeMutacion(x*2,porcientoAMutar);
            //generacion.get((x+1)*2).seleccionarPorRuletaConRanking(S);
            generacion.get((x+1)*2).seleccionarPorTorneoP(torneo);
        }
        grafica.addSerie(generacion.maximos(numGen),"Máximos");
        grafica.addSerie(generacion.minimos(numGen),"Mínimos");
    	grafica.dibujaGrafica();
    }
    
    public static void pactica6() throws CloneNotSupportedException{
        Integer numGen = 100;//10,30,50,100
        Double porcientoAMutar = 10.0;
        Double S = 1.1;
        //Double torneo = 70.0;
    	generacion = new Generacion(4,16);
    	grafica = new Graficar();
    	grafica.setNombreFrame("Generaciones");
    	grafica.setTitutlo("Probabilidad del fitness de generación");
    	grafica.setEtiquetaX("Generaciones");
    	grafica.setEtiquetaY("Fitness");
        for(int x = 0; x < numGen; x++){
            System.out.println("\nGeneración " + x + "\n");
            generacion.imprimirDatosDeGeneracion(x*2);
            generacion.cruzaUnPunto(x*2);
            generacion.imprimirDatosDeCruza(x*2);
            generacion.mutaPorPorcentaje(x*2, porcientoAMutar);
            generacion.imprimirDatosDeMutacion(x*2,porcientoAMutar);
            generacion.get((x+1)*2).seleccionarPorRuletaConRanking(S);
            //generacion.get((x+1)*2).seleccionarPorTorneoP(torneo);
        }
        grafica.addSerie(generacion.maximos(numGen),"Máximos");
        grafica.addSerie(generacion.minimos(numGen),"Mínimos");
    	grafica.dibujaGrafica();
    }
    
    public static void pactica7() throws CloneNotSupportedException{
        Integer numGen = 100;//10,30,50,100
        Double porcientoAMutar = 10.0;
        Double S = 1.1;
        //Double torneo = 70.0;
    	generacion = new Generacion(8,24);
    	grafica = new Graficar();
    	grafica.setNombreFrame("Generaciones");
    	grafica.setTitutlo("Probabilidad del fitness de generación");
    	grafica.setEtiquetaX("Generaciones");
    	grafica.setEtiquetaY("Fitness");
        for(int x = 0; x < numGen; x++){
            System.out.println("\nGeneración " + x + "\n");
            generacion.imprimirDatosDeGeneracion(x*2);
            //generacion.cruzaUnPunto(x*2);
            //generacion.cruzaDosPuntos(x*2);
            //generacion.cruzaUniforme(x*2);
            generacion.cruzaAcentuada(x*2);
            generacion.imprimirDatosDeCruza(x*2);
            generacion.mutaPorPorcentaje(x*2, porcientoAMutar);
            generacion.imprimirDatosDeMutacion(x*2,porcientoAMutar);
            //generacion.get((x+1)*2).seleccionarPorRuletaConRanking(S);
            //generacion.get((x+1)*2).seleccionarPorTorneoP(torneo);
        }
        grafica.addSerie(generacion.maximos(numGen),"Máximos");
        grafica.addSerie(generacion.minimos(numGen),"Mínimos");
    	grafica.dibujaGrafica();
    }
}
