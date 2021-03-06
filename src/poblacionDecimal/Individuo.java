/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poblacionDecimal;

import poblacionBinario.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Hatake
 */
public class Individuo implements Comparable{
    private ArrayList<Alelo> individuo;

    public Individuo(Integer numAlelos){
        individuo = new ArrayList<Alelo>();
        generarIndivudio(numAlelos);
    }
    
    public Individuo(Individuo in){
        individuo = (ArrayList<Alelo>) in.getArrayAlelos().clone();
    }
    
    public Individuo(ArrayList<Alelo> indi){
        individuo = indi;
    }
    
    public ArrayList<Alelo> getArrayAlelos(){
        return individuo;
    }

    public Integer getAlelos(){
        return individuo.size();
    }
    
    public void reamplazarAlelo(Integer num,Integer alelo){
        Alelo al = new Alelo(alelo);
        individuo.set(num, al);
    }
    
    public Alelo get(Integer in){
        return individuo.get(in);
    }
    
    public void add(Integer in,Alelo al){
        individuo.add(in,al);
    }

    private void generarIndivudio(Integer numAlelos){
        Random rn  =  new Random();
        Integer count,aux;
        aux = aux = rn.nextInt(numAlelos+1);;
        if (aux == 0) {
            aux = 1;
        }
        individuo.add(new Alelo(aux));
        for (int x = 1; x < numAlelos ; x++){
            aux = rn.nextInt(numAlelos+1);
            count = 0;
            if (aux == 0) {
                aux = 1;
            }
            do{
                if (individuo.get(count++).getValor() == aux){
                    aux = rn.nextInt(numAlelos+1);
                    if (aux == 0) {
                        aux = 1;
                    }
                    count = 0;
                }
            }while(count < individuo.size());
            individuo.add(new Alelo(aux));
        }
    }

    public Double aptitud(){
        Double x,operacion;
        x = this.valor();
        //operacion = ((x-5)/(2+Math.sin(x)));
        operacion = Math.pow(this.valor(),2);
        return Math.abs(operacion);
    }

    
    /**
     * Falta adecurlo para sacar el valor con un rango decimal
     */
    public Double valor(){
        Double x,aux;
        x = 0.0;
        aux = 0.0;
        for (int i = getAlelos()-1; i >= 0; i--){
            x += individuo.get(i).getValor()*Math.pow(2, aux++);
        }
        //return Integer.parseInt(String.valueOf(x).substring(0,String.valueOf(x).indexOf(".")));
        return x;
    }

    /**
     * Son iguales = 0
     * Es mayor la instacia = 1
     * Es mayor el objeto por parámetro = -1
     */
    public Integer compareTo(Individuo in){
        if (this.equals(in))
            return 0;
        else if (this.valor() > in.valor())
            return 1;
        else
            return -1;
    }

    @Override
    protected void finalize() throws Throwable {
        individuo.clear();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return individuo.clone();
    }

    public boolean equals(Individuo in) {
        return this.valor().equals(in.valor());
    }

    @Override
    public String toString(){
        String buff = new String();
        for(Alelo al: individuo)
            buff += al;
    	return buff + "";
    }

    @Override
    public int compareTo(Object o) {
        return this.aptitud().compareTo(((Individuo)o).aptitud());
    }
}
