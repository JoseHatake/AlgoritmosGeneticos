/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poblacion;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hatake
 */
public class Poblacion {
	private ArrayList<Individuo> individuos;
    private ArrayList<Integer> puntoDeCruza;

    public Poblacion(Integer alelos,Integer individuos){
    	this.individuos = new ArrayList<Individuo>();
    	puntoDeCruza = new ArrayList<Integer>();
    	generarPolacion(alelos,individuos);
    }

    public Poblacion(ArrayList<Individuo> in, ArrayList<Integer> pdc){
    	individuos = in;
    	puntoDeCruza = pdc;
    }

    public Integer getIndividuos(){
    	return individuos.size();
    }

    public Individuo getIndividuo(Integer in){
    	return individuos.get(in);
    }

    public ArrayList<Integer> getPuntoDeCruza(){
    	return puntoDeCruza;
    }

    public Integer getPuntoDeCruzaPorIndividuo(Integer pCruza){
        return puntoDeCruza.get(pCruza);
    }

    private void generarPolacion(Integer alelos,Integer individuos){
    	for (int i = 0; i < individuos ; i++) {
    		this.individuos.add(new Individuo(alelos));
    	}
    }

    public double probabilidad(Integer individuo){
    	return individuos.get(individuo).aptitud()*1.0/aptitud()*1.0;
    }

    public Double aptitud() {
        Double sumAptitud;
        sumAptitud = 0.0;
        for(Individuo in: individuos)
            sumAptitud += in.aptitud();
        return sumAptitud;
    }

    public Double valor(){
    	Double sumValor;
        sumValor = 0.0;
        for(Individuo in: individuos)
            sumValor += in.valor();
        return sumValor;
    }

    public Double promedioAptitud(){
    	return Double.valueOf( aptitud()/getIndividuos() );
    }

    public Double promedioValor(){
    	return Double.valueOf( valor()/getIndividuos() );
    }

    public Double maxAptitud(){
		Double tmpIndi,aux;
		aux = 0.0;
		for (int i = 0; i < getIndividuos(); i++){
			tmpIndi = individuos.get(i).aptitud();
			if (tmpIndi > aux)
				aux = tmpIndi;
		}
		return aux;
    }

    /**
     * Regresa los datos para graficar la probabilidad de cada indivuduo de la poblacion
     */
    public ArrayList<Double> datosPorPoblacion(){
    	ArrayList<Double> datos = new ArrayList<Double>();
    	Integer aux = 0;
    	for (Individuo in: individuos)
    		datos.add(probabilidad(aux++));
    	return datos;
    }

    /**
     * Primera mutación simple, se intercambian los cromozomas de dos 
     * individuos dependiendo del punto de cruza aleatorio
     */
    public Poblacion cruza1() throws CloneNotSupportedException{
    	Random rn = new Random();
    	ArrayList<Individuo> auxIND = new ArrayList<Individuo>();
        ArrayList<Integer> pCruza = new ArrayList<Integer>();
        Individuo inaux,inaux2;
    	Integer in,al,pc;
    	in = getIndividuos();
    	al = individuos.get(0).getAlelos();
    	for (int i = 0; i < in; i += 2){
            pc = rn.nextInt(al);
            pCruza.add(pc);
            pCruza.add(pc);
            inaux = new Individuo((ArrayList<Alelo>) individuos.get(i).clone());
            inaux2 = new Individuo((ArrayList<Alelo>) individuos.get(i+1).clone());
            
            for (int j = al-1; j >= pc; j--){
                inaux.reamplazarAlelo(j,individuos.get(i+1).get(j).getValor());
                inaux2.reamplazarAlelo(j,individuos.get(i).get(j).getValor());
            }
            auxIND.add(inaux);
            auxIND.add(inaux2);
	}
        return new Poblacion(auxIND, pCruza);
    }

    /**
     * La mutación se hace a un alelo de todos los individuos aleatoriamente
     */
    public Poblacion muta1(){
    	Random rn = new Random();
    	ArrayList<Individuo> auxIND = individuos;
    	Integer tmp,al,in,aux;
    	al = auxIND.get(0).getAlelos();
        in = auxIND.size();
    	for (int x = 0; x < in;x++) {
            tmp = rn.nextInt(al);
            aux = new Alelo(auxIND.get(x).get(tmp).getValor()).cambiarValor().getValor();
            auxIND.get(x).reamplazarAlelo(tmp, aux);
    	}
    	return new Poblacion(auxIND,getPuntoDeCruza());
    }
    
    public void imprimirPoblacion(){
    	Integer aux = 1;
        for(Individuo in: individuos){
        	System.out.print(aux++ +" : " + in);
        }
    }

    @Override
    protected void finalize() throws Throwable {
    	individuos.clear();
    	puntoDeCruza.clear();
    }
    
    @Override
    public String toString(){
    	return "La poblacion tiene las caracteristicas:\n\tAlelos: " + individuos.get(0) + "\n\tIndividuos: " + this.getIndividuos();
    }
}
