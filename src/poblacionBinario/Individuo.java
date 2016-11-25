/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poblacionBinario;

import java.util.ArrayList;

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
        for (int x = 0; x < numAlelos ; x++)
            individuo.add(new Alelo());
    }

    public Double aptitud(){
        Double x,operacion;
        x = this.valor();
        operacion = ((x-5)/(2+Math.sin(x)));
        //operacion = Math.pow(this.valor(),2);
        return Math.abs(operacion);
    }

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

    public void convertToGary(){
        Integer al;
        al = getAlelos();
        for (int x = 0; x < al-1; x++){
                individuo.add(x, individuo.get(x).XOR(individuo.get(x-1) ));
        }
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
            buff += al.getValor();
    	return buff + "";
    }

    @Override
    public int compareTo(Object o) {
        return this.aptitud().compareTo(((Individuo)o).aptitud());
    }
}
