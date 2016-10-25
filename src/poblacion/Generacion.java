/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poblacion;

import java.util.ArrayList;

/**
 *
 * @author Hatake
 */
public class Generacion {
    private ArrayList<Poblacion> poblacion;
    
    public Generacion(){
        poblacion = new ArrayList<Poblacion>(); 
    }
    public Generacion(Integer alelos, Integer individuos){
        poblacion = new ArrayList<Poblacion>();
        poblacion.add(new Poblacion(alelos,individuos));
    }

    public Poblacion get(Integer pb){
        return poblacion.get(pb);
    }

    public void agregarGeneracion(Integer alelos, Integer individuos){
        poblacion.add(new Poblacion(alelos,individuos));
    }

    public Double probabilidad(Integer generacion){
        //return Double.valueOf( poblacion.get(generacion).aptitud()/this.aptitud() );
        return poblacion.get(generacion).aptitud()/this.aptitud();
    }

    public Double aptitud() {
        Double sumAptitud;
        sumAptitud = 0.0;
        for(Poblacion pb: poblacion)
            sumAptitud += pb.aptitud();
        return sumAptitud;
    }

    public void imprimirDatosDeGeneracion(){
        Integer poblacionAImprimir = 0;
        Integer num;
        Poblacion actual;
        Individuo in;
        num = 0;
        actual = poblacion.get(poblacionAImprimir);
        System.out.println("No.\t | Poblacion Inicial\t | Valor X\t\t | Aptitud F(x) = x^2\t\t | Probabilidad\n");
        for (int x = 0;x<actual.getIndividuos();x++) {
            in = actual.getIndividuo(x);
            System.out.println(num+1 + "\t | " + in + " |\t " + in.valor() + " |\t " + in.aptitud() + " |\t " + actual.probabilidad(num++));
        }
        System.out.println("Suma valor: " + actual.aptitud());
        System.out.println("Promedio: " + actual.promedioAptitud());
        System.out.println("Max: " + actual.maxAptitud());
    }

    public void imprimirDatosDeCruza() throws CloneNotSupportedException{
        Integer poblacionAImprimir = 1;
        Integer poblacionAnterior = 0;
        Integer num;
        Poblacion actual,aux;
        Individuo in,ante;
        num = 0;
        poblacion.add(poblacion.get(poblacionAnterior).cruza1());
        actual = poblacion.get(poblacionAImprimir);
        aux = poblacion.get(poblacionAnterior);
        System.out.println("No.\t | Cruza\t | P.Cruza | Descendencia | Valor X\t\t | Aptitud F(x) = x^2\n");
        for (int x = 0;x<actual.getIndividuos();x++) {
            ante = aux.getIndividuo(x);
            in = actual.getIndividuo(x);
            System.out.println(num+1 + "\t | " + ante + " |\t " + actual.getPuntoDeCruzaPorIndividuo(num++)+ " |\t " + in + " |\t " + in.valor() + " |\t " + in.aptitud());
        }
        System.out.println("Suma valor: " + actual.aptitud());
        System.out.println("Promedio: " + actual.promedioAptitud());
        System.out.println("Max: " + actual.maxAptitud());
    }

    public void imprimirDatosDeMutacion(){
        Integer poblacionAImprimir = 2;
        Integer poblacionAnterior = 1;
        Integer num;
        Poblacion actual,aux;
        Individuo in,ante;
        num = 1;
        poblacion.add(poblacion.get(poblacionAnterior).muta1());
        actual = poblacion.get(poblacionAImprimir);
        aux = poblacion.get(poblacionAnterior);
        System.out.println("No.\t | Descendencia\t | Mutación | Valor X\t\t | Aptitud F(x) = x^2\n");
        for (int x = 0;x<actual.getIndividuos();x++) {
            ante = aux.getIndividuo(x);
            in = actual.getIndividuo(x);
            System.out.println(num++ + "\t | " + ante + " |\t " + in + " |\t " + in.valor() + " |\t " + in.aptitud());
        }
        System.out.println("Suma valor: " + actual.aptitud());
        System.out.println("Promedio: " + actual.promedioAptitud());
        System.out.println("Max: " + actual.maxAptitud());
    }
    
    /**
     * Regresa los datos para graficar la probabilidad de la aptitud por generacion
     */
    public ArrayList<Double> datosPorGeneracion(){
        ArrayList<Double> datos = new ArrayList<Double>();
        Integer aux = 0;
        for (Poblacion pb: poblacion)
            datos.add(pb.probabilidad(aux++));
        return datos;
    }

    /**
     * Borra el historial de las generaciones, dejando la última para trabajar
     */
    public void borrarGeneraciones(){
        for(int x = 0;x<poblacion.size()-1;x++)
            poblacion.remove(x);
        poblacion.trimToSize();
    }

    @Override
    protected void finalize() throws Throwable {
        poblacion.clear();
    }

    @Override
    public String toString(){
    	return poblacion.get(0) + "\n\tGeneraciones: " + poblacion.size();
    }
}
