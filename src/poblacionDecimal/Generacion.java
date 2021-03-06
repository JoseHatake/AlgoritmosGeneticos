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
    
    public void cruzaOX(Integer generacion) throws CloneNotSupportedException{
        poblacion.add(poblacion.get(generacion).cruzaOX());
    }
    
    public void cruzaPMX(Integer generacion) throws CloneNotSupportedException{
        poblacion.add(poblacion.get(generacion).cruzaPMX());
    }
    
    public void cruzaPBX(Integer generacion) throws CloneNotSupportedException{
        poblacion.add(poblacion.get(generacion).cruzaPBX());
    }
    
    public void cruzaOBX(Integer generacion) throws CloneNotSupportedException{
        poblacion.add(poblacion.get(generacion).cruzaOBX());
    }
    
    public void cruzaCCX(Integer generacion) throws CloneNotSupportedException{
        poblacion.add(poblacion.get(generacion).cruzaCCX());
    }

    public void imprimirDatosDeCruza(Integer generacion){
        Integer poblacionAnterior = generacion;
        Integer poblacionAImprimir = generacion + 1;
        Integer num,count;
        Poblacion actual,aux;
        Individuo in,ante;
        Boolean flag = true;
        num = 0;
        actual = poblacion.get(poblacionAImprimir);
        aux = poblacion.get(poblacionAnterior);
        if(!actual.getPatronDeCruza().isEmpty()){
            System.out.println("No.\t | Cruza\t | Descendencia | P.Cruza");
            for (int x = 0;x<actual.getIndividuos();x++) {
                ante = aux.getIndividuo(x);
                in = actual.getIndividuo(x);
                System.out.println(++num + "\t | " + ante + " |\t " + in + " |\t " + actual.getPatronDeCruza().get(x));
            }
        }
        else{
            System.out.println("No.\t | Cruza\t | P.Cruza | Descendencia");
            for (int x = 0;x<actual.getIndividuos();x++) {
                ante = aux.getIndividuo(x);
                in = actual.getIndividuo(x);
                System.out.print(num+1 + "\t | ");
                for (int i = 0; i < ante.getAlelos(); i++) {
                    if (!flag) {
                        System.out.print(ante.get(i));
                    }
                    if ((actual.getPuntoDeCruzaPorIndividuo(num)-1) == i) {
                        if (flag) {
                            System.out.print("|"+ante.get(i));
                        }
                        else
                            System.out.print("|");
                        flag = !flag;
                        continue;
                    }
                    if (flag) {
                        System.out.print(ante.get(i));
                    }   
                }
                System.out.println(" |\t " + actual.getPuntoDeCruzaPorIndividuo(num++)+ " |\t " + in);
            }
        }
    }
    
    public void imprimirDatosDeCruza(){
        Integer poblacionAnterior = 0;
        Integer poblacionAImprimir = poblacion.size()-1;
        Integer num,count;
        Poblacion actual,aux;
        Individuo in,ante;
        Boolean flag = true;
        num = 0;
        actual = poblacion.get(poblacionAImprimir);
        aux = poblacion.get(poblacionAnterior);
        if(!actual.getPatronDeCruza().isEmpty()){
            System.out.println("No.\t | Cruza\t | Descendencia | P.Cruza");
            for (int x = 0;x<actual.getIndividuos();x++) {
                ante = aux.getIndividuo(x);
                in = actual.getIndividuo(x);
                System.out.println(++num + "\t | " + ante + " |\t " + in + " |\t " + actual.getPatronDeCruza().get(x));
            }
        }
        else{
            System.out.println("No.\t | Cruza\t | P.Cruza | Descendencia");
            for (int x = 0;x<actual.getIndividuos();x++) {
                ante = aux.getIndividuo(x);
                in = actual.getIndividuo(x);
                System.out.print(num+1 + "\t | ");
                for (int i = 0; i < ante.getAlelos(); i++) {
                    if (!flag) {
                        System.out.print(ante.get(i));
                    }
                    if ((actual.getPuntoDeCruzaPorIndividuo(num)-1) == i) {
                        if (flag) {
                            System.out.print("|"+ante.get(i));
                        }
                        else
                            System.out.print("|");
                        flag = !flag;
                        continue;
                    }
                    if (flag) {
                        System.out.print(ante.get(i));
                    }   
                }
                System.out.println(" |\t " + actual.getPuntoDeCruzaPorIndividuo(num++)+ " |\t " + in);
            }
        }
    }
    
    public void mutaInsert(Integer generacion) throws CloneNotSupportedException{
        poblacion.add(poblacion.get(generacion).mutaInsert());
    }
    
    public void mutaDesplazamiento(Integer generacion) throws CloneNotSupportedException{
        poblacion.add(poblacion.get(generacion).mutaDesplazamiento());
    }
    
    public void mutaIntercambioR(Integer generacion) throws CloneNotSupportedException{
        poblacion.add(poblacion.get(generacion).mutaIntercambioR());
    }
    
    public void mutaHeuristica(Integer generacion) throws CloneNotSupportedException{
        poblacion.add(poblacion.get(generacion).mutaHeuristica());
    }

    public void imprimirDatosDeMutacion(){
        ArrayList<ArrayList<String>> patron = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<Integer>> patronNum = new ArrayList<ArrayList<Integer>>();
        Integer poblacionAnterior = 0;
        Integer poblacionAImprimir = poblacion.size()-1;
        Integer num;
        Poblacion actual,aux;
        Individuo in,ante;
        num = 1;
        actual = poblacion.get(poblacionAImprimir);
        aux = poblacion.get(poblacionAnterior);
        patron = poblacion.get(poblacionAImprimir).getPatronDeMuta();
        patronNum = poblacion.get(poblacionAImprimir).getPatronDeCruza();
        System.out.println("No.\t | Descendencia\t | Mutación\t | Patrón de mutación");
        if (patron.isEmpty()) {
            for (int x = 0;x<actual.getIndividuos();x++) {
                ante = aux.getIndividuo(x);
                in = actual.getIndividuo(x);
                System.out.print(num++ + "\t | " + ante + " |\t " + in + " |\t ");
                for (int i = 0; i < patronNum.get(x).size(); i++) {
                    System.out.print(patronNum.get(x).get(i) + " ");
                }
                System.out.println();
            }
        }
        else{
            for (int x = 0;x<actual.getIndividuos();x++) {
                ante = aux.getIndividuo(x);
                in = actual.getIndividuo(x);
                System.out.print(num++ + "\t | " + ante + " |\t " + in + " |\t ");
                for (int i = 0; i < patron.get(x).size(); i++) {
                    System.out.print(patron.get(x).get(i) + " ");
                }
                System.out.println();
            }
        }
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
