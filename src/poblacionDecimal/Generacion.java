/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poblacionDecimal;

import poblacionBinario.*;
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

    public void imprimirDatosDeGeneracion(Integer generacion){
        Integer poblacionAImprimir = generacion;
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
    
    public void cruzaUnPunto(Integer generacion) throws CloneNotSupportedException{
        poblacion.add(poblacion.get(generacion).cruzaUnPunto());
    }
    
    public void cruzaDosPuntos(Integer generacion) throws CloneNotSupportedException{
        poblacion.add(poblacion.get(generacion).cruzaDosPuntos());
    }
    
    public void cruzaUniforme(Integer generacion) throws CloneNotSupportedException{
        poblacion.add(poblacion.get(generacion).cruzaUniforme());
    }
    
    public void cruzaAcentuada(Integer generacion) throws CloneNotSupportedException{
        poblacion.add(poblacion.get(generacion).cruzaAcentuada());
    }

    public void imprimirDatosDeCruza(Integer generacion){
        Integer poblacionAnterior = generacion;
        Integer poblacionAImprimir = generacion + 1;
        Integer num;
        Poblacion actual,aux;
        Individuo in,ante;
        num = 0;
        actual = poblacion.get(poblacionAImprimir);
        aux = poblacion.get(poblacionAnterior);
        if(actual.getPuntoDeCruza().isEmpty()){
            System.out.println("No.\t | Cruza\t | Descendencia | Valor X\t\t | Aptitud F(x) = x^2\n");
            for (int x = 0;x<actual.getIndividuos();x++) {
                ante = aux.getIndividuo(x);
                in = actual.getIndividuo(x);
                System.out.println(++num + "\t | " + ante + " |\t " + in + " |\t " + in.valor() + " |\t " + in.aptitud());
            }
        }
        else{
            System.out.println("No.\t | Cruza\t | P.Cruza | Descendencia | Valor X\t\t | Aptitud F(x) = x^2\n");
            for (int x = 0;x<actual.getIndividuos();x++) {
                ante = aux.getIndividuo(x);
                in = actual.getIndividuo(x);
                System.out.println(num+1 + "\t | " + ante + " |\t " + actual.getPuntoDeCruzaPorIndividuo(num++)+ " |\t " + in + " |\t " + in.valor() + " |\t " + in.aptitud());
            }
        }
    }

    public void imprimirDatosDeMutacion(Integer generacion,Double porcentaje){
        Integer poblacionAnterior = generacion + 1;
        Integer poblacionAImprimir = generacion + 2;
        Integer num;
        Poblacion actual,aux;
        Individuo in,ante;
        num = 1;
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
    
    public ArrayList<Double> minimos(Integer numGen) {
        ArrayList<Double> min = new ArrayList<Double>();
        for(int x = 0; x < numGen; x++){//x * 2 (+2 por generacion)
            min.add(poblacion.get((x*2)+2).min());
        }
        return min;
    }

    public ArrayList<Double> maximos(Integer numGen) {
        ArrayList<Double> max = new ArrayList<Double>();
        for(int x = 0; x < numGen; x++){//x * 2 (+2 por generacion)
            //System.out.println((x*2)+2 + " -- " + poblacion.get((x*2)+2).maxAptitud() + " -- " + poblacion.get((x*2)+2) + " -- " + poblacion.get((x*2)+2).max());
            max.add(poblacion.get((x*2)+2).max());
        }
        return max;
    }
}
